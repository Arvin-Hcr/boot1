package com.whqfl.dao;



import com.whqfl.entity.RechargeRule;

import java.util.List;

public interface RechargeRuleDao {
    /**
     * 获取所有充值规则
     */
    List<RechargeRule> getRechargeRuleList(Integer pageNumber, Integer pageSize, String searchStartTime, String searchEndTime, String searchName, Integer searchStatus);
    /**
     * 获取规则数目
     */
    int getRechargeRuleCount(String searchStartTime, String searchEndTime, String searchName, Integer searchStatus);
    /**
     * 更新规则
     */

    int updateRechargeRuleById(RechargeRule rechargeRule);
    /**
     * 新增规则
     */

    int insertRechargeRule(RechargeRule rechargeRule);
    /**
     * 删除规则
     */
    int delRechargeRule(Integer id);

    /**
     * 获得所有的充值规则
     * @return
     */
    List<RechargeRule> getAllRecharRule();

    /**
     * 通过id获得充值系数
     * @param id
     * @return
     */
    Double getRecharCoefficient(Integer id);
}

