package org.wms.database.data.dao.iml;

import org.wms.database.data.dao.IPartDao;
import org.wms.database.data.dataType.TPart;
import org.wms.database.data.dataname.Table;
import org.wms.database.utl.Database;

import java.sql.*;
import java.util.ArrayList;

public class PartDao implements IPartDao {
    @Override
    public ArrayList<TPart> GetAllData() {
        Connection connection = null;
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        ArrayList<TPart> parts = new ArrayList<>();
        String sql = "SELECT * FROM " + Table.Part.tableName + ";";
        try {
            connection = Database.Connect();
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            SetArrValues(preparedStatement, resultSet, parts);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Database.CloseConnect(connection);
        }
        return parts;
    }

    /**
     * 设置查询到的零件数据到数据链
     *
     * @param preparedStatement 操作集
     * @param resultSet         结果集
     * @param parts             接收的数据链
     * @throws SQLException 数据库操作错误
     */
    private void SetArrValues(PreparedStatement preparedStatement, ResultSet resultSet, ArrayList<TPart> parts) throws SQLException {
        while (resultSet.next()) {
            TPart part = new TPart();
            part.partID = resultSet.getString(Table.Part.partID);
            part.partName = resultSet.getString(Table.Part.partName);
            part.partPrice = resultSet.getDouble(Table.Part.partPrice);
            part.providerID = resultSet.getString(Table.Part.providerID);
            parts.add(part);
        }
        resultSet.close();
        preparedStatement.close();
    }

    @Override
    public TPart GetDataByID(String partID) {
        Connection connection = null;
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        TPart part = new TPart();
        String sql = "SELECT * FROM " + Table.Part.tableName
                + " WHERE " + Table.Part.partID + "=?;";
        try {
            connection = Database.Connect();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, partID);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                part.partID = resultSet.getString(Table.Part.partID);
                part.partName = resultSet.getString(Table.Part.partName);
                part.partPrice = resultSet.getDouble(Table.Part.partPrice);
                part.providerID = resultSet.getString(Table.Part.providerID);
            }
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Database.CloseConnect(connection);
        }
        return part;
    }

    @Override
    public ArrayList<TPart> GetDataByPrvID(String providerID) {
        Connection connection = null;
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        ArrayList<TPart> parts = new ArrayList<>();
        String sql = "SELECT * FROM " + Table.Part.tableName
                + " WHERE " + Table.Part.providerID + "=?;";
        try {
            connection = Database.Connect();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, providerID);
            resultSet = preparedStatement.executeQuery();
            SetArrValues(preparedStatement, resultSet, parts);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Database.CloseConnect(connection);
        }
        return parts;
    }

    @Override
    public ArrayList<TPart> GetDataByPrice(double price, int eq) {
        Connection connection = null;
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        ArrayList<TPart> parts = new ArrayList<>();
        String sql = "SELECT * FROM " + Table.Part.tableName
                + " WHERE " + Table.Part.partPrice + GetEq(eq) + "?;";
        try {
            connection = Database.Connect();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setDouble(1, price);
            resultSet = preparedStatement.executeQuery();
            SetArrValues(preparedStatement, resultSet, parts);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Database.CloseConnect(connection);
        }
        return parts;
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
    public int DeleteDataByID(String partID) {
        Connection connection = null;
        PreparedStatement preparedStatement;
        int flag = 0;
        String sql = "DELETE FROM " + Table.Part.tableName
                + " WHERE " + Table.Part.partID + "=?;";
        try {
            connection = Database.Connect();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, partID);
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
    public int DeleteDataByPrvID(String providerID) {
        Connection connection = null;
        PreparedStatement preparedStatement;
        int flag = 0;
        String sql = "DELETE FROM " + Table.Part.tableName
                + " WHERE " + Table.Part.providerID + "=?;";
        try {
            connection = Database.Connect();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, providerID);
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
    public int InsertData(TPart part) {
        Connection connection = null;
        PreparedStatement preparedStatement;
        int flag = 0;
        String sql = "INSERT INTO " + Table.Part.tableName + "("
                + Table.Part.partID + ","
                + Table.Part.partName + ","
                + Table.Part.partPrice + ","
                + Table.Part.providerID + ") "
                + "VALUES(?,?,?,?);";
        try {
            connection = Database.Connect();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, part.partID);
            preparedStatement.setString(2, part.partName);
            preparedStatement.setDouble(3, part.partPrice);
            preparedStatement.setString(4, part.providerID);
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
    public int UpdateOneLine(String partID, TPart part) {
        Connection connection = null;
        PreparedStatement preparedStatement;
        int flag = 0;
        String sql = "UPDATE " + Table.Part.tableName
                + " SET "
                + Table.Part.partID + "=?,"
                + Table.Part.partName + "=?,"
                + Table.Part.partPrice + "=?,"
                + Table.Part.providerID + "=? "
                + " WHERE " + Table.Part.partID + "=?;";
        try {
            connection = Database.Connect();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, part.partID);
            preparedStatement.setString(2, part.partName);
            preparedStatement.setDouble(3, part.partPrice);
            preparedStatement.setString(4, part.providerID);
            preparedStatement.setString(5, partID);
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
