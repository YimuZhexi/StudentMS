package org.wms.Frame;

import org.wms.Link.PartAD;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

public class PartDelete {
    public PartDelete() {
        JFrame frame = new JFrame("删除零件");
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
        JLabel title = new JLabel("请输入要删除的零件编号");
        title.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        title.setForeground(Color.black);
        title.setBounds(120, 10, 600, 40);
        jp.add(title, JLayeredPane.DRAG_LAYER);

        // 零件编号
        JLabel partID = new JLabel("零件编号");
        partID.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        partID.setForeground(Color.black);
        partID.setBounds(100, 63, 120, 30);
        jp.add(partID, JLayeredPane.DRAG_LAYER);

        // 零件编号文本框
        JTextField partIDText = new JTextField(10);
        partIDText.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        partIDText.setBounds(200, 63, 150, 30);
        jp.add(partIDText, JLayeredPane.DRAG_LAYER);

        // 删除按钮
        JButton deletePartButton = new JButton("确认删除");
        deletePartButton.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        deletePartButton.setBounds(100, 110, 250, 30);
        jp.add(deletePartButton, JLayeredPane.DRAG_LAYER);

        // 点击删除按钮
        deletePartButton.addActionListener(e -> {
            //TODO:
            // 调用判断函数,判断成功则添加新仓库,并且 new Notice("删除成功") 进行弹窗
            // 判断失败则 new Notice("删除失败,零件编号不存在")
            String PartID = partIDText.getText();
            if (PartAD.havePartDelete(PartID)) {
                PartAD.deletePart(PartID);
                new Notice("删除成功");
            } else new Notice("删除失败，零件编号不存在");
        });

        // 使窗口置顶并锁定焦点
        frame.addWindowFocusListener(new WindowFocusListener() {
            @Override
            public void windowGainedFocus(WindowEvent e) {
                frame.toFront();
            }

            @Override
            public void windowLostFocus(WindowEvent e) {
                frame.toFront();
            }
        });

        for (Frame f : Frame.getFrames()) {
            if (f != frame) {
                f.setEnabled(false);
            }
        }

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
