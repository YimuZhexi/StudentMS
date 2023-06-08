package org.wms.Link;

import org.wms.database.data.daoFactory.DaoFactory;

public class User {
    /*调用函数判断数据库中账号和邮箱*/
    public static boolean NuJudge(String nAccount) {
        var account = DaoFactory.GetUserAccountDao();
        var user=account.GetData(nAccount);
        return user.username == null;
    }
}
