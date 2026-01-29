package com.lamb.chefaiserver.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lamb.chefaiserver.common.Result;
import com.lamb.chefaiserver.entity.Inventory;
import com.lamb.chefaiserver.entity.Preference;
import com.lamb.chefaiserver.entity.Recipe;
import com.lamb.chefaiserver.entity.RecipeImage;
import com.lamb.chefaiserver.mapper.RecipeImageMapper;
import com.lamb.chefaiserver.mapper.RecipeMapper;
import com.lamb.chefaiserver.service.InternalImageService;
import com.lamb.chefaiserver.service.InventoryService;
import com.lamb.chefaiserver.service.PreferenceService;
import com.lamb.chefaiserver.service.RecipeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 菜谱服务实现（含 AI 推荐逻辑）。
 */
@Service
public class RecipeServiceImpl extends ServiceImpl<RecipeMapper, Recipe> implements RecipeService {

    private static final Logger log = LoggerFactory.getLogger(RecipeServiceImpl.class);

    /** 生成菜谱的 AI 客户端。 */
    private final ChatClient chatClient;
    
    /** 用户偏好服务。 */
    private final PreferenceService preferenceService;
    
    /** 用户库存服务。 */
    private final InventoryService inventoryService;
    
    /** 图片生成服务。 */
    private final InternalImageService imageService;
    
    /** 菜谱图片 Mapper。 */
    private final RecipeImageMapper recipeImageMapper;
    
    /** 解析 AI 返回 JSON 的 ObjectMapper。 */
    private final ObjectMapper objectMapper;

    /**
     * 解析 AI 返回 JSON 的临时模型。
     */
    static class AiRecipe {
        public String menu_name;
        public List<String> ingredients;
        public List<String> steps;
        public String recommendation;
    }

    /**
     * 构造并注入所需依赖。
     */
    public RecipeServiceImpl(ChatClient.Builder chatClientBuilder, PreferenceService preferenceService, InventoryService inventoryService, InternalImageService imageService, RecipeImageMapper recipeImageMapper, ObjectMapper objectMapper) {
        this.chatClient = chatClientBuilder.build();
        this.preferenceService = preferenceService;
        this.inventoryService = inventoryService;
        this.imageService = imageService;
        this.recipeImageMapper = recipeImageMapper;
        this.objectMapper = objectMapper;
    }

    /** {@inheritDoc} */
    @Override
    public Result<List<Recipe>> getRecommendations(Integer userId) {
        // 1. Get User Info
        List<Preference> preferences = preferenceService.getUserPreferences(userId).getData();
        List<Inventory> inventory = inventoryService.getUserInventory(userId).getData();
        
        String preferenceStr = preferences.stream().map(Preference::getPreferenceName).collect(Collectors.joining(", "));
        String inventoryStr = inventory.stream().map(item -> item.getFoodName() + "(" + item.getQuantity() + ")").collect(Collectors.joining(", "));
        
        // 2. Build Prompt
        String prompt = String.format("""
                你是一个专业的大厨。请根据用户的口味偏好和现有食材，推荐3道适合的菜谱。
                用户口味: %s
                用户现有食材: %s
                
                请严格按照以下JSON格式返回结果（不要包含Markdown格式标记，直接返回JSON数组）:
                [
                    {
                        "menu_name": "菜名",
                        "ingredients": ["食材1", "食材2"],
                        "steps": ["步骤1", "步骤2"],
                        "recommendation": "建议搭配..."
                    }
                ]
                """, preferenceStr, inventoryStr);

        try {
            // 3. Call AI
            String response = chatClient.prompt().user(prompt).call().content();
            
            // Cleanup response if it contains markdown code blocks
            if (response != null) {
                if (response.startsWith("```json")) {
                    response = response.substring(7);
                }
                if (response.startsWith("```")) {
                    response = response.substring(3);
                }
                if (response.endsWith("```")) {
                    response = response.substring(0, response.length() - 3);
                }
            }
            
            List<AiRecipe> aiRecipes = objectMapper.readValue(response, new TypeReference<>() {
            });
            List<Recipe> savedRecipes = new ArrayList<>();

            for (AiRecipe aiRecipe : aiRecipes) {
                // 4. Save Recipe
                Recipe recipe = new Recipe();
                recipe.setUserId(userId);
                recipe.setMenuName(aiRecipe.menu_name);
                recipe.setIngredients(objectMapper.writeValueAsString(aiRecipe.ingredients));
                recipe.setSteps(objectMapper.writeValueAsString(aiRecipe.steps));
                recipe.setRecommendation(aiRecipe.recommendation);
                this.save(recipe);
                
                // 5. Generate and Save Image
                String imageUrl = imageService.generateImage(recipe.getMenuName());
                RecipeImage recipeImage = new RecipeImage();
                recipeImage.setMenuId(recipe.getMenuId());
                recipeImage.setImageUrl(imageUrl);
                recipeImageMapper.insert(recipeImage);
                
                savedRecipes.add(recipe);
            }
            
            return Result.success(savedRecipes);
            
        } catch (Exception e) {
            log.error("生成菜谱失败，userId={}", userId, e);
            return Result.error("生成菜谱失败：" + e.getMessage());
        }
    }

    /** {@inheritDoc} */
    @Override
    public Result<Map<String, Object>> getRecipeDetail(Integer menuId) {
        Recipe recipe = this.getById(menuId);
        if (recipe == null) {
            return Result.error("未找到菜谱");
        }
        
        RecipeImage image = recipeImageMapper.selectOne(new LambdaQueryWrapper<RecipeImage>().eq(RecipeImage::getMenuId, menuId));
        
        Map<String, Object> detail = new HashMap<>();
        detail.put("recipe", recipe);
        detail.put("image_url", image != null ? image.getImageUrl() : null);
        
        return Result.success(detail);
    }

    /** {@inheritDoc} */
    @Override
    public Result<List<Map<String, Object>>> getHistory(Integer userId, Integer limit) {
        int safeLimit = normalizeLimit(limit);
        List<Recipe> list = this.list(new LambdaQueryWrapper<Recipe>()
                .eq(Recipe::getUserId, userId)
                .orderByDesc(Recipe::getCreatedAt, Recipe::getMenuId)
                .last("limit " + safeLimit));
        if (list.isEmpty()) {
            return Result.success(new ArrayList<>());
        }

        List<Integer> menuIds = list.stream().map(Recipe::getMenuId).collect(Collectors.toList());
        List<RecipeImage> images = recipeImageMapper.selectList(
                new LambdaQueryWrapper<RecipeImage>().in(RecipeImage::getMenuId, menuIds)
        );
        Map<Integer, String> imageMap = images.stream()
                .collect(Collectors.toMap(RecipeImage::getMenuId, RecipeImage::getImageUrl, (a, b) -> a));

        List<Map<String, Object>> result = list.stream().map(recipe -> {
            Map<String, Object> item = new HashMap<>();
            item.put("recipe", recipe);
            item.put("image_url", imageMap.get(recipe.getMenuId()));
            return item;
        }).collect(Collectors.toList());

        return Result.success(result);
    }

    private int normalizeLimit(Integer limit) {
        if (limit == null || limit <= 0) {
            return 20;
        }
        return Math.min(limit, 100);
    }
}
