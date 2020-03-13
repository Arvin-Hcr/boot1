package com.whqfl.dao;

import com.whqfl.entity.ConsumOrder;
import com.whqfl.entity.Goods;

import java.util.List;

public interface ConsumDao {

    /**
     * 减少器材数量
     */
    int delGoodsNumber(Integer goodsId, Integer code);
    /**
     * 新增器材订单
     */
    int insertOrder(ConsumOrder consumOrder);
    /**
     * 扣费
     */
    int consumerCard(Integer cardId, double pay);

    /**
     *  器材列表
     */

    List<Goods> consumeGoodsList(Integer pageNumber, Integer pageSize, String searchGoods, String searchName);
    /**
     * 获取器材列表数目
     */

    int getAllGoodsInfoCount(String searchGoods, String searchName);
}