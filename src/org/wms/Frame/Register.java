package org.wms.Frame;

import org.wms.Link.*;

import javax.swing.*;
import java.awt.*;

public class Register extends JFrame {
    public Register() {
        JFrame frame = new JFrame("账号注册");
        frame.setSize(450, 500);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

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
        ImageIcon img = new ImageIcon("src/images/4.png");//在此处输入图片路径
        Image imgScaled = img.getImage().getScaledInstance(windowWidth, windowHeight, Image.SCALE_SMOOTH);
        JLabel label = new JLabel(new ImageIcon(imgScaled));
        label.setBounds(0, 0, windowWidth, windowHeight);
        jp.add(label, JLayeredPane.DEFAULT_LAYER);

        // 标题文本
        JLabel title = new JLabel("注册信息");
        title.setFont(new Font("微软雅黑", Font.PLAIN, 25));
        title.setForeground(Color.black);
        title.setBounds(170, 30, 600, 40);
        jp.add(title, JLayeredPane.DRAG_LAYER);

        // 注册账号
        JLabel userLabel = new JLabel("注册账号:");
        userLabel.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        userLabel.setForeground(Color.black);
        userLabel.setBounds(100, 100, 120, 30);
        jp.add(userLabel, JLayeredPane.DRAG_LAYER);

        // 注册账号文本框
        JTextField userText = new JTextField(15);
        userText.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        userText.setBounds(200, 100, 150, 30);
        jp.add(userText, JLayeredPane.DRAG_LAYER);

        // 注册邮箱
        JLabel email = new JLabel("注册邮箱:");
        email.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        email.setForeground(Color.black);
        email.setBounds(100, 150, 120, 30);
        jp.add(email, JLayeredPane.DRAG_LAYER);

        // 注册邮箱文本框
        JTextField emailText = new JTextField(50);
        emailText.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        emailText.setBounds(200, 150, 150, 30);
        jp.add(emailText, JLayeredPane.DRAG_LAYER);

        // 注册手机号
        JLabel phoneNumber = new JLabel("电话号码:");
        phoneNumber.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        phoneNumber.setForeground(Color.black);
        phoneNumber.setBounds(100, 200, 120, 30);
        jp.add(phoneNumber, JLayeredPane.DRAG_LAYER);

        // 注册账号文本框
        JTextField phoneNumberText = new JTextField(15);
        phoneNumberText.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        phoneNumberText.setBounds(200, 200, 150, 30);
        jp.add(phoneNumberText, JLayeredPane.DRAG_LAYER);

        // 注册密码文本
        JLabel pwdLabel = new JLabel("注册密码:");
        pwdLabel.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        pwdLabel.setForeground(Color.black);
        pwdLabel.setBounds(100, 250, 120, 30);
        jp.add(pwdLabel, JLayeredPane.DRAG_LAYER);

        // 注册密码文本框
        JPasswordField pwdText = new JPasswordField(15);
        pwdText.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        pwdText.setBounds(200, 250, 150, 30);
        jp.add(pwdText, JLayeredPane.DRAG_LAYER);

        // 重复密码文本
        JLabel rePwdLabel = new JLabel("重复密码:");
        rePwdLabel.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        rePwdLabel.setForeground(Color.black);
        rePwdLabel.setBounds(100, 300, 120, 30);
        jp.add(rePwdLabel, JLayeredPane.DRAG_LAYER);

        // 重复密码文本框
        JPasswordField rePwdText = new JPasswordField(15);
        rePwdText.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        rePwdText.setBounds(200, 300, 150, 30);
        jp.add(rePwdText, JLayeredPane.DRAG_LAYER);

        // 注册按钮
        JButton back = new JButton("已有账号");
        back.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        back.setBounds(80, 380, 120, 30);
        jp.add(back, JLayeredPane.DRAG_LAYER);

        // 登录按钮
        JButton regButton = new JButton("注  册");
        regButton.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        regButton.setBounds(250, 380, 120, 30);
        jp.add(regButton, JLayeredPane.DRAG_LAYER);

        frame.add(jp);
        frame.setVisible(true);

        // 按钮监听操作
        // 点击已有账户
        back.addActionListener(e -> frame.setVisible(false));

        // 输入信息后点击注册
        regButton.addActionListener(e -> {
            boolean equals = true;

            // 获取用户输入的密码
            char[] pwd = pwdText.getPassword();
            // 获取用户输入的重复密码
            char[] rePwd = rePwdText.getPassword();

            String user = userText.getText();
            String uEmail = emailText.getText();
            String phoneNum = phoneNumberText.getText();
            var pass = pwdText.getPassword();
            String password = new String(pass);


            if (pwd.length == rePwd.length) {
                for (int i = 0; i < pwd.length; i++) {
                    if (pwd[i] != rePwd[i]) {
                        equals = false;
                        break;
                    }
                }
            } else {
                equals = false;
            }
            if (equals && User.NuJudge(user)) {
                System.out.println(NewUser.NwUser(user, uEmail, password, phoneNum));

                //信息合法,注册成功
                new Notice("注册成功,请登录");
                frame.setVisible(false);
            } else {
                //信息不合法,注册失败
                new Notice("注册失败,请检查并重新输入信息");
            }
        });
    }
}