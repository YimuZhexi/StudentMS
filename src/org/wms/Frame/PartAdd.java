package org.wms.Frame;

import org.wms.Link.PartAD;
import org.wms.Link.PartUP;

import javax.swing.*;
import java.awt.*;

public class PartAdd {
    public PartAdd(String wareID) {
        JFrame frame = new JFrame("添加零件");
        frame.setSize(450, 450);
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
        ImageIcon img = new ImageIcon("images/4.png");//在此处输入图片路径
        Image imgScaled = img.getImage().getScaledInstance(windowWidth, windowHeight, Image.SCALE_SMOOTH);
        JLabel label = new JLabel(new ImageIcon(imgScaled));
        label.setBounds(0, 0, windowWidth, windowHeight);
        jp.add(label, JLayeredPane.DEFAULT_LAYER);

        // 标题文本
        JLabel title = new JLabel("添加零件信息");
        title.setFont(new Font("微软雅黑", Font.PLAIN, 25));
        title.setForeground(Color.black);
        title.setBounds(150, 30, 600, 40);
        jp.add(title, JLayeredPane.DRAG_LAYER);

        // 零件编号
        JLabel partID = new JLabel("零件编号");
        partID.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        partID.setForeground(Color.black);
        partID.setBounds(100, 100, 120, 30);
        jp.add(partID, JLayeredPane.DRAG_LAYER);

        // 零件编号文本框
        JTextField partIDText = new JTextField(10);
        partIDText.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        partIDText.setBounds(200, 100, 150, 30);
        jp.add(partIDText, JLayeredPane.DRAG_LAYER);

        // 零件名字
        JLabel partName = new JLabel("零件名字");
        partName.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        partName.setForeground(Color.black);
        partName.setBounds(100, 150, 120, 30);
        jp.add(partName, JLayeredPane.DRAG_LAYER);

        // 零件名字文本框
        JTextField partNameText = new JTextField(10);
        partNameText.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        partNameText.setBounds(200, 150, 150, 30);
        jp.add(partNameText, JLayeredPane.DRAG_LAYER);

        // 零件价格
        JLabel partPrice = new JLabel("零件价格");
        partPrice.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        partPrice.setForeground(Color.black);
        partPrice.setBounds(100, 200, 120, 30);
        jp.add(partPrice, JLayeredPane.DRAG_LAYER);

        // 零件价格文本框
        JTextField partPriceText = new JTextField(10);
        partPriceText.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        partPriceText.setBounds(200, 200, 150, 30);
        jp.add(partPriceText, JLayeredPane.DRAG_LAYER);

        // 供应商号
        JLabel partProviderID = new JLabel("供应商号");
        partProviderID.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        partProviderID.setForeground(Color.black);
        partProviderID.setBounds(100, 250, 120, 30);
        jp.add(partProviderID, JLayeredPane.DRAG_LAYER);

        // 供应商号文本框
        JTextField partProviderIDText = new JTextField(10);
        partProviderIDText.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        partProviderIDText.setBounds(200, 250, 150, 30);
        jp.add(partProviderIDText, JLayeredPane.DRAG_LAYER);

        // 零件数量
        JLabel partNum = new JLabel("零件数量");
        partNum.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        partNum.setForeground(Color.black);
        partNum.setBounds(100, 300, 120, 30);
        jp.add(partNum, JLayeredPane.DRAG_LAYER);

        // 零件数量文本框
        JTextField partNumText = new JTextField(10);
        partNumText.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        partNumText.setBounds(200, 300, 150, 30);
        jp.add(partNumText, JLayeredPane.DRAG_LAYER);

        // 新建按钮
        JButton newPartButton = new JButton("添加零件");
        newPartButton.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        newPartButton.setBounds(100, 350, 250, 30);
        jp.add(newPartButton, JLayeredPane.DRAG_LAYER);

        // 点击已有账户
        newPartButton.addActionListener(e -> {
            String PartID = partIDText.getText();
            String PartPrID = partProviderIDText.getText();
            String PartPrice = partPriceText.getText();
            String PartName = partNameText.getText();
            Double PPrice = Double.parseDouble(PartPrice);

            /*传入零件数量*/
            int pNum = Integer.parseInt(partNumText.getText());

            boolean TF = true;
            if (PartName.length() > 20) {
                TF = false;
                new Notice("名字超出范围20");
            }
            if (PartID.length() > 10) {
                TF = false;
                new Notice("零件编号超出范围10");
            }
            if (PartPrID.length() > 10) {
                TF = false;
                new Notice("供应商编号超出范围10");
            }

            if (TF && PartAD.havePart(PartName, PartID)) {
                PartAD.insertPart(PartName, PartID, PPrice, PartPrID);
                PartUP.UpByHouse(pNum, PartID, wareID);
                new Notice("上传数据库成功");
            } else {
                new Notice("上传失败");
            }
            frame.dispose();

        });

        frame.add(jp);
        frame.setVisible(true);
    }
}
