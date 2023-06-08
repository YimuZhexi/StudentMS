package org.wms.Frame;

import org.wms.Link.PartNum;
import org.wms.database.data.dao.iml.PartDao;
import org.wms.database.data.dao.iml.PartInWarehouseDao;
import org.wms.database.data.dataType.TPart;
import org.wms.database.data.dataname.Table;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.util.ArrayList;

public class WarehouseInfo {
    public WarehouseInfo(String wareID) {
        JFrame frame = new JFrame("仓库管理系统");
        frame.setSize(800, 600);
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
        JLabel title = new JLabel("仓库详细信息");
        title.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        title.setForeground(Color.black);
        title.setBounds(320, 20, 600, 30);
        jp.add(title,JLayeredPane.DRAG_LAYER);

        // 在标题文本下方插入sql仓库详细数据
        // 创建表格(右侧详细信息)

        // 获取数据库数据
        ArrayList<TPart> parts = new ArrayList<>();
        var part = new PartInWarehouseDao().GetDataByID(Table.Warehouse.warehouseID, wareID);

        System.out.println(wareID);
        for (var item : part) {
            TPart p = new PartDao().GetDataByID(item.partID);
            parts.add(p);
        }

        // 创建表格模型
        DefaultTableModel tmInfo = new DefaultTableModel();
        tmInfo.setColumnIdentifiers(Table.Part.GetColumnName1()); // 设置列名

        // 添加数据到表格模型

        /*分别传入仓库和零件编号*//*
        WareID wareID1 = new WareID(wareID);*/


        for (TPart warePart : parts) {
            if (warePart.partID != null) {
                tmInfo.addRow(new Object[]{
                        warePart.partID,
                        warePart.partName,
                        warePart.partPrice,
                        warePart.providerID,
                        PartNum.PartNUM(warePart.partID, wareID)
                });
            }
        }
        // 创建表格
        JTable jtInfo = new JTable(tmInfo);
        jtInfo.setRowHeight(30); // 设置行高
        jtInfo.setEnabled(false);
        // 锁定列名无法拖动
        JTableHeader infoHeader = jtInfo.getTableHeader();
        infoHeader.setReorderingAllowed(false);
        // 设置表格参数
        JScrollPane infoSp = new JScrollPane(jtInfo);
        infoSp.setBounds(30, 70, 740, 400);
        jp.add(infoSp, JLayeredPane.DRAG_LAYER);

        // 新建零件按钮
        JButton newPartButton = new JButton("新建零件");
        newPartButton.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        newPartButton.setBounds(200, 500, 120, 30);
        jp.add(newPartButton, JLayeredPane.DRAG_LAYER);

        // 点击新建零件按钮
        newPartButton.addActionListener(e -> new PartAdd(wareID));

        // 删除零件按钮
        JButton deletePartButton = new JButton("删除零件");
        deletePartButton.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        deletePartButton.setBounds(500, 500, 120, 30);
        jp.add(deletePartButton, JLayeredPane.DRAG_LAYER);

        // 点击删除零件按钮
        deletePartButton.addActionListener(e -> new PartDelete());

        // 刷新按钮
        JButton refreshButton = new JButton("刷新");
        refreshButton.setFont(new Font("微软雅黑", Font.PLAIN, 10));
        refreshButton.setBounds(650, 30, 80, 30);
        jp.add(refreshButton, JLayeredPane.DRAG_LAYER);

        // 点击刷新按钮
        refreshButton.addActionListener(e -> {
            frame.dispose();
            new WarehouseInfo(wareID);
        });

        frame.add(jp);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }
}