package org.wms.database.data.dao.iml;

import org.wms.database.data.dao.IWarehouseDao;
import org.wms.database.data.dataType.TWarehouse;
import org.wms.database.data.dataname.Table;
import org.wms.database.utl.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class WarehouseDao implements IWarehouseDao {
    @Override
    public ArrayList<TWarehouse> GetAllData() {
        ArrayList<TWarehouse> warehouses = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        String sql = "SELECT * " +
                "FROM " + Table.Warehouse.tableName + ";";
        try {
            connection = Database.Connect();
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            SetArrValues(warehouses, resultSet);
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Database.CloseConnect(connection);
        }
        return warehouses;
    }

    /**
     * 将从数据库获取的结果存入数据链
     *
     * @param warehouses 需要存入的数据链
     * @param resultSet  结果集
     * @throws SQLException 数据库错误
     */
    private void SetArrValues(ArrayList<TWarehouse> warehouses, ResultSet resultSet) throws SQLException {
        while (resultSet.next()) {
            TWarehouse warehouse = new TWarehouse();
            warehouse.warehouseID = resultSet.getString(Table.Warehouse.warehouseID);
            warehouse.warehouseAddress = resultSet.getString(Table.Warehouse.address);
            warehouse.warehouseName = resultSet.getString(Table.Warehouse.warehouseName);
            warehouses.add(warehouse);
        }
    }

    @Override
    public TWarehouse GetDataByID(String ID) {
        Connection connection = null;
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        TWarehouse warehouse = new TWarehouse();
        String sql = "SELECT * " +
                "FROM " + Table.Warehouse.tableName
                + " WHERE " + Table.Warehouse.warehouseID + "=?";
        try {
            connection = Database.Connect();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, ID);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                warehouse.warehouseName = resultSet.getString(Table.Warehouse.warehouseName);
                warehouse.warehouseAddress = resultSet.getString(Table.Warehouse.address);
                warehouse.warehouseID = resultSet.getString(Table.Warehouse.warehouseID);
            }
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Database.CloseConnect(connection);
        }
        return warehouse;
    }

    @Override
    public ArrayList<TWarehouse> GetDataByAddress(String address) {
        ArrayList<TWarehouse> warehouses = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        String sql = "SELECT * " +
                "FROM " + Table.Warehouse.tableName
                + " WHERE " + Table.Warehouse.address + "=?;";
        try {
            connection = Database.Connect();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, address);
            resultSet = preparedStatement.executeQuery();
            SetArrValues(warehouses, resultSet);
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Database.CloseConnect(connection);
        }
        return warehouses;
    }

    @Override
    public int DeleteDataByID(String warehouseID) {
        Connection connection = null;
        PreparedStatement preparedStatement;
        int flag = 0;
        String sql = "DELETE FROM " + Table.Warehouse.tableName
                + " WHERE " + Table.Warehouse.warehouseID + "=?;";
        try {
            connection = Database.Connect();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, warehouseID);
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
    public int DeleteDataByAddress(String address) {
        Connection connection = null;
        PreparedStatement preparedStatement;
        int flag = 0;
        String sql = "DELETE FROM " + Table.Warehouse.tableName
                + " WHERE " + Table.Warehouse.address + "=?;";
        try {
            connection = Database.Connect();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, address);
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
    public int InsertOnLine(TWarehouse warehouse) {
        Connection connection = null;
        PreparedStatement preparedStatement;
        int flag = 0;
        String sql = "INSERT INTO " + Table.Warehouse.tableName + "("
                + Table.Warehouse.warehouseID + ","
                + Table.Warehouse.warehouseName + ","
                + Table.Warehouse.address + ") "
                + "VALUES(?,?,?);";
        try {
            connection = Database.Connect();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, warehouse.warehouseID);
            preparedStatement.setString(2, warehouse.warehouseName);
            preparedStatement.setString(3, warehouse.warehouseAddress);
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
    public int UpdateOnLineByID(String warehouseID, TWarehouse warehouse) {
        Connection connection = null;
        PreparedStatement preparedStatement;
        int flag = 0;
        String sql = "UPDATE " + Table.Warehouse.tableName
                + " SET "
                + Table.Warehouse.warehouseID + "=?,"
                + Table.Warehouse.warehouseName + "=?,"
                + Table.Warehouse.address + "=? "
                + "WHERE " + Table.Warehouse.warehouseID + "=?;";
        try {
            connection = Database.Connect();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, warehouse.warehouseID);
            preparedStatement.setString(2, warehouse.warehouseName);
            preparedStatement.setString(3, warehouse.warehouseAddress);
            preparedStatement.setString(4, warehouseID);
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
    public int UpdateOnLineByAddress(String address, TWarehouse warehouse) {
        Connection connection = null;
        PreparedStatement preparedStatement;
        int flag = 0;
        String sql = "UPDATE " + Table.Warehouse.tableName
                + " SET "
                + Table.Warehouse.warehouseID + "=?,"
                + Table.Warehouse.warehouseName + "=?,"
                + Table.Warehouse.address + "=? "
                + "WHERE " + Table.Warehouse.address + "=?;";
        try {
            connection = Database.Connect();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, warehouse.warehouseID);
            preparedStatement.setString(2, warehouse.warehouseName);
            preparedStatement.setString(3, warehouse.warehouseAddress);
            preparedStatement.setString(4, address);
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
