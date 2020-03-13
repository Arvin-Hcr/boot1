package com.whqfl.service;

import com.whqfl.entity.Rechargerecord;
import com.whqfl.util.BusinessException;

import java.util.List;

public interface RechargerecordService {
    /**
     * 新增会员充值记录
     * @param cardid
     * @param rechargeamount
     * @param afteramount
     * @param beforeamount
     * @param ruleid
     * @param createdtime
     * @param staffid
     * @param momo
     * @return
     */
    int addRechargerecord(Integer cardid,Double rechargeamount,Double afteramount,Double beforeamount,Integer ruleid,String createdtime,
                             Integer staffid,String momo) throws Exception;

    /**
     * 会员充值记录
     * @param cardid
     * @param rechargeamount
     * @param afteramount
     * @param beforeamount
     * @param ruleid
     * @param createdtime
     * @param staffid
     * @param momo
     * @return
     * @throws Exception
     */
    int addUserRechargerecord(Integer cardid,Double rechargeamount,Double afteramount,Double beforeamount,Integer ruleid,String createdtime,
                          Integer staffid,String momo) throws Exception;
    /**
     * 根据卡Id获得所有卡的充值记录
     * @param cardId
     * @return
     */
    List<Rechargerecord> getRechargerecordBycardId(Integer cardId,Integer pageNumber,Integer pageSize) throws Exception;

    /**
     * 根据卡Id获得所有卡的充值记录的条数
     * @param cardId
     * @return
     */
    int getRechargerecordBycardIdCount(Integer cardId) throws Exception;
}
