package org.wms.Link;

import org.wms.database.data.daoFactory.DaoFactory;
import org.wms.database.data.dataType.TWarehouse;

public class WareUP {
    /*通过ID更新*/
    public static void UpbyID(String ID, String Name, String Address) {
        TWarehouse warehouse = new TWarehouse(ID, Address, Name);
        warehouse.warehouseAddress = Address;
        warehouse.warehouseName = Name;
        warehouse.warehouseID = ID;

        var up = DaoFactory.GetWarehouseDao();
        up.UpdateOnLineByID(ID, warehouse);
    }

    /*通过地址更新*/
    public static void UpbyAdd(String Add, String Name, String Address) {
        TWarehouse warehouse = new TWarehouse(Add, Address, Name);
        warehouse.warehouseAddress = Address;
        warehouse.warehouseName = Name;
        warehouse.warehouseID = Add;

        var up = DaoFactory.GetWarehouseDao();
        up.UpdateOnLineByAddress(Add, warehouse);
    }
}
