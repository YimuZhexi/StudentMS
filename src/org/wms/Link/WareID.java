package org.wms.Link;

import org.wms.database.data.daoFactory.DaoFactory;
import org.wms.database.data.dataType.TPartInWarehouse;

import java.util.ArrayList;

public class WareID {
    /*零件仓库编号*/
    public String warid;


    public WareID(String warid) {
        this.warid = warid;
    }

    /*获取零件数量*/
    public int getNUM(String name) {
        int Num;
        var nus = DaoFactory.GetPartInWarehouseDao();
        ArrayList<TPartInWarehouse> num = nus.GetAllData();

        for (var w : num) {
            /*  for (var x : nu) {*/
            if (w.warehouseID.equals(name)) {
                Num = w.partNum;
                return Num;
            }
            /*}*/
        }
        return 0;
    }
}


