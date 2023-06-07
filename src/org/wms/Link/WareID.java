package org.wms.Link;

import org.wms.database.data.daoFactory.DaoFactory;
import org.wms.database.data.dataType.TPartInWarehouse;

import java.util.ArrayList;

public class WareID {
    /*获取零件数量*/
    public int getNUM(String name) {
        int Num;
        var nus = DaoFactory.GetPartInWarehouseDao();
        ArrayList<TPartInWarehouse> num = nus.GetAllData();

        for (var w : num) {
            /*  for (var x : nu) {*/
            if (w.partID.equals(name)) {
                Num = w.partNum;
                return Num;
            }
            /*}*/
        }
        return 0;
    }
}


