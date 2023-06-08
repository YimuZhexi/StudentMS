package org.wms.database.data.dataType;

/**
 * 供应商数据类
 */
public class TProvider {
    /**
     * 供应商编号
     */
    public String providerID;
    /**
     * 供应商名
     */
    public String providerName;
    /**
     * 供应商住址
     */
    public String providerAddress;
    /**
     * 供应商邮箱
     */
    public String providerEmail;

    public TProvider() {
    }

    /**
     * 供应商构造
     *
     * @param providerID      供应商编号
     * @param providerName    供应商姓名
     * @param providerAddress 供应商住址
     * @param providerEmail   供应商邮箱
     */
    public TProvider(String providerID, String providerName, String providerAddress, String providerEmail) {
        this.providerID = providerID;
        this.providerName = providerName;
        this.providerAddress = providerAddress;
        this.providerEmail = providerEmail;
    }
}
