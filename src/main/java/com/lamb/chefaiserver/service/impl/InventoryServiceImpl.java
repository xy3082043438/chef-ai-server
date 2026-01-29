package com.lamb.chefaiserver.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lamb.chefaiserver.common.Result;
import com.lamb.chefaiserver.entity.Inventory;
import com.lamb.chefaiserver.mapper.InventoryMapper;
import com.lamb.chefaiserver.service.InventoryService;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * 库存服务实现（基于 MyBatis-Plus）。
 */
@Service
public class InventoryServiceImpl extends ServiceImpl<InventoryMapper, Inventory> implements InventoryService {

    /** {@inheritDoc} */
    @Override
    public Result<Boolean> addInventory(Integer userId, String foodName, Integer quantity) {
        Inventory inventory = new Inventory();
        inventory.setUserId(userId);
        inventory.setFoodName(foodName);
        inventory.setQuantity(quantity);
        boolean success = this.save(inventory);
        return success ? Result.success(true) : Result.error("添加库存失败");
    }

    /** {@inheritDoc} */
    @Override
    public Result<Boolean> deleteInventory(Integer inventoryId) {
        boolean success = this.removeById(inventoryId);
        return success ? Result.success(true) : Result.error("删除库存失败");
    }

    /** {@inheritDoc} */
    @Override
    public Result<List<Inventory>> getUserInventory(Integer userId) {
        List<Inventory> list = this.list(new LambdaQueryWrapper<Inventory>().eq(Inventory::getUserId, userId));
        return Result.success(list);
    }
}
