package com.lamb.chefaiserver.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lamb.chefaiserver.entity.Recipe;
import com.lamb.chefaiserver.common.Result;
import java.util.List;
import java.util.Map;

/**
 * 菜谱领域服务。
 */
public interface RecipeService extends IService<Recipe> {
    /**
     * 获取用户推荐菜谱。
     *
     * @param userId 用户ID
     * @return 推荐菜谱列表
     */
    Result<List<Recipe>> getRecommendations(Integer userId);

    /**
     * 根据菜谱ID获取详情。
     *
     * @param menuId 菜谱ID
     * @return 菜谱详情
     */
    Result<Map<String, Object>> getRecipeDetail(Integer menuId);

    /**
     * 获取用户历史菜谱（按时间倒序）。
     *
     * @param userId 用户ID
     * @param limit 返回条数上限
     * @return 历史菜谱列表（含图片地址）
     */
    Result<List<Map<String, Object>>> getHistory(Integer userId, Integer limit);
}
