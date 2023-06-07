package org.wms.Link;

import org.wms.database.data.daoFactory.DaoFactory;
import org.wms.database.data.dataType.TPartInWarehouse;
import org.wms.database.data.dataname.Table;

import java.util.ArrayList;

public class PartNum {
    public String Num;

    public PartNum(String num) {
        this.Num = num;
    }

    public static int PartNUM(String ID, String wareID) {
        var lin = DaoFactory.GetPartInWarehouseDao();
        ArrayList<TPartInWarehouse> part1=lin.GetDataByID(Table.PartInWarehouse.partID,ID);
        for (var w : part1) {
            if (w.warehouseID.equals(wareID)) {
                return w.partNum;
            }
        }
        return 0;
    }
}

