package com.lamb.chefaiserver.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lamb.chefaiserver.entity.Preference;
import org.apache.ibatis.annotations.Mapper;

/**
 * 偏好表 Mapper。
 */
@Mapper
public interface PreferenceMapper extends BaseMapper<Preference> {
}
