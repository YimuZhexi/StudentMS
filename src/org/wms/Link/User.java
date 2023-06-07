package org.wms.Link;

import org.wms.database.data.daoFactory.DaoFactory;
import org.wms.database.data.dataType.TUserAccount;

import java.util.ArrayList;

public class User {
    /*获取用户数据*/
    public static boolean Ujudge(String Inputac, String Inputpw) {
        var account = DaoFactory.GetUserAccountDao();
        /*
        获取用户的密码
        */
        TUserAccount Acc = account.GetData(Inputac);
        String passwordone = Acc.password;

        //判断用户名密码是否正确

        return Inputpw.equals(passwordone);
    }

    /*调用函数判断数据库中账号和邮箱*/
    public static boolean Nujudge(String naccount, String Nemail, String phone) {
        var account = DaoFactory.GetUserAccountDao();
        ArrayList<TUserAccount> Nus = account.GetAllData();

        /*判断是否账号和邮箱已存在*/
        for (var w : Nus) {
            String Account = w.username;
            if (naccount.equals(Account)) {
                return false;
            }
        }
        for (var w : Nus) {
            String Password = w.email;
            if (Nemail.equals(Password)) {
                return false;
            }
        }
        for (var w : Nus) {
            String phonenumber = w.phoneNumber;
            if (phone.equals(phonenumber))
                return false;
        }
        return true;
    }
}
