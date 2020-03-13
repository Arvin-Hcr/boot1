package com.whqfl.dao.impl;

import com.whqfl.dao.OderDao;
import com.whqfl.entity.ConsumOrder;
import com.whqfl.entity.Order;
import com.whqfl.util.BaseDao;
import javafx.beans.binding.ObjectExpression;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Map;


public class OderDaoImpl implements OderDao {

private BaseDao baseDao=new BaseDao();
    @Override
    /**
     * 获取订单列表
     */
    public List<ConsumOrder> getOderList(Integer pagerNumber, Integer pagerSize, String searchOrderId) {
        String sql="select * from `order` where 1=1";
        if (StringUtils.isNotBlank(searchOrderId)){
            sql+=" and orderId="+searchOrderId;
        }

        sql+=" limit ?,?";
        Object[] params={pagerNumber,pagerSize};
        return baseDao.queryList(sql,params,ConsumOrder.class);

    }

    @Override
    /**
     * 获取订单数目
     */
    public int getOderCount(String searchOrderId) {
        String sql="select count(*) len from `order` where 1=1";
        if (StringUtils.isNotBlank(searchOrderId)){
            sql+=" and orderId="+searchOrderId;
        }
        List<Map<String,Object>> list = baseDao.executeQuery(sql, null);
        if (list!=null&&list.size()>0){
            return Integer.parseInt(list.get(0).get("len")+"");
        }

        return 0;
    }

    @Override
    /**
     * 添加备注
     */
    public int updateOderById(ConsumOrder consumOrder) {
        String sql="update `order` set orderId=?,cardId=?,cardType=?,price=?,pay=?,credit=?,momo=?,createdTime=? where id=?";
        Object[]params={consumOrder.getOrderId(),consumOrder.getCardId(),consumOrder.getCardType(),consumOrder.getPrice(),consumOrder.getPay(),consumOrder.getCredit(),consumOrder.getMomo(),consumOrder.getCreatedtime(),consumOrder.getId()};

        return baseDao.executeUpdate(sql,params);
    }



    @Override
    /**
     * 修改订单状态
     */
    public int delOderById(Integer id) {
        String sql="select * from `order` where id=?";
        Object[] params={id};
        List<Map<String,Object>> list = baseDao.executeQuery(sql, params);
        int status=0;
        if (list!=null&&list.size()>0){
            status=Integer.parseInt(list.get(0).get("status")+"");
        }
        status=status==0?1:0;
        String sql1="update `order` set status=? where id=?";
        Object[] params1={status,id};
        return baseDao.executeUpdate(sql1,params1);
    }

    @Override
    public List<Order> getOrderById(Integer cardId, Integer pageNumber, Integer pageSize) {
        String sql="select o.*,ct.name levelName from `order` o,cardtype ct where o.cardtype=ct.`level` and o.cardId=? limit ?,?";
        Object [] params={cardId,(pageNumber-1)*pageSize,pageSize};
        return baseDao.queryList(sql,params,Order.class);

    }

    @Override
    public int getOrderByIdCount(Integer cardId) {
        String sql="select o.*,ct.name levelName from `order` o,cardtype ct where o.cardtype=ct.`level` and o.cardId=?";
        Object [] params={cardId};
        List<Order> orderList=baseDao.queryList(sql,params,Order.class);
        int a=orderList.size();
        return a;
    }


}
