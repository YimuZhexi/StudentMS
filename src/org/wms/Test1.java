package org.wms;

import org.wms.database.data.daoFactory.DaoFactory;

public class Test1 {
    public static void main(String[] args) {
        var part = DaoFactory.GetPartDao();
        var parts = part.GetDataByID("p003");
        if(parts==null) System.out.println("空");
    }
}
