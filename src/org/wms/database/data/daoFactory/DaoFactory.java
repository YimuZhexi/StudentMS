package org.wms.database.data.daoFactory;

import org.wms.database.data.dao.iml.*;

/**
 * 简单Dao工厂
 */
public class DaoFactory {
    /**
     * 获取账户操作类
     *
     * @return 操作对象
     */
    public static UserAccountDao GetUserAccountDao() {
        return new UserAccountDao();
    }

    /**
     * 获取员工操作类
     *
     * @return 操作对象
     */
    public static EmployeeDao GetEmployeeDao() {
        return new EmployeeDao();
    }

    /**
     * 获取仓库操作对象
     *
     * @return WarehouseDao
     */
    public static WarehouseDao GetWarehouseDao() {
        return new WarehouseDao();
    }

    /**
     * 获取零件操作对象
     *
     * @return 零件操作对象
     */
    public static PartDao GetPartDao() {
        return new PartDao();
    }

    /**
     * 获取仓储操作对象
     *
     * @return 仓储操作对象
     */
    public static PartInWarehouseDao GetPartInWarehouseDao() {
        return new PartInWarehouseDao();
    }

    /**
     * 获取供应商表操作对象
     *
     * @return 供应商表操作对象
     */
    public static ProviderDao GetProviderDao() {
        return new ProviderDao();
    }
}
