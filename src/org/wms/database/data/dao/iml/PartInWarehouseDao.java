package org.wms.database.data.dao.iml;

import org.wms.database.data.dao.IPartInWarehouseDao;
import org.wms.database.data.dataType.TPartInWarehouse;
import org.wms.database.data.dataname.Table;
import org.wms.database.utl.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PartInWarehouseDao implements IPartInWarehouseDao {
    @Override
    public ArrayList<TPartInWarehouse> GetAllData() {
        Connection connection = null;
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        ArrayList<TPartInWarehouse> partInWarehouses = new ArrayList<>();
        String sql = "SELECT * FROM " + Table.PartInWarehouse.tableName + ";";
        try {
            connection = Database.Connect();
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            SetPartValue(resultSet, partInWarehouses);
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Database.CloseConnect(connection);
        }
        return partInWarehouses;
    }

    /**
     * 将从数据库获取的值写入到数据链
     *
     * @param resultSet        结果集
     * @param partInWarehouses 需要写入的数据链
     * @throws SQLException 数据库错误
     */
    private void SetPartValue(ResultSet resultSet, ArrayList<TPartInWarehouse> partInWarehouses) throws SQLException {
        while (resultSet.next()) {
            TPartInWarehouse part = new TPartInWarehouse();
            part.partID = resultSet.getString(Table.PartInWarehouse.partID);
            part.warehouseID = resultSet.getString(Table.PartInWarehouse.warehouseID);
            part.partNum = resultSet.getInt(Table.PartInWarehouse.partNum);
            partInWarehouses.add(part);
        }
    }

    @Override
    public ArrayList<TPartInWarehouse> GetDataByID(String IDName, String ID) {
        Connection connection = null;
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        ArrayList<TPartInWarehouse> partInWarehouses = new ArrayList<>();
        String sql = "SELECT * FROM " + Table.PartInWarehouse.tableName
                + " WHERE " + IDName + "=?;";
        try {
            connection = Database.Connect();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, ID);
            resultSet = preparedStatement.executeQuery();
            SetPartValue(resultSet, partInWarehouses);
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Database.CloseConnect(connection);
        }
        return partInWarehouses;
    }

    /**
     * 获取大小比较运算符号
     *
     * @param eq 大小标识
     * @return 数据符号>或<或=
     */
    private char GetEq(int eq) {
        char s = '=';
        switch (eq) {
            case 1 -> s = '>';
            case 0 -> s = '=';
            case -1 -> s = '<';
        }
        return s;
    }

    @Override
    public int InsertOnLine(TPartInWarehouse partInWarehouse) {
        Connection connection = null;
        PreparedStatement preparedStatement;
        int flag = 0;
        String sql = "INSERT INTO " + Table.PartInWarehouse.tableName + "("
                + Table.PartInWarehouse.partID + ","
                + Table.PartInWarehouse.warehouseID + ","
                + Table.PartInWarehouse.partNum + ") "
                + "VALUES(?,?,?);";
        try {
            connection = Database.Connect();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, partInWarehouse.partID);
            preparedStatement.setString(2, partInWarehouse.warehouseID);
            preparedStatement.setInt(3, partInWarehouse.partNum);
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
    public int UpdateByPW(String partID, String WarehouseID, TPartInWarehouse partInWarehouse) {
        Connection connection = null;
        PreparedStatement preparedStatement;
        int flag = 0;
        String sql = "UPDATE " + Table.PartInWarehouse.tableName
                + " SET "
                + Table.PartInWarehouse.partID + "=?,"
                + Table.PartInWarehouse.warehouseID + "=?,"
                + Table.PartInWarehouse.partNum + "=? "
                + "WHERE " + Table.PartInWarehouse.partID + "=? "
                + "AND " + Table.PartInWarehouse.warehouseID + "=?;";
        try {
            connection = Database.Connect();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, partInWarehouse.partID);
            preparedStatement.setString(2, partInWarehouse.warehouseID);
            preparedStatement.setInt(3, partInWarehouse.partNum);
            preparedStatement.setString(4, partID);
            preparedStatement.setString(5, WarehouseID);
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
    public int DeleteDataByID(String IDName, String ID) {
        Connection connection = null;
        PreparedStatement preparedStatement;
        int flag = 0;
        String sql = "DELETE FROM " + Table.PartInWarehouse.tableName
                + " WHERE " + IDName + "=?;";
        try {
            connection = Database.Connect();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, ID);
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
    public int DeleteByPW(String partID, String warehouseID) {
        Connection connection = null;
        PreparedStatement preparedStatement;
        int flag = 0;
        String sql = "DELETE FROM " + Table.PartInWarehouse.tableName
                + " WHERE " + Table.PartInWarehouse.warehouseID + "=? "
                + "AND " + Table.PartInWarehouse.partID + "=?;";
        try {
            connection = Database.Connect();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, warehouseID);
            preparedStatement.setString(2, partID);
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
