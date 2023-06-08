package org.wms.database.data.dataType;

/**
 * 员工数据类
 */
public class TEmployee {
    /**
     * 员工编号
     */
    public String employeeID;
    /**
     * 员工姓名
     */
    public String employeeName;
    /**
     * 员工年龄
     */
    public int employeeAge;
    /**
     * 员工薪资
     */
    public double employeeSalary;
    /**
     * 员工邮箱
     */
    public String employeeEmail;
    /**
     * 员工住址
     */
    public String employeeAddress;
    /**
     * 员工工作仓库编号
     */
    public String workWarehouseID;

    public TEmployee() {
    }

    /**
     * 员工数据初始化
     *
     * @param employeeID      员工ID varchar(10)PRIMARY KEY
     * @param employeeName    员工名 varchar(10) NOT NULL
     * @param employeeAge     员工年龄 integer
     * @param employeeSalary  员工薪资 decimal(10,2)
     * @param employeeEmail   员工邮箱 varchar(50)
     * @param employeeAddress 员工地址 varchar(20)
     * @param workWarehouseID 工作仓库仓库号 varchar(10)
     */
    public TEmployee(String employeeID, String employeeName, int employeeAge, double employeeSalary, String employeeEmail, String employeeAddress, String workWarehouseID) {
        this.employeeID = employeeID;
        this.employeeName = employeeName;
        this.employeeAge = employeeAge;
        this.employeeSalary = employeeSalary;
        this.employeeEmail = employeeEmail;
        this.employeeAddress = employeeAddress;
        this.workWarehouseID = workWarehouseID;
    }
}
