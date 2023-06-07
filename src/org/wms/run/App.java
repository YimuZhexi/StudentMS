package org.wms.run;

import org.wms.Frame.Welcome;

public class App {
    public App() {
        new Welcome();

        /*登录操作:
        条件判断语句,判断是否成功登录
            如果成功登录:new Notice("您已成功登录");
                       new WarehouseList();
            如果登录失败:new Notice("账号或密码有误");
        */
    }
}
