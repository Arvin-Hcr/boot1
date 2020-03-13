package com.whqfl.dao;

import com.whqfl.entity.CateGory;
import com.whqfl.entity.Goods;
import com.whqfl.entity.Unit;

import java.util.List;

/**
 */
public interface GoodsDao1 {
    /**分页查询所有的器材信息
     */
    List<Goods> getAllGoodsInfo(Integer pageNumber, Integer pageSize, String searchGoods, String searchName);
    /**
     * 查询器材的总条数
     */
    int getAllGoodsInfoCount(String searchGoods,String searchName);
    /**
     * 下架器材
     */
    int delGoods(Integer goodsId ,Integer status);

    List<CateGory> getAllCategory();

    /**
     * 增加器材
     */
    int addGoods(Goods goods);

    /**
     * 获取最后一位器材号
     */
    String getLastGoodsId();

    /**修改器材信息
     */
    int updateGoodsByGoodsId(Goods goods);
    /**
     *器材类别
     */
    List<CateGory> GetGoodsCategory();



    /**获取单位所有的类别

     */
    List<Unit> getAllUnit();


}
