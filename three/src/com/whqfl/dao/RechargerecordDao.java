package com.whqfl.dao;

import com.whqfl.entity.Rechargerecord;
import com.whqfl.util.IntegerUtils;

import java.util.List;

public interface RechargerecordDao {

    /**
     * 充值记录
     * @param rechargerecord
     * @return
     */
    int addRechargerecord(Rechargerecord rechargerecord);

    /**
     * 根据卡Id获得所有卡的充值记录
     * @param cardId
     * @return
     */
    List<Rechargerecord> getRechargerecordBycardId(Integer cardId,Integer pageNumber,Integer pageSize);

    /**
     * 根据卡Id获得所有卡的充值记录的条数
     * @param cardId
     * @return
     */
    int getRechargerecordBycardIdCount(Integer cardId);
}
