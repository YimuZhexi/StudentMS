package org.wms;

import org.wms.database.data.daoFactory.DaoFactory;
import org.wms.database.data.dataType.*;
import org.wms.database.data.dataname.Table;

import java.util.ArrayList;

public class Test {
    public static void main(String[] args) {
        //获取各种Dao对象
        var employeeDao = DaoFactory.GetEmployeeDao();
        var partDao = DaoFactory.GetPartDao();
        var partInWarehouseDao = DaoFactory.GetPartInWarehouseDao();
        var providerDao = DaoFactory.GetProviderDao();
        var userAccountDao = DaoFactory.GetUserAccountDao();
        var warehouseDao = DaoFactory.GetWarehouseDao();

        //获取账户表的数据
        System.out.println(Table.UserAccount.tableName);
        var users = userAccountDao.GetAllData();
        PrintUserAccount(users);
        //向账户表添加一个元素
        TUserAccount userAccount = new TUserAccount(
                "manager",
                "zxcvbnm",
                "454648@outlook.com",
                "13333333333"
        );
        System.out.println("添加数据到" + Table.UserAccount.tableName + ":");
        int flag = userAccountDao.InsertOneLine(userAccount);
        //输出是否成功
        System.out.println(flag == 1 ? "成功" : "失败");
        //输出添加后的表
        System.out.println("增加后的" + Table.UserAccount.tableName);
        users = userAccountDao.GetAllData();
        PrintUserAccount(users);

        //打印零件表数据和仓储表
        System.out.println(Table.Part.tableName);
        var parts = partDao.GetAllData();
        PrintPart(parts);
        System.out.println(Table.PartInWarehouse.tableName);
        var partInWarehouses = partInWarehouseDao.GetAllData();
        PrintPartInWarehouse(partInWarehouses);
        //删除零件表中数据
        System.out.println("删除零件p006并且清除PartInWarehouse中对应数据");
        flag = partDao.DeleteDataByID("p006");
        System.out.println("删除零件：" + (flag == 1 ? "成功" : "失败"));
        //删除仓储表数据
        flag = partInWarehouseDao.DeleteDataByID(Table.PartInWarehouse.partID, "p006");
        System.out.println("删除仓储表数据：" + (flag == 1 ? "成功" : "失败"));
        //再次打印数据
        System.out.println(Table.Part.tableName);
        parts = partDao.GetAllData();
        PrintPart(parts);
        System.out.println(Table.PartInWarehouse.tableName);
        partInWarehouses = partInWarehouseDao.GetAllData();
        PrintPartInWarehouse(partInWarehouses);

        //获取并打印仓库表数据
        System.out.println(Table.Warehouse.tableName);
        var warehouses = warehouseDao.GetAllData();
        PrintWarehouse(warehouses);
        //修改WH5仓库名为"上海Φ存储仓库"
        TWarehouse warehouse = warehouseDao.GetDataByID("WH5");
        warehouse.warehouseName = "上海Φ存储仓库";
        flag = warehouseDao.UpdateOnLineByID("WH5", warehouse);
        System.out.println("更新数据：" + (flag == 1 ? "成功" : "失败"));
        //答应修改后的数据
        warehouses = warehouseDao.GetAllData();
        PrintWarehouse(warehouses);

        //获取并打印员工表数据
        System.out.println(Table.Employee.tableName);
        var employees = employeeDao.GetData();
        PrintEmployee(employees);
        //删除员工张三
        flag = employeeDao.DeleteOneLine("e001");
        System.out.println("删除：" + (flag == 1 ? "成功" : "失败"));
        //再次打印员工表数据
        employees = employeeDao.GetData();
        PrintEmployee(employees);

        //获取并打印共供应商表数据
        System.out.println(Table.Provider.tableName);
        var providers = providerDao.GetAllData();
        PrintProvider(providers);
        //查找编号为pr001的供应商姓名
        var provider = providerDao.GetDataByProviderID("pr001");
        System.out.println("pr001为：" + provider.providerName);
    }

    //打印供应商信息
    private static void PrintProvider(ArrayList<TProvider> providers) {
        for (var provider : providers) {
            System.out.println(provider.providerID + "\t\t"
                    + provider.providerName + "\t\t"
                    + provider.providerEmail + "\t\t"
                    + provider.providerAddress);
        }
    }

    //打印仓储信息
    private static void PrintPartInWarehouse(ArrayList<TPartInWarehouse> partInWarehouses) {
        for (var part : partInWarehouses) {
            System.out.println(part.partID + "\t\t"
                    + part.warehouseID + "\t\t"
                    + part.partNum);
        }
    }

    //打印账户表信息
    private static void PrintUserAccount(ArrayList<TUserAccount> userAccounts) {
        for (var user : userAccounts) {
            System.out.println(user.username + "\t\t"
                    + user.password + "\t\t"
                    + user.email + "\t\t"
                    + user.phoneNumber);
        }
    }

    //打印零件表信息
    private static void PrintPart(ArrayList<TPart> parts) {
        for (var part : parts) {
            System.out.println(part.partID + "\t\t"
                    + part.partName + "\t\t"
                    + part.partPrice + "\t\t"
                    + part.providerID);
        }
    }

    //打印仓库表信息
    private static void PrintWarehouse(ArrayList<TWarehouse> warehouses) {
        for (var warehouse : warehouses) {
            System.out.println(warehouse.warehouseID + "\t\t"
                    + warehouse.warehouseName + "\t\t"
                    + warehouse.warehouseAddress);
        }
    }

    //打印员工表信息
    private static void PrintEmployee(ArrayList<TEmployee> employees) {
        for (var employee : employees) {
            System.out.println(employee.employeeID + "\t\t"
                    + employee.employeeName + "\t\t"
                    + employee.employeeAge + "\t\t"
                    + employee.employeeAddress + "\t\t"
                    + employee.employeeEmail + "\t\t"
                    + employee.employeeSalary + "\t\t"
                    + employee.workWarehouseID);
        }
    }
}