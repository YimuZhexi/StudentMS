package org.wms.database.data.dao;

import org.wms.database.data.dataType.TProvider;

import java.util.ArrayList;

public interface IProviderDao {
    /**
     * 获取所有数据
     *
     * @return 供应商数据链
     */
    ArrayList<TProvider> GetAllData();

    /**
     * 通过供应商编号获取供应商信息
     *
     * @param providerID 供应商编号
     * @return 供应商数据
     */
    TProvider GetDataByProviderID(String providerID);

    /**
     * 新添供应商数据
     *
     * @param provider 新添的供应商数据
     * @return 1：成功，0：失败
     */
    int InsertOneLine(TProvider provider);

    /**
     * 通过供应商好删除数据
     *
     * @param providerID 供应商号
     * @return 1：成功，0：失败
     */
    int DeleteByProviderID(String providerID);

    /**
     * 通过供应商号修改数据
     *
     * @param providerID 供应商号
     * @return 1：成功，0：失败
     */
    int UpdateByProviderID(String providerID, TProvider provider);
}
