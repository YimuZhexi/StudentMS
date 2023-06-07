package org.wms.Frame;

import org.wms.database.data.dao.iml.WarehouseDao;
import org.wms.database.data.dataType.TWarehouse;
import org.wms.database.data.dataname.Table;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class WarehouseList {
    public WarehouseList() {
        // 窗口基本参数
        JFrame frame = new JFrame("仓库管理系统");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        // 设置窗口屏幕居中
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

        // 文本基本参数
        JLabel title = new JLabel("您的仓库列表");
        title.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        title.setForeground(Color.black);
        title.setBounds(320, 20, 150, 30);
        title.setOpaque(false);
        jp.setComponentZOrder(title, 0);

        // 创建表格(左侧仓库列表):
        // 获取数据库数据
        ArrayList<TWarehouse> list = new WarehouseDao().GetAllData();
        // 创建表格模型
        DefaultTableModel tmList = new DefaultTableModel();
        tmList.setColumnIdentifiers(Table.Warehouse.GetColumnName()); // 设置列名
        // 添加数据到表格模型
        for (TWarehouse wareList : list) {
            tmList.addRow(new Object[]{
                    wareList.warehouseID,
                    wareList.warehouseName,
                    wareList.warehouseAddress
            });
        }
        // 创建表格
        JTable jtList = new JTable(tmList);
        jtList.setRowHeight(30); // 设置行高
        jtList.setEnabled(false);
        // 锁定列名无法拖动
        JTableHeader listHeader = jtList.getTableHeader();
        listHeader.setReorderingAllowed(false);
        // 设置表格参数
        JScrollPane listSp = new JScrollPane(jtList);
        listSp.setBounds(30, 70, 740, 400);
        jp.add(listSp, JLayeredPane.MODAL_LAYER);

        // 添加鼠标监听器
        jtList.addMouseListener(new MouseAdapter() {
            // 只有点击第一列才响应
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = jtList.rowAtPoint(e.getPoint());
                int col = jtList.columnAtPoint(e.getPoint());
                if (col == 0) {
                    // 获取用户点击的仓库号
                    Object[] rowData = new Object[tmList.getColumnCount()];
                    for (int i = 0; i < tmList.getColumnCount(); i++) {
                        rowData[i] = tmList.getValueAt(row, i);
                    }
                    String wareID = (String) rowData[0];

                    new WarehouseInfo(wareID);
                }
            }
        });

        // 新建仓库按钮
        JButton newWareButton = new JButton("新建仓库");
        newWareButton.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        newWareButton.setBounds(70, 500, 200, 30);
        jp.add(newWareButton);

        // 刷新按钮
        JButton refreshButton = new JButton("刷新");
        refreshButton.setFont(new Font("微软雅黑", Font.PLAIN, 10));
        refreshButton.setBounds(650, 30, 80, 30);
        jp.add(refreshButton);

        // 点击新建仓库按钮
        newWareButton.addActionListener(e -> new WareAdd());

        // 点击刷新按钮
        refreshButton.addActionListener(e -> {
            frame.dispose();
            new WarehouseList();
        });

        // 删除仓库按钮
        JButton deleteWareButton = new JButton("删除仓库");
        deleteWareButton.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        deleteWareButton.setBounds(500, 500, 120, 30);
        jp.add(deleteWareButton);

        // 点击删除仓库按钮
        deleteWareButton.addActionListener(e -> new WareDelete());

        frame.add(jp);
        frame.setVisible(true);
    }
}
