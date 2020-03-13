package com.whqfl.service;

import com.whqfl.entity.CateGory;
import com.whqfl.entity.Unit;

import java.util.List;
import java.util.Map;

public interface GoodsService1 {
    /**
     * 分页查询所有的器材信息
     */
    Map<String,Object> getAllGoodsInfo(Integer pageNumber, Integer pageSize, String searchId, String searchName)throws Exception;

    /**
     * 下架器材
     */
    int delGoods(Integer goodsId ,Integer status)throws Exception;
    /**
     * 修改器材
     */
    int updateGoodsByGoodsId(String goodsId,String name,String code,Integer type,double price) throws Exception ;

    /**获取单位所有的类别
     */
    List<Unit> getAllUnit();
    List<CateGory> getAllCategory();

    /**增加器材
     */
    int addGoods(String goodsId,String name,String code,double price,String categoryName)throws Exception;


    /**
     * 获取最后一位器材号
     * @return
     */
    int getLastGoodsId();
}
