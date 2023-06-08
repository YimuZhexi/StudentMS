package org.wms.Frame;

import org.wms.Link.WareAd;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

public class WareDelete {
    public WareDelete() {
        JFrame frame = new JFrame("删除仓库");
        frame.setSize(450, 200);
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
        ImageIcon img = new ImageIcon("images/2.png");//在此处输入图片路径
        Image imgScaled = img.getImage().getScaledInstance(windowWidth, windowHeight, Image.SCALE_SMOOTH);
        JLabel label = new JLabel(new ImageIcon(imgScaled));
        label.setBounds(0, 0, windowWidth, windowHeight);
        jp.add(label, JLayeredPane.DEFAULT_LAYER);

        // 标题文本
        JLabel title = new JLabel("请输入要删除的仓库编号");
        title.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        title.setForeground(Color.black);
        title.setBounds(120, 10, 600, 40);
        jp.add(title, JLayeredPane.DRAG_LAYER);

        // 仓库编号
        JLabel WarehouseAddress = new JLabel("仓库编号");
        WarehouseAddress.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        WarehouseAddress.setForeground(Color.black);
        WarehouseAddress.setBounds(100, 63, 120, 30);
        jp.add(WarehouseAddress, JLayeredPane.DRAG_LAYER);

        // 仓库名字文本框
        JTextField WarehouseAddressText = new JTextField(10);
        WarehouseAddressText.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        WarehouseAddressText.setBounds(200, 63, 150, 30);
        jp.add(WarehouseAddressText, JLayeredPane.DRAG_LAYER);

        // 删除按钮
        JButton newWareButton = new JButton("确认删除");
        newWareButton.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        newWareButton.setBounds(100, 110, 250, 30);
        jp.add(newWareButton, JLayeredPane.DRAG_LAYER);

        // 点击删除按钮
        newWareButton.addActionListener(e -> {
            String ID = WarehouseAddressText.getText();

            if (WareAd.haveWare(ID)) {
                new Notice("仓库不存在");

            } else {
                WareAd.deleteWareID(ID);
                frame.dispose();
                new Notice("通过编号删除仓库成功");
            }
        });

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                for (Frame f : Frame.getFrames()) {
                    if (f != frame) {
                        f.setEnabled(true);
                    }
                }
            }
        });

        frame.add(jp);
        frame.setVisible(true);
    }
}
