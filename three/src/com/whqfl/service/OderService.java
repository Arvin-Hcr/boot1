package com.whqfl.service;

import com.whqfl.entity.Order;
import com.whqfl.util.BusinessException;

import java.util.List;
import java.util.Map;
public interface OderService {
    /**
     * 获取订单表
     */
    Map<String,Object> getOderService(Integer pagerNumber,Integer pagerSize,String searchOrderId)throws Exception;
    /**
     * 添加备注
     */
    int updateOderByIdService(String ruleId,String ruleOrderId,String ruleCardId,String ruleCardType,String rulePrice,String rulePay,String ruleCredit,String ruleMOmo,String ruleCrreatedTime)throws Exception;
    /**
     * 修改订单状态
     */
    int delOderService(Integer id);

    /**
     * 根君CardId查找会员的
     * @param cardId
     * @param pageNumber
     * @param pageSize
     * @return
     */
    List<Order> getOrderById(Integer cardId, Integer pageNumber, Integer pageSize) throws Exception;

    /**
     * 查询条数
     * @param cardId
     * @return
     */
    int getOrderByIdCount(Integer cardId) throws Exception;
}
