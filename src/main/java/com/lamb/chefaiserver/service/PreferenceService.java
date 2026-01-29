package com.lamb.chefaiserver.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lamb.chefaiserver.entity.Preference;
import com.lamb.chefaiserver.common.Result;
import java.util.List;

/**
 * 用户偏好领域服务。
 */
public interface PreferenceService extends IService<Preference> {
    /**
     * 新增用户偏好。
     *
     * @param userId 用户ID
     * @param preferenceName 偏好名称
     * @return 操作结果
     */
    Result<Boolean> addPreference(Integer userId, String preferenceName);

    /**
     * 按偏好ID删除记录。
     *
     * @param preferenceId 偏好ID
     * @return 操作结果
     */
    Result<Boolean> deletePreference(Integer preferenceId);

    /**
     * 获取用户偏好列表。
     *
     * @param userId 用户ID
     * @return 偏好列表
     */
    Result<List<Preference>> getUserPreferences(Integer userId);
}
