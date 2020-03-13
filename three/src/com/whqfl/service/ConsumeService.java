package com.whqfl.service;

import java.util.Map;

public interface ConsumeService {
    /**
     * 新增器材消费
     * @param orderId
     * @param cardId
     * @param price
     * @param pay
     * @param momo
     * @return
     */
    /**
     * 提交订单，更新器材数量，卡钱，增加订单
     */
    Map<String,Object>insertOrder(String ruleOrderId, String ruleCardId, String rulePrice, String rulePay, String ruleMOmo, String goodsId);
    /**
     * 器材列表
     */
    Map<String,Object> consumeGoodList(Integer pageNumber, Integer pageSize, String searchId, String searchName)throws Exception;

}
