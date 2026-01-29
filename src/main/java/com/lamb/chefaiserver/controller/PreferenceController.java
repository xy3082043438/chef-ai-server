package com.lamb.chefaiserver.controller;

import com.lamb.chefaiserver.common.Result;
import com.lamb.chefaiserver.dto.PreferenceAddRequest;
import com.lamb.chefaiserver.entity.Preference;
import com.lamb.chefaiserver.service.PreferenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户偏好管理接口。
 */
@RestController
@RequestMapping("/user/preferences")
public class PreferenceController {

    /** 偏好服务。 */
    @Autowired
    private PreferenceService preferenceService;

    /**
     * 新增用户偏好。
     *
     * @param request 请求体
     * @return 操作结果
     */
    @PostMapping("")
    public Result<Boolean> addPreference(@RequestBody PreferenceAddRequest request) {
        return preferenceService.addPreference(
                request.getUserId(),
                request.getPreferenceName()
        );
    }

    /**
     * 按偏好ID删除记录。
     *
     * @param preferenceId 偏好ID
     * @return 操作结果
     */
    @DeleteMapping("/{preferenceId}")
    public Result<Boolean> deletePreference(@PathVariable Integer preferenceId) {
        return preferenceService.deletePreference(preferenceId);
    }
    
    /**
     * 获取用户偏好列表。
     *
     * @param userId 用户ID
     * @return 偏好列表
     */
    @GetMapping("")
    public Result<List<Preference>> getPreferences(@RequestParam("user_id") Integer userId) {
        return preferenceService.getUserPreferences(userId);
    }
}
