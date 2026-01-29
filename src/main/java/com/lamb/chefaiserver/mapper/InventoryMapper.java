package com.lamb.chefaiserver.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lamb.chefaiserver.entity.Inventory;
import org.apache.ibatis.annotations.Mapper;

/**
 * 库存表 Mapper。
 */
@Mapper
public interface InventoryMapper extends BaseMapper<Inventory> {
}
