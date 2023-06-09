package org.wms.database.data.dao;

import org.wms.database.data.dataType.TPart;

import java.util.ArrayList;

/**
 * 零件操作表接口
 */
public interface IPartDao {
    /**
     * 获取所有数据
     * @return ArrayList<TPart> 所有零件数据项
     */
    ArrayList<TPart> GetAllData();

    /**
     * 通过零件编号查询结果
     * @param partID 零件编号
     * @return TPart 查询结果
     */
    TPart GetDataByID(String partID);

    /**
     * 通过供应商编号查询零件
     * @param providerID 供应商号
     * @return ArrayList<TPart> 零件数据链
     */
    ArrayList<TPart> GetDataByPrvID(String providerID);

    /**
     * 通过价格获取零件数据
     * @param price 价格
     * @param eq 0：相等；1：大于；-1：小于
     * @return ArrayList<TPart> 零件数据链
     */
    ArrayList<TPart> GetDataByPrice(double price, int eq);

    /**
     * 通过零件ID删除零件数据
     * @param partID 零件ID
     * @return 成功1；失败0
     */
    int DeleteDataByID(String partID);

    /**
     * 通过供应商号删除数据
     * @param providerID 供应商号
     * @return 成功1；失败0
     */
    int DeleteDataByPrvID(String providerID);

    /**
     * 插入一行新的数据
     * @param part 新的数据
     * @return 成功1；失败0
     */
    int InsertData(TPart part);

    /**
     * 更新数据
     * @param partID 零件编号
     * @param part 新数据
     * @return 成功1；失败0
     */
    int UpdateOneLine(String partID, TPart part);
}
