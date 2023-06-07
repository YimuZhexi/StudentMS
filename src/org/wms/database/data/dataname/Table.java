package org.wms.database.data.dataname;

/**
 * 表名存储数据类，便于对表的增删查改
 */
public class Table {
    /**
     * 登陆系统账户信息表
     */
    public static class UserAccount {
        /**
         * 表名
         */
        public static final String tableName = "UserAccount";
        /**
         * 用户名
         * 不可为空
         */
        public static final String userName = "UserName";
        /**
         * 密码
         * 不可为空
         */
        public static final String password = "Pass";
        /**
         * 邮箱
         * 最大50个字符
         */
        public static final String email = "Email";
        /**
         * 电话
         * 不可为空
         */
        public static final String phoneNumber = "PhoneNumber";

        /**
         * 获取账户表按数据库建表顺序的字段名
         *
         * @return 所有字段名
         */
        public static String[] GetColumnName() {
            return new String[]{
                    userName, password, email, phoneNumber
            };
        }
    }

    /**
     * 员工数据表
     */
    public static class Employee {
        /**
         * 员工表表名
         */
        public static final String tableName = "Employee";
        /**
         * 员工id
         */
        public static final String employeeID = "EmployeeID";
        /**
         * 员工年龄
         */
        public static final String employeeAge = "EmployeeAge";
        /**
         * 员工薪资
         */
        public static final String employeeSalary = "EmployeeSalary";
        /**
         * 员工名
         */
        public static final String employeeName = "EmployeeName";
        /**
         * 员工邮箱
         */
        public static final String employeeEmail = "EmployeeEmail";
        /**
         * 员工住址
         */
        public static final String employeeAddress = "EmployeeAddress";
        /**
         * 员工工作仓库
         */
        public static final String workWarehouse = "WorkWarehouseID";

        /**
         * 获取员工表按数据库建表顺序的字段名
         *
         * @return 所有字段名
         */
        public static String[] GetColumnName() {
            return new String[]{
                    employeeID, employeeName, employeeAge, employeeSalary, employeeEmail, employeeAddress, workWarehouse
            };
        }
    }

    /**
     * 仓库数据表
     */
    public static class Warehouse {
        /**
         * 仓库表名
         */
        public static final String tableName = "Warehouse";
        /**
         * 仓库ID字段名
         */
        public static final String warehouseID = "WarehouseID";
        /**
         * 仓库名字段名
         */
        public static final String warehouseName = "WarehouseName";
        /**
         * 仓库地址字段名
         */
        public static final String address = "WarehouseAddress";

        /**
         * 获取仓库表按数据库建表顺序的字段名
         *
         * @return 所有字段名
         */
        public static String[] GetColumnName() {
            return new String[]{
                    warehouseID, warehouseName, address
            };
        }
    }

    /**
     * 零件数据表
     */
    public static class Part {
        /**
         * 表名
         */
        public static final String tableName = "Part";
        /**
         * 零件编号字段名
         */
        public static final String partID = "PartID";
        /**
         * 零件名称字段名
         */
        public static final String partName = "PartName";
        /**
         * 另加价格字段名
         */
        public static final String partPrice = "PartPrice";
        /**
         * 供应商编号字段名
         */
        public static final String providerID = "ProviderID";

        /**
         * 获取零件表按数据库建表顺序的字段名
         *
         * @return 所有字段名
         */
        public static String[] GetColumnName() {
            return new String[]{
                    partID, partName, partPrice, providerID
            };
        }

        /**
         * 获取零件表按数据库建表顺序的字段名
         *
         * @return 所有字段名
         */
        public static String[] GetColumnName1() {
            return new String[]{
                    partID, partName, partPrice, providerID, PartInWarehouse.partNum
            };
        }
    }

    /**
     * 零件和仓库连接表
     */
    public static class PartInWarehouse {
        /**
         * 表名
         */
        public static final String tableName = "PartInWarehouse";
        /**
         * 零件数量
         */
        public static final String partNum = "PartNum";
        /**
         * 零件编号
         */
        public static final String partID = "PartID";
        /**
         * 仓库编号
         */
        public static final String warehouseID = "WarehouseID";

        /**
         * 获取连接表按数据库建表顺序的字段名
         *
         * @return 所有字段名
         */
        public static String[] GetColumnName() {
            return new String[]{
                    partID,
                    partNum,
                    warehouseID
            };
        }
    }

    public static class Provider {
        /**
         * 表名
         */
        public static final String tableName = "Provider";
        /**
         * 供应商姓名字段名
         */
        public static final String providerName = "ProviderName";
        /**
         * 供应商编号字段名
         */
        public static final String providerID = "ProviderID";
        /**
         * 供应商地址字段名
         */
        public static final String providerAddress = "ProviderAddress";
        /**
         * 供应商邮箱字段名
         */
        public static final String providerEmail = "ProviderEmail";

        /**
         * 获取供应商表按数据库建表顺序的字段名
         *
         * @return 所有字段名
         */
        public static String[] GetColumnName() {
            return new String[]{
                    providerID,
                    providerName,
                    providerAddress,
                    providerEmail
            };
        }
    }
}
