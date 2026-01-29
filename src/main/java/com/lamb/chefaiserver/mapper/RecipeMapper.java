package com.lamb.chefaiserver.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lamb.chefaiserver.entity.Recipe;
import org.apache.ibatis.annotations.Mapper;

/**
 * 菜谱表 Mapper。
 */
@Mapper
public interface RecipeMapper extends BaseMapper<Recipe> {
}
