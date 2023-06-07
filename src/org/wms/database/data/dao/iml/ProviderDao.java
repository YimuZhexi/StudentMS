package org.wms.database.data.dao.iml;

import org.wms.database.data.dao.IProviderDao;
import org.wms.database.data.dataType.TProvider;
import org.wms.database.data.dataname.Table;
import org.wms.database.utl.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProviderDao implements IProviderDao {
    @Override
    public ArrayList<TProvider> GetAllData() {
        ArrayList<TProvider> providers = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        String sql = "SELECT * FROM " + Table.Provider.tableName + ";";
        try {
            connection = Database.Connect();
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                TProvider provider = new TProvider();
                provider.providerID = resultSet.getString(Table.Provider.providerID);
                provider.providerName = resultSet.getString(Table.Provider.providerName);
                provider.providerAddress = resultSet.getString(Table.Provider.providerAddress);
                provider.providerEmail = resultSet.getString(Table.Provider.providerEmail);
                providers.add(provider);
            }
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Database.CloseConnect(connection);
        }
        return providers;
    }

    @Override
    public TProvider GetDataByProviderID(String providerID) {
        TProvider provider = new TProvider();
        Connection connection = null;
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        String sql = "SELECT * FROM " + Table.Provider.tableName
                + " WHERE " + Table.Provider.providerID + "=?;";
        try {
            connection = Database.Connect();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, providerID);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                provider.providerID = resultSet.getString(Table.Provider.providerID);
                provider.providerName = resultSet.getString(Table.Provider.providerName);
                provider.providerAddress = resultSet.getString(Table.Provider.providerAddress);
                provider.providerEmail = resultSet.getString(Table.Provider.providerEmail);
            }
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Database.CloseConnect(connection);
        }
        return provider;
    }

    @Override
    public int InsertOneLine(TProvider provider) {
        Connection connection = null;
        PreparedStatement preparedStatement;
        int flag = 0;
        String sql = "INSERT INTO " + Table.Provider.tableName + "("
                + Table.Provider.providerID + ","
                + Table.Provider.providerName + ","
                + Table.Provider.providerAddress + ","
                + Table.Provider.providerEmail + ") "
                + "VALUES(?,?,?,?);";
        try {
            connection = Database.Connect();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, provider.providerID);
            preparedStatement.setString(2, provider.providerName);
            preparedStatement.setString(3, provider.providerAddress);
            preparedStatement.setString(4, provider.providerEmail);
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
    public int DeleteByProviderID(String providerID) {
        Connection connection = null;
        PreparedStatement preparedStatement;
        int flag = 0;
        String sql = "DELETE FROM " + Table.Provider.tableName
                + " WHERE " + Table.Provider.providerID + "=?;";
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
    public int UpdateByProviderID(String providerID, TProvider provider) {
        Connection connection = null;
        PreparedStatement preparedStatement;
        int flag = 0;
        String sql = "UPDATE " + Table.Provider.tableName
                + " SET "
                + Table.Provider.providerID + "=?,"
                + Table.Provider.providerName + "=?,"
                + Table.Provider.providerAddress + "=?,"
                + Table.Provider.providerEmail + "=? "
                + "WHERE " + Table.Provider.providerID + "=?;";
        try {
            connection = Database.Connect();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, provider.providerID);
            preparedStatement.setString(2, provider.providerName);
            preparedStatement.setString(3, provider.providerAddress);
            preparedStatement.setString(4, provider.providerEmail);
            preparedStatement.setString(5, providerID);
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
