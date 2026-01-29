package com.lamb.chefaiserver.controller;

import com.lamb.chefaiserver.common.Result;
import com.lamb.chefaiserver.entity.Recipe;
import com.lamb.chefaiserver.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 菜谱查询与详情接口。
 */
@RestController
public class RecipeController {

    /** 菜谱服务。 */
    @Autowired
    private RecipeService recipeService;

    /**
     * 获取用户推荐菜谱。
     *
     * @param userId 用户ID
     * @return 推荐菜谱列表
     */
    @GetMapping("/recipes/recommendations")
    public Result<List<Recipe>> getRecommendations(@RequestParam("user_id") Integer userId) {
        return recipeService.getRecommendations(userId);
    }

    /**
     * 根据菜谱ID获取详情。
     *
     * @param menuId 菜谱ID
     * @return 菜谱详情
     */
    @GetMapping("/recipe/{menuId}")
    public Result<Map<String, Object>> getRecipeDetail(@PathVariable Integer menuId) {
        return recipeService.getRecipeDetail(menuId);
    }

    /**
     * 获取用户历史菜谱（按时间倒序）。
     *
     * @param userId 用户ID
     * @param limit 返回条数（可选，默认 20）
     * @return 历史菜谱列表（含图片地址）
     */
    @GetMapping("/recipes/history")
    public Result<List<Map<String, Object>>> getHistory(@RequestParam("user_id") Integer userId,
                                                        @RequestParam(value = "limit", required = false) Integer limit) {
        return recipeService.getHistory(userId, limit);
    }
}
