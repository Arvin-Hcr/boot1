package com.whqfl.dao.impl;

import com.whqfl.dao.RechargerecordDao;
import com.whqfl.entity.Rechargerecord;
import com.whqfl.util.BaseDao;

import java.util.List;

public class RechargerecordImpl implements RechargerecordDao {
    private BaseDao baseDao=new BaseDao();
    @Override
    public int addRechargerecord(Rechargerecord rechargerecord) {
        String sql="insert into rechargerecord (cardId,rechargeAmount,afterAmount,beforeAmount,ruleId,createdTime,staffId,momo) values(?,?,?,?," +
                "?,?,?,?)";
        Object [] params={rechargerecord.getCardId(),rechargerecord.getRechargeAmount(),rechargerecord.getAfterAmount(),rechargerecord.getBeforeAmount(),
        rechargerecord.getRuleId(),rechargerecord.getCreatedTime(),rechargerecord.getStaffId(),rechargerecord.getMomo()};
        return baseDao.executeUpdate(sql,params);
    }

    @Override
    public List<Rechargerecord> getRechargerecordBycardId(Integer cardId,Integer pageNumber,Integer pageSize) {
        String sql="select u.userName,r.* from rechargerecord r,user u where r.cardId=u.cardId and r.cardId=? limit ?,?";
        Object [] params={cardId,(pageNumber-1)*pageSize,pageSize};
        return  baseDao.queryList(sql,params,Rechargerecord.class);
    }

    @Override
    public int getRechargerecordBycardIdCount(Integer cardId) {
        String sql="select u.userName,r.* from rechargerecord r,user u where r.cardId=u.cardId and r.cardId=?";
        Object [] params={cardId};
        List<Rechargerecord> rechargerecords=baseDao.queryList(sql,params,Rechargerecord.class);
        return rechargerecords.size();
    }


}
