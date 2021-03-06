package com.whqfl.dao.impl;

import com.whqfl.dao.StatisticsDao;
import com.whqfl.util.BaseDao;

import java.util.List;
import java.util.Map;

public class StatisticsDaoImp implements StatisticsDao {
    private BaseDao baseDao=new BaseDao();
    @Override
    public List<Map<String, Object>> getNearOneYearUser() {
        String sql="select count(*) len ,MONTH(createdTime) mouth from user " +
                "where  createdTime is not NULL and createdTime BETWEEN DATE_SUB(CURDATE(),INTERVAL 12 MONTH) " +
                "and DATE_SUB(CURDATE(), INTERVAL -1 DAY)   " +
                "GROUP BY MONTH(createdTime)";
        List list = baseDao.executeQuery(sql, null);
        return list;
    }

    /**
     * 这个是普通会员的近一年注册的数量
     * @return
     */
    @Override
    public List<Map<String, Object>> getCommonUser() {
        String sql="select count(*) len,MONTH(u.createdTime) month " +
                " from user u,card c" +
                " where u.cardId=c.cardId and c.levelId=1 and " +
                "u.createdTime BETWEEN DATE_SUB(CURDATE(), INTERVAL 12 MONTH) and DATE_SUB(CURDATE(), INTERVAL -1 DAY) " +
                "GROUP BY MONTH(createdTime)";
        List list = baseDao.executeQuery(sql, null);
        return list;
    }

    /**
     * 青铜
     * @return
     */
    @Override
    public List<Map<String, Object>> getQinTong() {
        String sql="select count(*) len,MONTH(u.createdTime) month " +
                " from user u,card c" +
                " where u.cardId=c.cardId and c.levelId=2 and " +
                "u.createdTime BETWEEN DATE_SUB(CURDATE(), INTERVAL 12 MONTH) and DATE_SUB(CURDATE(), INTERVAL -1 DAY) " +
                "GROUP BY MONTH(createdTime)";
        List list = baseDao.executeQuery(sql, null);
        return list;
    }

    /**
     * 白银
     * @return
     */
    @Override
    public List<Map<String, Object>> getBaiYin() {
        String sql="select count(*) len,MONTH(u.createdTime) month " +
                " from user u,card c" +
                " where u.cardId=c.cardId and c.levelId=3 and " +
                "u.createdTime BETWEEN DATE_SUB(CURDATE(), INTERVAL 12 MONTH) and DATE_SUB(CURDATE(), INTERVAL -1 DAY) " +
                "GROUP BY MONTH(createdTime)";
        List list = baseDao.executeQuery(sql, null);
        return list;
    }

    /**
     * 黄金
     * @return
     */
    @Override
    public List<Map<String, Object>> getHuangJinUser() {
        String sql="select count(*) len,MONTH(u.createdTime) month " +
                " from user u,card c" +
                " where u.cardId=c.cardId and c.levelId=4 and " +
                "u.createdTime BETWEEN DATE_SUB(CURDATE(), INTERVAL 12 MONTH) and DATE_SUB(CURDATE(), INTERVAL -1 DAY) " +
                "GROUP BY MONTH(createdTime)";
        List list = baseDao.executeQuery(sql, null);
        return list;
    }

    /**
     * 铂金
     * @return
     */
    @Override
    public List<Map<String, Object>> getBoJin() {
        String sql="select count(*) len,MONTH(u.createdTime) month " +
                " from user u,card c" +
                " where u.cardId=c.cardId and c.levelId=5 and " +
                "u.createdTime BETWEEN DATE_SUB(CURDATE(), INTERVAL 12 MONTH) and DATE_SUB(CURDATE(), INTERVAL -1 DAY) " +
                "GROUP BY MONTH(createdTime)";
        List list = baseDao.executeQuery(sql, null);
        return list;
    }

    @Override
    public List<Map<String, Object>> getCountreChargerecord() {
        String sql="select SUM(rechargeAmount) count,MONTH(createdTime) mon from rechargerecord " +
                "where createdTime BETWEEN DATE_SUB(CURDATE(),INTERVAL 12 MONTH) AND DATE_SUB(CURDATE(), INTERVAL -1 DAY) " +
                "GROUP  BY MONTH(createdTime)";
        List list = baseDao.executeQuery(sql, null);
        return list;
    }

    @Override
    public List<Map<String, Object>> getOrder() {
        String sql="select sum(pay) sum,count(*) count,month(createdTime) mon from `order` where `status`=1 " +
                "and createdTime between DATE_SUB(createdTime,interval 12 month) AND DATE_SUB(CURDATE(), INTERVAL -1 DAY) " +
                "GROUP BY MONTH(createdTime)";
        List<Map<String,Object>> list = baseDao.executeQuery(sql, null);
        return list;
    }


    @Override
    public List<Map<String, Object>> getRechargeAmount() {
        String sql=" select SUM(rechargeAmount) sum,SUBSTRING(createdTime FROM 6 FOR 2) mon " +
                "from rechargerecord GROUP BY SUBSTRING(createdTime FROM 6 FOR 2)";
        List <Map<String, Object>> list = baseDao.executeQuery(sql, null);
        return list;
    }

    @Override
    public List<Map<String, Object>> getVipO() {
    String sql="select card.name name,IFNULL(card1.count,0) value" +
            " from cardtype card left join " +
            "(select count(*) count,c2.levelId li from (select cardId from user where createdTime is not null and createdTime BETWEEN DATE_SUB(CURDATE(),INTERVAL 12 MONTH) and DATE_SUB(CURDATE(), INTERVAL -1 DAY))c1,card c2 where c1.cardId=c2.cardId GROUP BY c2.levelId)" +
            " card1 on card.`level`=card1.li";
        List <Map<String, Object>> list = baseDao.executeQuery(sql, null);
        return list;
    }
}
