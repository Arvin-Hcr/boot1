package com.whqfl.service.impl;

import com.whqfl.dao.GoodsDao;
import com.whqfl.dao.GoodsDao1;
import com.whqfl.dao.impl.GoodsDaoImpl;
import com.whqfl.dao.impl.GoodsDaoImpl1;
import com.whqfl.entity.CateGory;
import com.whqfl.entity.Goods;
import com.whqfl.entity.Unit;
import com.whqfl.service.GoodsService1;
import com.whqfl.util.BusinessException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GoodsServiceImpl1 implements GoodsService1 {
    private GoodsDao1 goodsDao1 = new GoodsDaoImpl1();
    private GoodsDao goodsDao =new GoodsDaoImpl();

    @Override
    public Map<String, Object> getAllGoodsInfo(Integer pageNumber, Integer pageSize, String searchId, String searchName) throws Exception {
        if (pageNumber == null || pageNumber == 0 )  {
            throw new BusinessException("当前页数不能为空");
        }
        if (pageSize == null||pageSize==0) {
            throw new BusinessException("每页条数不能为空");
        }
        pageNumber=(pageNumber-1)*pageSize;

        List<Goods> list= goodsDao1.getAllGoodsInfo( pageNumber,  pageSize, searchId, searchName);
        int count=goodsDao1.getAllGoodsInfoCount(searchId,searchName);
        Map<String,Object> map=new HashMap<>();
        map.put("list",list);
        map.put("count",count);
        return map;
    }

    @Override
    public int delGoods(Integer goodsId, Integer status) throws Exception {
        if (goodsId == null || goodsId == 0) {
            throw new BusinessException("器材编号不能为空");
        }
        return goodsDao.delGoods(goodsId,status);
    }

    @Override
    public int updateGoodsByGoodsId(String goodsId, String name, String code, Integer type,  double price) throws Exception {
        if (goodsId == null || goodsId == "") {
            throw new BusinessException("器材编号不能为空");
        }
       /* if (unitName == null ||unitName == ""){
            throw new BusinessException("器材编号不能为空");
        }*/
        Goods goods = new Goods();
        goods.setGoodsId(goodsId);
        goods.setName(name);
        goods.setCode(code);
        /* goods.setUnitId(Integer.parseInt(unitName));*/
        goods.setPrice(price);
        goods.setCategoryId(type);
        return goodsDao.updateGoodsByGoodsId(goods);
    }
    @Override
    public List<CateGory> getAllCategory() {
        List<CateGory> list= goodsDao.getAllCategory();
        return list;
    }

    @Override
    public List<Unit> getAllUnit() {
        return goodsDao.getAllUnit();

    }
    /**增加器材
     @autor lxx
     @date   2019/12/28
     */
    @Override
    public int addGoods(String goodsId, String name, String code,   double price,String categoryName)throws Exception {
        if (goodsId == null || goodsId == "") {
            throw new BusinessException("器材编号不能为空");
        }

        Goods goods = new Goods();
        goods.setGoodsId(goodsId);
        goods.setName(name);
        goods.setCode(code);
        goods.setCategoryId(Integer.parseInt(categoryName));
        /* goods.setUnitId(Integer.parseInt(unitName));*/
        goods.setPrice(price);
        /*goods.setType(type);*/
        return goodsDao.addGoods(goods);
    }

    @Override
    public int getLastGoodsId() {
        return Integer.parseInt(goodsDao.getLastGoodsId())+1;
    }


}
