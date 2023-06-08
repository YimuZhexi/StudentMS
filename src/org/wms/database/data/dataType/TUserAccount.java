package org.wms.database.data.dataType;

/**
 * 账户数据类
 */
public class TUserAccount {
    /**
     * 登录用户名
     */
    public String username;
    /**
     * 登陆密码
     */
    public String password;
    /**
     * 账户邮箱
     */
    public String email;
    /**
     * 电话号码
     */
    public String phoneNumber;

    public TUserAccount() {
    }

    /**
     * 账户数据类构造
     *
     * @param username    用户名
     * @param password    密码
     * @param email       邮箱
     * @param phoneNumber 电话
     */
    public TUserAccount(String username, String password, String email, String phoneNumber) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }
}
