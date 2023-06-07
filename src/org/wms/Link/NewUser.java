package org.wms.Link;

import org.wms.database.data.daoFactory.DaoFactory;
import org.wms.database.data.dataType.TUserAccount;


public class NewUser {

    public static int NwUser(String Username, String Useremail, String Password, String phonenumber) {

        /*传入数据创建一个新的类*/
        TUserAccount TuserAccount = new TUserAccount();
        TuserAccount.username =Username;
        TuserAccount.email =Useremail;
        TuserAccount.password=Password;
        TuserAccount.phoneNumber=phonenumber;
        /*将用户数据上传数据库*/
        var Update = DaoFactory.GetUserAccountDao();
        return Update.InsertOneLine(TuserAccount);
    }
}