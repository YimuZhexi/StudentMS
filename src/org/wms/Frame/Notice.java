package org.wms.Frame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

public class Notice {
    public Notice(String message){
        JFrame frame = new JFrame("提示");
        frame.setSize(400, 100);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

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
        ImageIcon img = new ImageIcon("src/images/2.png");//在此处输入图片路径
        Image imgScaled = img.getImage().getScaledInstance(windowWidth, windowHeight, Image.SCALE_SMOOTH);
        JLabel label = new JLabel(new ImageIcon(imgScaled));
        label.setBounds(0, 0, windowWidth, windowHeight);
        jp.add(label, JLayeredPane.DEFAULT_LAYER);

        // 标题文本
        JLabel title = new JLabel(message);
        title.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        title.setForeground(Color.BLACK);

        // 获取标题文本的大小
        Dimension size = title.getPreferredSize();
        int titleWidth = size.width;
        int titleHeight = size.height;

        // 计算标题文本的位置
        int x = (windowWidth - titleWidth) / 2;
        int y = (windowHeight - titleHeight) / 2;

        // 设置标题文本的位置
        title.setBounds(x, y - titleHeight, frame.getWidth(), titleHeight);

        jp.add(title,JLayeredPane.DRAG_LAYER);
        frame.setAlwaysOnTop(true);
        frame.add(jp);
        frame.setVisible(true);

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
    }
}