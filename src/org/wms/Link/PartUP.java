package org.wms.Link;

import org.wms.database.data.daoFactory.DaoFactory;
import org.wms.database.data.dataType.TPart;
import org.wms.database.data.dataType.TPartInWarehouse;

public class PartUP {

    /*将零件普通数据传入数据库*/
    public static void UpPart(String ID,String Name,Double Price,String PPrID){
        TPart part=new TPart();
        part.partPrice=Price;
        part.partName=Name;
        part.partID=ID;
        part.providerID=PPrID;

        var update= DaoFactory.GetPartDao();
        update.UpdateOneLine(ID,part);
    }

    /*将零件的仓库号和数量传入数据库*/
    public static void UpByHouse(int Num,String partID,String warehouseID){
        TPartInWarehouse partinhouse=new TPartInWarehouse();
        partinhouse.partNum=Num;
        partinhouse.partID=partID;
        partinhouse.warehouseID=warehouseID;

        var insert=DaoFactory.GetPartInWarehouseDao();
        insert.InsertOnLine(partinhouse);
    }
}
