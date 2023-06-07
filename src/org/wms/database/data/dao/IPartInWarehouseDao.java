package org.wms.database.data.dao;

import org.wms.database.data.dataType.TPartInWarehouse;

import java.util.ArrayList;

public interface IPartInWarehouseDao {
    /**
     * 获取仓储表所有数据
     *
     * @return 仓储数据链 ArrayList<TPartInWarehouse>
     */
    ArrayList<TPartInWarehouse> GetAllData();

    /**
     * 通过编号获取数据
     *
     * @param IDName 查询的编号字段名Table.PartInWarehouse.字段
     * @param ID     查询编号
     * @return 仓储数据链 ArrayList<TPartInWarehouse>
     */
    ArrayList<TPartInWarehouse> GetDataByID(String IDName, String ID);

    /**
     * 插入一行数据
     *
     * @param partInWarehouse 插入的数据
     * @return 1：成功；0：失败
     */
    int InsertOnLine(TPartInWarehouse partInWarehouse);

    /**
     * 通过零件编号和仓库号更新数据
     *
     * @param partID          零件编号
     * @param WarehouseID     仓库编号
     * @param partInWarehouse 更新的数据
     * @return 1：成功；0：失败
     */
    int UpdateByPW(String partID, String WarehouseID, TPartInWarehouse partInWarehouse);

    /**
     * 通过仓库编号或零件号删除数据
     *
     * @param IDName 查询的编号字段名Table.PartInWarehouse.字段
     * @param ID     编号
     * @return 1：成功；0：失败
     */
    int DeleteDataByID(String IDName, String ID);

    /**
     * 通过仓库号和零件编号删除数据
     *
     * @param partID      零件编号
     * @param warehouseID 仓库编号
     * @return 1：成功；0：失败
     */
    int DeleteByPW(String partID, String warehouseID);
}
