package org.wms.database.data.dataType;

/**
 * 仓储数据类
 */
public class TPartInWarehouse {
    /**
     * 包含零件数量
     */
    public int partNum;
    /**
     * 包含的零件编号
     */
    public String partID;
    /**
     * 包含零件的仓库编号
     */
    public String warehouseID;

    public TPartInWarehouse() {
    }

    /**
     * 仓储构造
     *
     * @param partNum     零件数量
     * @param partID      零件编号
     * @param warehouseID 仓库编号
     */
    public TPartInWarehouse(int partNum, String partID, String warehouseID) {
        this.partNum = partNum;
        this.partID = partID;
        this.warehouseID = warehouseID;
    }
}
