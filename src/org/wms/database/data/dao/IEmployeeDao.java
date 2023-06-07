package org.wms.database.data.dao;

import org.wms.database.data.dataType.TEmployee;

import java.util.ArrayList;

/**
 * Employee表操作类接口
 */
public interface IEmployeeDao {
    /**
     * 获取通过员工ID获取Employee表的数据
     *
     * @param employeeID 员工ID
     * @return 一个员工数据
     */
    TEmployee GetOneData(String employeeID);

    /**
     * 获取Employee表所有数据
     *
     * @return TEmployee数据列表
     */
    ArrayList<TEmployee> GetData();

    /**
     * 通过仓库号获取员工信息
     *
     * @param warehouseID 仓库号
     * @return 该仓库所有员工信息
     */
    ArrayList<TEmployee> GetDataByWarehouse(String warehouseID);

    /**
     * 通过员工ID修改该员工信息
     *
     * @param employeeID 员工ID
     * @param employee   修改后的信息
     * @return 成功：1；失败：0
     */
    int UpdateValue(String employeeID, TEmployee employee);

    /**
     * 插入一行新员工信息
     *
     * @param employee 插入的员工信息
     * @return 成功：1；失败：0
     */
    int InsertOneLine(TEmployee employee);

    /**
     * 删除员工信息
     *
     * @param employeeID 需要删除的员工ID
     * @return 成功：1；失败：0
     */
    int DeleteOneLine(String employeeID);
}
