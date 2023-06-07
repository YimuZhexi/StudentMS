package org.wms.database.data.dao.iml;

import org.wms.database.utl.Database;
import org.wms.database.data.dao.IUserAccountDao;
import org.wms.database.data.dataType.TUserAccount;
import org.wms.database.data.dataname.Table;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserAccountDao implements IUserAccountDao {
    @Override
    public ArrayList<TUserAccount> GetAllData() {
        Connection connection = null;//创建连接对象
        ResultSet resultSet;//创建结果集
        PreparedStatement preparedStatement;//创建执行对象
        String sql = "SELECT * FROM " + Table.UserAccount.tableName + ";";//定义SQL语句
        ArrayList<TUserAccount> accounts = new ArrayList<>();//创建返回链表
        try {
            connection = Database.Connect();//建立连接
            preparedStatement = connection.prepareStatement(sql);//预编译语句
            resultSet = preparedStatement.executeQuery();//执行并获取结果
            //将结果存入accounts
            while (resultSet.next()) {
                TUserAccount account = new TUserAccount();
                account.username = resultSet.getString(Table.UserAccount.userName);
                account.password = resultSet.getString(Table.UserAccount.password);
                account.email = resultSet.getString(Table.UserAccount.email);
                account.phoneNumber = resultSet.getString(Table.UserAccount.phoneNumber);
                accounts.add(account);
            }
            //关闭执行对象和结果集
            preparedStatement.close();
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //关闭链接
            Database.CloseConnect(connection);
        }
        //返回结果
        return accounts;
    }

    @Override
    public TUserAccount GetData(String userName) {
        Connection connection = null;//创建连接对象
        ResultSet resultSet;//创建结果集
        PreparedStatement preparedStatement;//创建执行对象
        String sql = "SELECT * FROM " + Table.UserAccount.tableName + " WHERE UserName=?;";
        TUserAccount account = new TUserAccount();
        try {
            connection = Database.Connect();//建立连接
            preparedStatement = connection.prepareStatement(sql);//预编译语句
            //设置占位符
            preparedStatement.setString(1, userName);
            resultSet = preparedStatement.executeQuery();//执行并获取结果
            //将结果存入accounts
            while (resultSet.next()) {
                account.username = resultSet.getString(Table.UserAccount.userName);
                account.password = resultSet.getString(Table.UserAccount.password);
                account.email = resultSet.getString(Table.UserAccount.email);
                account.phoneNumber = resultSet.getString(Table.UserAccount.phoneNumber);
            }
            //关闭执行对象和结果集
            preparedStatement.close();
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //关闭链接
            Database.CloseConnect(connection);
        }
        return account;
    }

    @Override
    public int UpdateValue(String userName, TUserAccount userAccount) {
        Connection connection = null;//创建连接对象
        PreparedStatement preparedStatement;//创建执行对象
        String sql = "UPDATE " + Table.UserAccount.tableName
                + " SET " + Table.UserAccount.userName + "=?,"
                + Table.UserAccount.password + "=?,"
                + Table.UserAccount.email + "=?,"
                + Table.UserAccount.phoneNumber + "=?"
                + " WHERE UserName=?";
        int flag = 0;
        try {
            connection = Database.Connect();
            preparedStatement = connection.prepareStatement(sql);
            //设置占位符
            preparedStatement.setString(1, userAccount.username);
            preparedStatement.setString(2, userAccount.password);
            preparedStatement.setString(3, userAccount.email);
            preparedStatement.setString(4, userAccount.phoneNumber);
            preparedStatement.setString(5, userName);
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
    public int InsertOneLine(TUserAccount dataLine) {
        Connection connection = null;//创建连接对象
        PreparedStatement preparedStatement;//创建执行对象
        String sql = "INSERT INTO " + Table.UserAccount.tableName +
                "(" + Table.UserAccount.userName + ","
                + Table.UserAccount.password + ","
                + Table.UserAccount.email + ","
                + Table.UserAccount.phoneNumber + ")"
                + " VALUES(?,?,?,?)";
        int flag = 0;
        try {
            connection = Database.Connect();
            preparedStatement = connection.prepareStatement(sql);
            //设置占位符
            preparedStatement.setString(1, dataLine.username);
            preparedStatement.setString(2, dataLine.password);
            preparedStatement.setString(3, dataLine.email);
            preparedStatement.setString(4, dataLine.phoneNumber);
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
    public int DeleteOneLine(String userName) {
        Connection connection = null;//创建连接对象
        PreparedStatement preparedStatement;//创建执行对象
        int flag = 0;
        String sql = "DELETE FROM " + Table.UserAccount.tableName
                + " WHERE " + Table.UserAccount.userName + "=?";
        try {
            connection = Database.Connect();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, userName);
            boolean f = preparedStatement.execute();
            flag = f ? 1 : 0;
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Database.CloseConnect(connection);
        }
        return flag;
    }
}
