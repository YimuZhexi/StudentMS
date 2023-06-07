package org.wms.Link;

import org.wms.database.data.daoFactory.DaoFactory;
import org.wms.database.data.dataType.TWarehouse;

import java.util.ArrayList;

public class Waread {

    /*判断仓库是否已存在*/
    public static boolean haveWare(String whName, String whID) {
        var ware = DaoFactory.GetWarehouseDao();
        ArrayList<TWarehouse> nw = ware.GetAllData();

        for (var w : nw) {
            String ID = w.warehouseID;
            if (whID.equals(ID)) {
                return true;
            }
        }
        return false;
    }

    /*增加仓库*/
    public static void addWare(String warehouseID, String warehouseName, String warehouseAddress) {
        /*传入数据创建一个新的类*/
        TWarehouse addWare = new TWarehouse();
        addWare.warehouseID = warehouseID;
        addWare.warehouseName = warehouseName;
        addWare.warehouseAddress = warehouseAddress;
        /*将用户数据上传数据库*/
        var update = DaoFactory.GetWarehouseDao();
        update.InsertOnLine(addWare);
    }

    /*删除仓库*/
    public static void deleteWareID(String ID) {
        var deleteID = DaoFactory.GetWarehouseDao();
        deleteID.DeleteDataByID(ID);
    }

    public static void deleteWareAdd(String add) {
        var deleteAdd = DaoFactory.GetWarehouseDao();
        deleteAdd.DeleteDataByAddress(add);
    }
}
