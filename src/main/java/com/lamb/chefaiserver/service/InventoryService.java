package com.lamb.chefaiserver.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lamb.chefaiserver.entity.Inventory;
import com.lamb.chefaiserver.common.Result;
import java.util.List;

/**
 * 库存领域服务。
 */
public interface InventoryService extends IService<Inventory> {
    /**
     * 添加用户库存食材。
     *
     * @param userId 用户ID
     * @param foodName 食材名称
     * @param quantity 数量
     * @return 操作结果
     */
    Result<Boolean> addInventory(Integer userId, String foodName, Integer quantity);

    /**
     * 按库存ID删除记录。
     *
     * @param inventoryId 库存ID
     * @return 操作结果
     */
    Result<Boolean> deleteInventory(Integer inventoryId);

    /**
     * 获取用户库存列表。
     *
     * @param userId 用户ID
     * @return 库存列表
     */
    Result<List<Inventory>> getUserInventory(Integer userId);
}
