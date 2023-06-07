package org.wms.Link;

import org.wms.database.data.daoFactory.DaoFactory;
import org.wms.database.data.dataType.TPart;
import org.wms.database.data.dataname.Table;

import java.util.ArrayList;

public class PartAD {
    /*判断零件是否重复*/
    public static boolean havePart(String pName, String pID) {
        var nPart = DaoFactory.GetPartDao();
        ArrayList<TPart> part = nPart.GetAllData();

        for (var w : part) {
            String name = w.partName;
            String ID = w.partID;
            return !pName.equals(name) && !pID.equals(ID);
        }
        return true;
    }

    /*上传至数据库*/
    public static void insertPart(String pName, String pID, Double pPrice, String prID) {
        TPart newPart = new TPart();
        newPart.partID = pID;
        newPart.partName = pName;
        newPart.partPrice = pPrice;
        newPart.providerID = prID;

        var update = DaoFactory.GetPartDao();
        update.InsertData(newPart);
    }

    public static boolean havePartDelete(String pID) {
        var oldPart = DaoFactory.GetPartDao();
        ArrayList<TPart> oPart = oldPart.GetAllData();
        for (var w : oPart) {
            if(w.partID.equals(pID))return true;
        }
        return false;
    }

    public static void deletePart(String ID) {
        var delete = DaoFactory.GetPartDao();
        int flag = delete.DeleteDataByID(ID);
        if (flag == 1) {
            var piw = DaoFactory.GetPartInWarehouseDao();
            var pw = piw.GetDataByID(Table.PartInWarehouse.partID, ID);
            for (var item : pw) {
                piw.DeleteByPW(ID, item.warehouseID);
            }
        }
    }
}
