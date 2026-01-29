package com.lamb.chefaiserver.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lamb.chefaiserver.common.Result;
import com.lamb.chefaiserver.entity.Preference;
import com.lamb.chefaiserver.mapper.PreferenceMapper;
import com.lamb.chefaiserver.service.PreferenceService;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * 偏好服务实现（基于 MyBatis-Plus）。
 */
@Service
public class PreferenceServiceImpl extends ServiceImpl<PreferenceMapper, Preference> implements PreferenceService {

    /** {@inheritDoc} */
    @Override
    public Result<Boolean> addPreference(Integer userId, String preferenceName) {
        Preference preference = new Preference();
        preference.setUserId(userId);
        preference.setPreferenceName(preferenceName);
        boolean success = this.save(preference);
        return success ? Result.success(true) : Result.error("添加偏好失败");
    }

    /** {@inheritDoc} */
    @Override
    public Result<Boolean> deletePreference(Integer preferenceId) {
        boolean success = this.removeById(preferenceId);
        return success ? Result.success(true) : Result.error("删除偏好失败");
    }

    /** {@inheritDoc} */
    @Override
    public Result<List<Preference>> getUserPreferences(Integer userId) {
        List<Preference> list = this.list(new LambdaQueryWrapper<Preference>().eq(Preference::getUserId, userId));
        return Result.success(list);
    }
}
