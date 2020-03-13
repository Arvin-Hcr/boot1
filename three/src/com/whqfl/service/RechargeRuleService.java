package com.whqfl.service;

import com.whqfl.entity.RechargeRule;
import com.whqfl.util.BusinessException;

import java.util.List;
import java.util.Map;
public interface RechargeRuleService {
    /**
     * 获取充值规则列表
     */
    Map<String,Object> getRechargeRuleService(Integer pageNumber,Integer pageSize,String searchStartTime,
                                              String searchEndTime,String searchName,Integer searchStatus);
    /**
     * 新增修改规则
     */
    int updateRechargeRulesById(String id, String ruleId, String ruleName, String ruleCoff, String ruleStatus, String ruleCreatedDate, String ruleEndDate, String ruleAmount)throws Exception;
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
    Double getRecharCoefficient(Integer id) throws Exception;
}
