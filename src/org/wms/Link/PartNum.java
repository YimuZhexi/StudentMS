package org.wms.Link;

import org.wms.database.data.daoFactory.DaoFactory;
import org.wms.database.data.dataType.TPartInWarehouse;

import java.util.ArrayList;

public class PartNum {
    public String Num;

    public PartNum(String num) {
        this.Num = num;
    }

    public String getNum() {
        return Num;
    }

    public static void PartAll(String ID, String wareID) {
        var lin = DaoFactory.GetPartInWarehouseDao();
        ArrayList<TPartInWarehouse> part = lin.GetAllData();
        for (var w : part) {
            if (w.warehouseID.equals(ID) && w.warehouseID.equals(wareID)) {
                lin.GetDataByID(wareID, ID);

            }
        }
    }
}
