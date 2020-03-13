package com.whqfl.dao.impl;

import com.whqfl.dao.RechargeRuleDao;

import com.whqfl.dao.RechargerecordDao;
import com.whqfl.entity.RechargeRule;
import com.whqfl.service.RechargeRuleService;
import com.whqfl.util.BaseDao;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Map;

public class RechargeRuleDaoImpl implements RechargeRuleDao {
    private BaseDao baseDao=new BaseDao();
    @Override
    /**
     * 获取所有充值规则
     */
    public List<RechargeRule> getRechargeRuleList(Integer pageNumber, Integer pageSize, String searchStartTime, String searchEndTime, String searchName, Integer searchStatus) {
        String sql=" select * from rechargerule where 1=1";
        if (StringUtils.isNotBlank(searchStartTime)&&StringUtils.isNotBlank(searchEndTime)){
            sql+=" and createdTime between '"+searchStartTime+"' and '"+searchEndTime+"'";
        }
        if (StringUtils.isNotBlank(searchName)){
            sql+=" and name like '%"+searchName+"%'";
        }
        if (searchStatus!=null&&searchStatus!=-1){
        sql+=" and status="+searchStatus;
        }
        sql+=" limit ?,?";
        Object[]params={pageNumber,pageSize};
        return baseDao.queryList(sql,params,RechargeRule.class);
    }
    /**
     * 获取规则数目
     */
    @Override
    public int getRechargeRuleCount(String searchStartTime, String searchEndTime, String searchName, Integer searchStatus) {
        String sql="select count(1) len from rechargeRule where 1=1";
        if (StringUtils.isNotBlank(searchStartTime)&&StringUtils.isNotBlank(searchEndTime)){
            sql+=" and createdTime between '"+searchStartTime+"' and '"+searchEndTime+"'";
        }
        if (StringUtils.isNotBlank(searchName)){
            sql+=" and name like '%"+searchName+"%'";
        }
        if (searchStatus!=null&&searchStatus!=-1){
            sql+=" and status="+searchStatus;
        }
        List<Map<String,Object>> list = baseDao.executeQuery(sql, null);
        if (list!=null&&list.size()>0){
            return Integer.parseInt(list.get(0).get("len")+"");
        }
        return 0;
    }
    /**
     * 更新规则
     */
    @Override
    public int updateRechargeRuleById(RechargeRule rechargeRule) {
        String sql="update rechargerule set name=?,coefficient=?,createdTime=?,endTime=?,status=?,startMoney=? where id=?";
        Object[] parames={rechargeRule.getName(),rechargeRule.getCoefficient(),rechargeRule.getCreatedTime(),rechargeRule.getEndTime(),rechargeRule.getStatus(),rechargeRule.getStartMoney(),rechargeRule.getId()};
        int update = baseDao.executeUpdate(sql, parames);
        return update;
    }
    /**
     * 新增规则
     */
    @Override
    public int insertRechargeRule(RechargeRule rechargeRule) {
        String sql="insert rechargerule (name,coefficient,status,startMoney,endTime,createdTime) values (?,?,?,?,?,?)";
        Object[] params = {rechargeRule.getName(), rechargeRule.getCoefficient(), rechargeRule.getStatus(),
                rechargeRule.getStartMoney(), rechargeRule.getEndTime(), rechargeRule.getCreatedTime()};
        int i = baseDao.executeUpdate(sql, params);
        return i;
    }
    /**
     * 删除规则
     */
    @Override
    public int delRechargeRule(Integer id) {
        String sql="select status from rechargerule where id=?";
        Object[] params={id};
        List<Map<String,Object>> list = baseDao.executeQuery(sql, params);
        int status=0;
        if (list!=null&&list.size()>0){
            status=Integer.parseInt(list.get(0).get("status")+"");
        }
        sql="update rechargerule set status=? where id=?";
        status= status ==0  ? 1 : 0;
        Object[] params1={status,id};
        int i = baseDao.executeUpdate(sql, params1);
        return i;
    }

    @Override
    public List<RechargeRule> getAllRecharRule() {
        String sql="select * from rechargerule where status=1";
        List<RechargeRule> list = baseDao.queryList(sql, null, RechargeRule.class);
        return  list;
    }

    @Override
    public Double getRecharCoefficient(Integer id) {
        String sql="select coefficient from rechargerule where id=?";
        Object [] params={id};
        List<RechargeRule> rechargeRules=baseDao.queryList(sql,params, RechargeRule.class);
        if (rechargeRules!=null && rechargeRules.size()>0){
            return rechargeRules.get(0).getCoefficient();
        }
        return null;
    }


}
