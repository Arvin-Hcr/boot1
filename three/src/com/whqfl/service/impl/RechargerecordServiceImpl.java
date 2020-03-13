package com.whqfl.service.impl;

import com.whqfl.dao.RechargerecordDao;
import com.whqfl.dao.impl.RechargerecordImpl;
import com.whqfl.entity.Rechargerecord;
import com.whqfl.service.RechargerecordService;
import com.whqfl.util.BusinessException;
import com.whqfl.util.DateUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
public class RechargerecordServiceImpl implements RechargerecordService {
    private RechargerecordDao rechargerecordDao=new RechargerecordImpl();
    private Rechargerecord rechargerecord=new Rechargerecord();
    @Override
    public int addRechargerecord(Integer cardid, Double rechargeamount, Double afteramount, Double beforeamount, Integer ruleid, String createdtime, Integer staffid, String momo) throws BusinessException {
        if(cardid==null && cardid==0){
            throw  new BusinessException("cardid不能为空");
        }
        if(ruleid==null && ruleid==0){
            throw  new BusinessException("ruleid不能为空");
        }
        rechargerecord.setCardId(cardid);
        rechargerecord.setRechargeAmount(rechargeamount);
        rechargerecord.setAfterAmount(afteramount);
        rechargerecord.setBeforeAmount(0.0);
        rechargerecord.setRuleId(ruleid);
        rechargerecord.setCreatedTime(DateUtils.toFormat(new Date()));
        rechargerecord.setStaffId(staffid);
        rechargerecord.setMomo("新增会员卡");
        return rechargerecordDao.addRechargerecord(rechargerecord);
    }

    @Override
    public int addUserRechargerecord(Integer cardid, Double rechargeamount, Double afteramount, Double beforeamount, Integer ruleid, String createdtime, Integer staffid, String momo) throws Exception {
        if(cardid==0 && cardid==null){
            throw  new BusinessException("cardId不能为空");
        }
        if(staffid==0 && staffid==null){
            throw  new BusinessException("staffId不能为空");
        }
        if(rechargeamount ==0.0){
            throw  new BusinessException("充值记录不能为空");
        }
        rechargerecord.setCardId(cardid);
        rechargerecord.setRechargeAmount(rechargeamount);
        rechargerecord.setAfterAmount(afteramount);
        rechargerecord.setBeforeAmount(beforeamount);
        rechargerecord.setRuleId(ruleid);
        rechargerecord.setCreatedTime(DateUtils.getDateToStringFormat(new Date()));
        rechargerecord.setStaffId(staffid);
        rechargerecord.setMomo("卡号为"+cardid+"的用户"+"冲了"+rechargeamount+"元，"+"赠送了"+(afteramount-beforeamount-rechargeamount)+"元，余额为"+afteramount+","+momo);
        return  rechargerecordDao.addRechargerecord(rechargerecord);

    }

    @Override
    public List<Rechargerecord> getRechargerecordBycardId(Integer cardId,Integer pageNumber,Integer pageSize) throws BusinessException {
        if(cardId==0 && cardId==null){
            throw new BusinessException("cardId不能为空");
        }
        if(pageNumber==0 && pageNumber==null){
            throw new BusinessException("pageNumber不能为空");
        }
        if(pageSize==0 && pageSize==null){
            throw new BusinessException("pageSize不能为空");
        }
        return rechargerecordDao.getRechargerecordBycardId(cardId,pageNumber,pageSize);
    }

    @Override
    public int getRechargerecordBycardIdCount(Integer cardId) throws BusinessException {
        if(cardId==null && cardId==null){
            throw new BusinessException("cardId不能为空");
        }
        return rechargerecordDao.getRechargerecordBycardIdCount(cardId);
    }


}
