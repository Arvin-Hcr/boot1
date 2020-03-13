package com.whqfl.service.impl;

import com.sun.deploy.security.ruleset.RuleId;
import com.whqfl.dao.RechargeRuleDao;
import com.whqfl.dao.impl.RechargeRuleDaoImpl;


import com.whqfl.entity.RechargeRule;
import com.whqfl.service.RechargeRuleService;
import com.whqfl.util.BusinessException;
import com.whqfl.util.IntegerUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RechargeRuleServiceImpl implements RechargeRuleService {
    private RechargeRuleDao rechargeRuleDao=new RechargeRuleDaoImpl();
    @Override
    /**
     * 获取充值规则列表
     */
    public Map<String, Object> getRechargeRuleService(Integer pageNumber, Integer pageSize, String searchStartTime, String searchEndTime, String searchName, Integer searchStatus) {
    pageNumber=(pageNumber-1)*pageSize;
        List<RechargeRule> rechargeRuleList = rechargeRuleDao.getRechargeRuleList(pageNumber, pageSize, searchStartTime, searchEndTime, searchName, searchStatus);
        int rechargeRuleCount = rechargeRuleDao.getRechargeRuleCount(searchStartTime, searchEndTime, searchName, searchStatus);
        Map<String,Object> map=new HashMap<>();
        map.put("list",rechargeRuleList);
        map.put("count",rechargeRuleCount);
        return map;
    }

    @Override
    /**
     * 新增修改规则
     */
    public int updateRechargeRulesById(String id, String ruleId, String ruleName, String ruleCoff, String ruleStatus, String ruleCreatedDate, String ruleEndDate, String ruleAmount) throws Exception {
        if (StringUtils.isBlank(ruleName)) {
            throw new BusinessException("ruleName不能为空");
        }
        if (StringUtils.isBlank(ruleCoff)) {
            throw new BusinessException("ruleCoff不能为空");
        }
        if (StringUtils.isBlank(ruleStatus)) {
            throw new BusinessException("ruleStatus不能为空");
        }
        if (StringUtils.isBlank(ruleEndDate)) {
            throw new BusinessException("ruleEndDate不能为空");
        }
        if (StringUtils.isBlank(ruleAmount)) {
            throw new BusinessException("ruleAmount不能为空");
        }
        RechargeRule rechargeRule = new RechargeRule();
        rechargeRule.setId(IntegerUtils.ToInteger(ruleId));
        rechargeRule.setStatus(IntegerUtils.ToInteger(ruleStatus));
        rechargeRule.setCoefficient(Double.parseDouble(ruleCoff));
        rechargeRule.setName(ruleName);
        rechargeRule.setStartMoney(ruleAmount);
        rechargeRule.setEndTime(ruleEndDate);
        rechargeRule.setCreatedTime(ruleCreatedDate);
        int i=0;
        if (StringUtils.isBlank(ruleId)){

        i = rechargeRuleDao.insertRechargeRule(rechargeRule);
        }else {
            i=rechargeRuleDao.updateRechargeRuleById(rechargeRule);
        }
        return i;
    }

    @Override
    /**
     * 删除规则
     */
    public int delRechargeRule(Integer id) {
        return rechargeRuleDao.delRechargeRule(id);
    }

    @Override
    /**
     * 获得所有的充值规则
     * @return
     */
    public List<RechargeRule> getAllRecharRule() {
       return rechargeRuleDao.getAllRecharRule();
    }

    @Override
    /**
     * 通过id获得充值系数
     * @param id
     * @return
     */
    public Double getRecharCoefficient(Integer id) throws BusinessException {
        if(id==0 && id==null){
            throw  new BusinessException("id不能为空");
        }
        return rechargeRuleDao.getRecharCoefficient(id);
    }


}
