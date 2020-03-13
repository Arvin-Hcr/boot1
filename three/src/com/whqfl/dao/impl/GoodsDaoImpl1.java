package com.whqfl.dao.impl;

import com.whqfl.dao.GoodsDao1;
import com.whqfl.entity.CateGory;
import com.whqfl.entity.Goods;
import com.whqfl.entity.Unit;
import com.whqfl.util.BaseDao;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Map;


public class GoodsDaoImpl1 implements GoodsDao1 {
    private BaseDao baseDao = new BaseDao();
    @Override
    public List<Goods> getAllGoodsInfo(Integer pageNumber, Integer pageSize, String searchId, String searchName) {


        String sql = "select g.*,u.name unitName,c.`name` categoryName from goods g left join unit u on g.unitId= u.id left join " +
                "category c on g.categoryId=c.id where 1=1 and g.status=1";

        if (StringUtils.isNotBlank(searchId)) {
            sql += " and g.goodsId = " + searchId;
        }
        if (StringUtils.isNotBlank(searchName)) {
            sql += " and g.name like '%" + searchName+"%'";
        }
        sql += " order by g.goodsId asc limit ?,?";
        Object[] params = {pageNumber, pageSize};
        List<Goods> list = baseDao.queryList(sql, params, Goods.class);
        return list;
    }

    @Override
    public int getAllGoodsInfoCount(String goodsId, String searchName) {
        String sql = "select count(1) len from goods where status=1 and 1=1 ";
        if (StringUtils.isNotBlank(goodsId)) {
            sql += " and goodsId = " + goodsId;
        }
        if (StringUtils.isNotBlank(searchName)) {
            searchName = "%" + searchName + "%";
            sql += " and name like '" + searchName + "'";
        }
        List<Map<String, Object>> mapList = baseDao.executeQuery(sql, null);
        if (mapList != null && mapList.size() > 0) {
            return Integer.parseInt(mapList.get(0).get("len") + "");
        }
        return 0;
    }

    @Override
    public int delGoods(Integer goodsId, Integer status) {
        status = status == 1 ? 2 : 1;
        String sql = "update goods set status = ? where goodsId = " + goodsId;
        Object[] params = {status};
        int len = baseDao.executeUpdate(sql, params);
        return len;
    }


    @Override
    public List<CateGory> getAllCategory() {
        String sql="select * from " +
                " category" +
                " where 1=1 ";
        Object[] pas={};
        List<CateGory> list=baseDao.queryList(sql,pas,CateGory.class);
        return list;
    }

    @Override
    public int updateGoodsByGoodsId(Goods goods) {
        String sql = "update goods set name = ?,code = ?,categoryId = ?,price = ? where goodsId = ?";
        Object[] params = {goods.getName(),goods.getCode(),goods.getCategoryId(),goods.getPrice()
                ,goods.getGoodsId()};
        return baseDao.executeUpdate(sql, params);
    }

    @Override
    public List<CateGory> GetGoodsCategory() {
        String sql = "select * from category where status = 1";
        List<CateGory> cateGoryList = baseDao.executeQuery(sql,null);
        return cateGoryList;
    }


    @Override
    public List<Unit> getAllUnit() {
        String sql = "select * from unit where status = 1";
        List<Unit> units = baseDao.executeQuery(sql,null);
        return units;
    }

    @Override
    public int  addGoods(Goods goods) {
        String sql = "insert into goods(goodsId,name,code,type,price,categoryId) values (?,?,?,?,?,?)";
        Object[] params={goods.getGoodsId(),goods.getName(),goods.getCode(),goods.getType(),goods.getPrice(),goods.getCategoryId()};
        int len = baseDao.executeUpdate(sql,params);
        return len;

    }

    @Override
    public String getLastGoodsId() {
        String sql = "SELECT goodsId from goods order by goodsId desc LIMIT 1";
        List<Map<String,Object>> list = baseDao.executeQuery(sql,null);
        if (list != null && list.size() > 0) {
            String cardId = list.get(0).get("goodsId") + "";
            return cardId;
        }
        return null;
    }

}
