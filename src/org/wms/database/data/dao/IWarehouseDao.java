package org.wms.database.data.dao;

import org.wms.database.data.dataType.TWarehouse;

import java.util.ArrayList;

public interface IWarehouseDao {
    /**
     * 获取所有仓库信息
     *
     * @return 仓库信息链
     */
    ArrayList<TWarehouse> GetAllData();

    /**
     * 通过仓库号获取仓库信息
     *
     * @param ID 仓库好
     * @return 该仓库信息
     */
    TWarehouse GetDataByID(String ID);

    /**
     * 查询某一地址仓库信息
     *
     * @param address 地址
     * @return 该地址仓库信息链
     */
    ArrayList<TWarehouse> GetDataByAddress(String address);

    /**
     * 通过仓库号删除一行数据
     *
     * @param warehouseID 仓库号
     * @return 成功1；失败0
     */
    int DeleteDataByID(String warehouseID);

    /**
     * 通过地址删除仓库数据
     *
     * @param address 地址
     * @return 成功1；失败0
     */
    int DeleteDataByAddress(String address);

    /**
     * 插入新的仓库数据
     *
     * @param warehouse 仓库数据
     * @return 成功1；失败0
     */
    int InsertOnLine(TWarehouse warehouse);

    /**
     * 通过仓库号更新某一仓库信息
     *
     * @param warehouseID 仓库号
     * @param warehouse   更新的数据
     * @return 成功1；失败0
     */
    int UpdateOnLineByID(String warehouseID, TWarehouse warehouse);

    /**
     * 通过地址修改数据
     *
     * @param address   修改信息的仓库地址
     * @param warehouse 修改的数据
     * @return 成功1；失败0
     */
    int UpdateOnLineByAddress(String address, TWarehouse warehouse);
}
