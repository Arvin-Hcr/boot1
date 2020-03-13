package com.whqfl.service.impl;

import com.whqfl.dao.OderDao;
import com.whqfl.dao.impl.OderDaoImpl;
import com.whqfl.entity.ConsumOrder;
import com.whqfl.entity.Order;
import com.whqfl.service.OderService;
import com.whqfl.util.BaseDao;
import com.whqfl.util.BusinessException;
import com.whqfl.util.IntegerUtils;
import org.apache.commons.lang3.StringUtils;

import java.awt.*;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OderServiceImpl implements OderService {
    private OderDao oderDao=new OderDaoImpl();
    @Override
    /**
     * 获取订单表
     */
    public Map<String, Object> getOderService(Integer pagerNumber, Integer pagerSize, String searchOrderId) throws Exception {
        pagerNumber=(pagerNumber-1)*pagerSize;
        List<ConsumOrder> oderList = oderDao.getOderList(pagerNumber, pagerSize, searchOrderId);
        int oderCount = oderDao.getOderCount(searchOrderId);
        Map<String,Object> map=new HashMap<>();
        map.put("list",oderList);
        map.put("count",oderCount);
        return map;
    }

    @Override
    /**
     * 添加备注
     */
    public int updateOderByIdService(String ruleId, String ruleOrderId, String ruleCardId, String ruleCardType, String rulePrice, String rulePay, String ruleCredit,  String ruleMOmo, String ruleCreatedTime)throws Exception {
        if (StringUtils.isBlank(ruleOrderId)){
            throw new BusinessException("订单编号不能为空");
        }
        if (StringUtils.isBlank(ruleCardId)){
            throw new BusinessException("卡id不能为空");
        }
        if (StringUtils.isBlank(ruleCardType)){
            throw new BusinessException("卡类型不能为空");
        }
        if (StringUtils.isBlank(rulePrice)){
            throw new BusinessException("价格不能为空");
        }
        if (StringUtils.isBlank(rulePay)){
            throw new BusinessException("实付金额不能为空");
        }
        if (StringUtils.isBlank(ruleCredit)){
            throw new BusinessException("积分不能为空");
        }
       /* if (StringUtils.isBlank(ruleStatus)){
            throw new BusinessException("状态不能为空");
        }*/

        ConsumOrder consumOrder=new ConsumOrder();
        consumOrder.setId(IntegerUtils.ToInteger(ruleId));
        consumOrder.setCardId(IntegerUtils.ToInteger(ruleCardId));
        consumOrder.setOrderId(IntegerUtils.ToInteger(ruleOrderId));
        consumOrder.setCardType(IntegerUtils.ToInteger(ruleCardType));
        consumOrder.setPrice((double)IntegerUtils.ToInteger(rulePrice));
        consumOrder.setPay((double)IntegerUtils.ToInteger(rulePay));
        consumOrder.setCredit(IntegerUtils.ToInteger(ruleCredit));
       // consumOrder.setStatus(IntegerUtils.ToInteger(ruleStatus));
        consumOrder.setMomo(ruleMOmo);
        consumOrder.setCreatedtime(ruleCreatedTime);

          int  len=oderDao.updateOderById(consumOrder);

        return len;
    }

    @Override
    /**
     * 修改订单状态
     */
    public int delOderService(Integer id) {
        OderDao oderDao=new OderDaoImpl();
        return oderDao.delOderById(id);
    }

    @Override
    /**
     * 根君CardId查找会员的
     * @param cardId
     * @param pageNumber
     * @param pageSize
     * @return
     */
    public List<Order> getOrderById(Integer cardId, Integer pageNumber, Integer pageSize) throws BusinessException {
        if(cardId==null && cardId==0){
            throw new BusinessException("cardId不能为空");
        }
        return oderDao.getOrderById(cardId,pageNumber,pageSize);
    }

    @Override

    /**
     * 查询条数
     * @param cardId
     * @return
     */
    public int getOrderByIdCount(Integer cardId) throws BusinessException {
        if(cardId==null && cardId==0){
            throw new BusinessException("cardId不能为空");
        }
        return oderDao.getOrderByIdCount(cardId);
    }
}
