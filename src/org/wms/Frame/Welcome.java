package org.wms.Frame;

import org.wms.database.data.dao.IUserAccountDao;
import org.wms.database.data.dao.iml.UserAccountDao;

import javax.swing.*;
import java.awt.*;

public class Welcome extends JFrame{
    public Welcome(){
        JFrame frame = new JFrame("仓库管理系统");
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 使窗口默认屏幕居中
        int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
        int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
        int windowWidth = frame.getWidth();
        int windowHeight = frame.getHeight();
        frame.setLocation((screenWidth - windowWidth) / 2, (screenHeight - windowHeight) / 2);

        // 固定窗口大小
        frame.setResizable(false);

        // 使用JLayeredPane控制层次
        JLayeredPane jp = new JLayeredPane();
        jp.setLayout(null);

        // 添加背景图片
        ImageIcon img = new ImageIcon("images/1.png");//在此处输入图片路径
        Image imgScaled = img.getImage().getScaledInstance(windowWidth, windowHeight, Image.SCALE_SMOOTH);
        JLabel label = new JLabel(new ImageIcon(imgScaled));
        label.setBounds(0, 0, windowWidth, windowHeight);
        jp.add(label, JLayeredPane.DEFAULT_LAYER);

        // 标题文本
        JLabel title = new JLabel("欢迎来到仓库管理系统,请登录...!");
        title.setFont(new Font("微软雅黑", Font.PLAIN, 25));
        title.setForeground(Color.CYAN);
        title.setBounds(120, 40, 600, 40);
        jp.add(title,JLayeredPane.DRAG_LAYER);

        // 账号
        JLabel userLabel = new JLabel("账 号:");
        userLabel.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        userLabel.setForeground(Color.black);
        userLabel.setBounds(160, 120, 80, 30);
        jp.add(userLabel,JLayeredPane.DRAG_LAYER);

        // 账号文本框
        JTextField userText = new JTextField(15);
        userText.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        userText.setBounds(240, 120, 150, 30);
        jp.add(userText,JLayeredPane.DRAG_LAYER);

        // 密码
        JLabel pwdLabel = new JLabel("密 码:");
        pwdLabel.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        pwdLabel.setForeground(Color.black);
        pwdLabel.setBounds(160, 160, 80, 30);
        jp.add(pwdLabel,JLayeredPane.DRAG_LAYER);

        // 密码文本框
        JPasswordField pwdText = new JPasswordField(15);
        pwdText.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        pwdText.setBounds(240, 160, 150, 30);
        jp.add(pwdText,JLayeredPane.DRAG_LAYER);

        // 注册按钮
        JButton regButton = new JButton("注 册");
        regButton.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        regButton.setBounds(180, 250, 80, 30);
        jp.add(regButton,JLayeredPane.DRAG_LAYER);

        // 登录按钮
        JButton loginButton = new JButton("登 录");
        loginButton.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        loginButton.setBounds(360, 250, 80, 30);
        jp.add(loginButton,JLayeredPane.DRAG_LAYER);

        frame.add(jp);
        frame.setVisible(true);

        // 按钮监听操作
        // 点击登录
        loginButton.addActionListener(e -> {
            // 判断账号是否与数据库一致
            IUserAccountDao account = new UserAccountDao();
            String acInput = userText.getText();
            var account1 = account.GetData(acInput);
            String acResult = account1.username;

            // 判断密码是否与数据库一致
            String pwInput = pwdText.getText();
            String pwResult = account1.password;

            if(acInput.equals(acResult) && pwInput.equals(pwResult)){
                //弹出成功登录的信息弹窗
                new Notice("您已成功登录");
                //为用户打开仓库列表
                new WarehouseList();
            } else{
                //登陆失败并弹窗提示
                new Notice("账号或密码错误,请重新输入");
            }
        });

        // 点击注册
        regButton.addActionListener(e -> new Register());
    }
}