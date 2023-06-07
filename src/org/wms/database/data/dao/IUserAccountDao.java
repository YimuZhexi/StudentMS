package org.wms.database.data.dao;

import org.wms.database.data.dataType.TUserAccount;

import java.util.ArrayList;

/**
 * 用户表操作接口
 */
public interface IUserAccountDao {
    /**
     * 获取账户表所有数据
     *
     * @return 当前表所有数据
     */
    ArrayList<TUserAccount> GetAllData();

    /**
     * 获取单行数据
     *
     * @param userName 用户名UserName
     * @return 该行数据
     */
    TUserAccount GetData(String userName);

    /**
     * 更新表中同一行的某一个值
     *
     * @param userName 更改的数据标识（键）
     * @param userAccount 修改后的数据
     * @return 成功更改1，更改失败0
     */
    int UpdateValue(String userName, TUserAccount userAccount);

    /**
     * 插入一行数据
     *
     * @param dataLine 插入的数据行
     * @return 插入成功1，失败0
     */
    int InsertOneLine(TUserAccount dataLine);

    /**
     * 删除一行数据
     *
     * @param userName 需要删除用户数据的用户名
     * @return 成功删除1，失败0
     */
    int DeleteOneLine(String userName);
}
