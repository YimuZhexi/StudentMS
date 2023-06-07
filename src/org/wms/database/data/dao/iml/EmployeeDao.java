package org.wms.database.data.dao.iml;

import org.wms.database.data.dao.IEmployeeDao;
import org.wms.database.data.dataType.TEmployee;
import org.wms.database.data.dataname.Table;
import org.wms.database.utl.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeDao implements IEmployeeDao {
    @Override
    public TEmployee GetOneData(String employeeID) {
        Connection connection = null;
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        TEmployee employee = new TEmployee();
        String sql = "SELECT * "
                + "FROM " + Table.Employee.tableName + " "
                + "WHERE " + Table.Employee.employeeID + " =?;";
        try {
            connection = Database.Connect();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, employeeID);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                SetEmployeeValue(resultSet, employee);
            }
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Database.CloseConnect(connection);
        }
        return employee;
    }

    @Override
    public ArrayList<TEmployee> GetData() {
        Connection connection = null;
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        ArrayList<TEmployee> employees = new ArrayList<>();
        String sql = "SELECT * "
                + "FROM " + Table.Employee.tableName + ";";
        try {
            connection = Database.Connect();
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            SetArrValue(preparedStatement, resultSet, employees);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Database.CloseConnect(connection);
        }
        return employees;
    }

    /**
     * 设置数据链数据
     *
     * @param preparedStatement 数据库操作对象
     * @param resultSet         结果集
     * @param employees         需要获取的数据链表
     * @throws SQLException 数据库操作错误
     */
    private void SetArrValue(PreparedStatement preparedStatement, ResultSet resultSet, ArrayList<TEmployee> employees) throws SQLException {
        while (resultSet.next()) {
            TEmployee employee = new TEmployee();
            SetEmployeeValue(resultSet, employee);
            employees.add(employee);
        }
        resultSet.close();
        preparedStatement.close();
    }

    /**
     * 设置单项数据
     *
     * @param resultSet 结果集
     * @param employee  单项数据
     * @throws SQLException 数据库操作错误
     */
    private void SetEmployeeValue(ResultSet resultSet, TEmployee employee) throws SQLException {
        employee.employeeID = resultSet.getString(Table.Employee.employeeID);
        employee.employeeAddress = resultSet.getString(Table.Employee.employeeAddress);
        employee.employeeAge = resultSet.getInt(Table.Employee.employeeAge);
        employee.employeeEmail = resultSet.getString(Table.Employee.employeeEmail);
        employee.employeeName = resultSet.getString(Table.Employee.employeeName);
        employee.employeeSalary = resultSet.getDouble(Table.Employee.employeeSalary);
        employee.workWarehouseID = resultSet.getString(Table.Employee.workWarehouse);
    }

    @Override
    public ArrayList<TEmployee> GetDataByWarehouse(String warehouseID) {
        Connection connection = null;
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        ArrayList<TEmployee> employees = new ArrayList<>();
        String sql = "SELECT * "
                + "FROM " + Table.Employee.tableName + " "
                + "WHERE " + Table.Employee.workWarehouse + " =?;";
        try {
            connection = Database.Connect();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, warehouseID);
            resultSet = preparedStatement.executeQuery();
            SetArrValue(preparedStatement, resultSet, employees);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Database.CloseConnect(connection);
        }
        return employees;
    }

    @Override
    public int UpdateValue(String employeeID, TEmployee employee) {
        Connection connection = null;
        PreparedStatement preparedStatement;
        int flag = 0;
        String sql = "UPDATE " + Table.Employee.tableName
                + " SET "
                + Table.Employee.employeeID + "=?,"
                + Table.Employee.employeeAge + "=?,"
                + Table.Employee.employeeSalary + "=?,"
                + Table.Employee.employeeName + "=?,"
                + Table.Employee.employeeAddress + "=?,"
                + Table.Employee.employeeEmail + "=?,"
                + Table.Employee.workWarehouse + "=?"
                + " WHERE " + Table.Employee.employeeID + "=?;";
        try {
            connection = Database.Connect();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, employee.employeeID);
            preparedStatement.setInt(2, employee.employeeAge);
            preparedStatement.setDouble(3, employee.employeeSalary);
            preparedStatement.setString(4, employee.employeeName);
            preparedStatement.setString(5, employee.employeeAddress);
            preparedStatement.setString(6, employee.employeeEmail);
            preparedStatement.setString(7, employee.workWarehouseID);
            preparedStatement.setString(8, employeeID);
            flag = preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Database.CloseConnect(connection);
        }
        return flag;
    }

    @Override
    public int InsertOneLine(TEmployee employee) {
        Connection connection = null;
        PreparedStatement preparedStatement;
        int flag = 0;
        String sql = "INSERT INTO " + Table.Employee.tableName
                + " VALUES(?,?,?,?,?,?,?);";
        try {
            connection = Database.Connect();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, employee.employeeID);
            preparedStatement.setInt(3, employee.employeeAge);
            preparedStatement.setDouble(4, employee.employeeSalary);
            preparedStatement.setString(2, employee.employeeName);
            preparedStatement.setString(6, employee.employeeAddress);
            preparedStatement.setString(5, employee.employeeEmail);
            preparedStatement.setString(7, employee.workWarehouseID);
            flag = preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Database.CloseConnect(connection);
        }
        return flag;
    }

    @Override
    public int DeleteOneLine(String employeeID) {
        Connection connection = null;
        PreparedStatement preparedStatement;
        String sql = "DELETE FROM " + Table.Employee.tableName
                + " WHERE " + Table.Employee.employeeID + "=?;";
        int flag = 0;
        try {
            connection = Database.Connect();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, employeeID);
            flag = preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Database.CloseConnect(connection);
        }
        return flag;
    }
}
