package org.wms.database.data.dataType;

public class TWarehouse {
    /**
     * 仓库编号
     */
    public String warehouseID;
    /**
     * 仓库名称
     */
    public String warehouseName;
    /**
     * 仓库地址
     */
    public String warehouseAddress;

    public TWarehouse() {
    }

    /**
     * 构造仓库
     *
     * @param warehouseID      仓库号 varchar(10) PRIMARY KEY
     * @param warehouseAddress 仓库地址 varchar(20)
     * @param warehouseName    仓库名称 varchar(20)NOT NULL
     */
    public TWarehouse(String warehouseID, String warehouseAddress, String warehouseName) {
        this.warehouseAddress = warehouseAddress;
        this.warehouseID = warehouseID;
        this.warehouseName = warehouseName;
    }
}
