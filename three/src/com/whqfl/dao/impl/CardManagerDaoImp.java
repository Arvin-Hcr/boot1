package com.whqfl.dao.impl;

import com.whqfl.dao.CardManagerDao;
import com.whqfl.util.BaseDao;

import java.util.List;
import java.util.Map;


public class CardManagerDaoImp implements CardManagerDao {
    private BaseDao baseDao=new BaseDao();
    @Override
    public int transferCard(String userId, String userName, String phone, String sheng, String shi, String idCard) {
        String sql="update `user` set userName=? , idCard=? , address=? ,area=? ,phone=?  where  cardId=?";
        Object[]objects={userName,idCard,sheng,shi,phone,userId};
        int i = baseDao.executeUpdate(sql, objects);
        return i;
    }

    @Override
    public List<Map<String, Object>> getVip() {
        String sql="select level ,rank,name from cardtype ";
        return baseDao.executeQuery(sql,null);

    }

    @Override
    public int updataParalleCar(String userCardId) {
        String sql="update card set status=2 where cardId=?";
        Object[]objects={userCardId};
        return  baseDao.executeUpdate(sql, objects);
    }

    @Override
    public int updataCard(String userCardId1, String userCardId, String getMoney, String getLevel, String getFen) {
    String sql="update card set amount=?,credit=?,levelId=?  where  cardId=?";
    Object[]objects={getMoney,getFen,getLevel,userCardId1};
        int i = baseDao.executeUpdate(sql, objects);
        return i;
    }
}
