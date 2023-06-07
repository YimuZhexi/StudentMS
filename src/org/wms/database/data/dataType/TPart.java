package org.wms.database.data.dataType;

public class TPart {
    /**
     * 零件名称
     */
    public String partName;
    /**
     * 零件编号
     */
    public String partID;
    /**
     * 零件价格
     */
    public double partPrice;
    /**
     * 供应零件的供应商编号
     */
    public String providerID;

    public TPart() {
    }

    /**
     * 零件构造
     *
     * @param partName   零件名
     * @param partID     零件编号
     * @param providerID 供应商编号
     * @param partPrice  价格
     */
    public TPart(String partName, String partID, String providerID, double partPrice) {

        this.partName = partName;
        this.partID = partID;
        this.providerID = providerID;
        this.partPrice = partPrice;
    }
}
