package org.wms.Frame;

import org.wms.Link.WareUP;
import org.wms.Link.WareAd;

import javax.swing.*;
import java.awt.*;

public class WareAdd {
    public WareAdd(){
        JFrame frame = new JFrame("新建仓库");
        frame.setSize(450, 350);
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
        ImageIcon img = new ImageIcon("images/3.png");//在此处输入图片路径
        Image imgScaled = img.getImage().getScaledInstance(windowWidth, windowHeight, Image.SCALE_SMOOTH);
        JLabel label = new JLabel(new ImageIcon(imgScaled));
        label.setBounds(0, 0, windowWidth, windowHeight);
        jp.add(label, JLayeredPane.DEFAULT_LAYER);

        // 标题文本
        JLabel title = new JLabel("新建仓库信息");
        title.setFont(new Font("微软雅黑", Font.PLAIN, 25));
        title.setForeground(Color.black);
        title.setBounds(150, 30, 600, 40);
        jp.add(title,JLayeredPane.DRAG_LAYER);

        // 仓库编号
        JLabel WarehouseID = new JLabel("仓库编号");
        WarehouseID.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        WarehouseID.setForeground(Color.black);
        WarehouseID.setBounds(100, 100, 120, 30);
        jp.add(WarehouseID,JLayeredPane.DRAG_LAYER);

        // 仓库编号文本框
        JTextField WarehouseIDText = new JTextField(10);
        WarehouseIDText.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        WarehouseIDText.setBounds(200, 100, 150, 30);
        jp.add(WarehouseIDText,JLayeredPane.DRAG_LAYER);

        // 仓库名字
        JLabel WarehouseName = new JLabel("仓库名字");
        WarehouseName.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        WarehouseName.setForeground(Color.black);
        WarehouseName.setBounds(100, 150, 120, 30);
        jp.add(WarehouseName,JLayeredPane.DRAG_LAYER);

        // 仓库名字文本框
        JTextField WarehouseNameText = new JTextField(10);
        WarehouseNameText.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        WarehouseNameText.setBounds(200, 150, 150, 30);
        jp.add(WarehouseNameText,JLayeredPane.DRAG_LAYER);

        // 仓库地址
        JLabel WarehouseAddress = new JLabel("仓库地址");
        WarehouseAddress.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        WarehouseAddress.setForeground(Color.black);
        WarehouseAddress.setBounds(100, 200, 120, 30);
        jp.add(WarehouseAddress,JLayeredPane.DRAG_LAYER);

        // 仓库名字文本框
        JTextField WarehouseAddressText = new JTextField(10);
        WarehouseAddressText.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        WarehouseAddressText.setBounds(200, 200, 150, 30);
        jp.add(WarehouseAddressText,JLayeredPane.DRAG_LAYER);

        // 新建按钮
        JButton newWareButton = new JButton("新建仓库");
        newWareButton.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        newWareButton.setBounds(100, 250, 250, 30);
        jp.add(newWareButton,JLayeredPane.DRAG_LAYER);

        // 点击已有账户
        newWareButton.addActionListener(e -> {
            String whAddress = WarehouseAddressText.getText();
            String whID = WarehouseIDText.getText();
            String whName = WarehouseNameText.getText();

            boolean TF = true;
            if (whID.length() > 10) {
                TF = false;
                new Notice("编号超出范围10");
            }
            if (whName.length() > 20) {
                TF = false;
                new Notice("名字超出范围20");
            }
            if (whAddress.length() > 20) {
                TF = false;
                new Notice("地址超出范围20");
            }

            /*判断是仓库已有*/
            if (WareAd.haveWare(whID)) {
                if (TF) {
                    WareAd.addWare(whID, whName, whAddress);
                    new Notice("仓库创建成功");
                }
            } else{
                new Notice("仓库创建失败，该仓库号已存在！您可以选择先删除该仓库。");
            }
            frame.dispose();
        });


        frame.add(jp);
        frame.setVisible(true);
    }
}
