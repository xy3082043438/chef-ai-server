package com.lamb.chefaiserver.controller;

import com.lamb.chefaiserver.common.Result;
import com.lamb.chefaiserver.dto.InventoryAddRequest;
import com.lamb.chefaiserver.entity.Inventory;
import com.lamb.chefaiserver.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户库存管理接口。
 */
@RestController
@RequestMapping("/user/inventory")
public class InventoryController {

    /** 库存服务。 */
    @Autowired
    private InventoryService inventoryService;

    /**
     * 添加用户库存食材。
     *
     * @param request 请求体
     * @return 操作结果
     */
    @PostMapping("")
    public Result<Boolean> addInventory(@RequestBody InventoryAddRequest request) {
        return inventoryService.addInventory(
                request.getUserId(),
                request.getFoodName(),
                request.getQuantity()
        );
    }

    /**
     * 按库存ID删除记录。
     *
     * @param inventoryId 库存ID
     * @return 操作结果
     */
    @DeleteMapping("/{inventoryId}")
    public Result<Boolean> deleteInventory(@PathVariable Integer inventoryId) {
        return inventoryService.deleteInventory(inventoryId);
    }

    /**
     * 获取用户库存列表。
     *
     * @param userId 用户ID
     * @return 库存列表
     */
    @GetMapping("")
    public Result<List<Inventory>> getInventory(@RequestParam("user_id") Integer userId) {
        return inventoryService.getUserInventory(userId);
    }
}
