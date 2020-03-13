package com.whqfl.service.impl;

import com.whqfl.dao.CardManagerDao;
import com.whqfl.dao.impl.CardManagerDaoImp;
import com.whqfl.service.CardManagerService;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Map;

public class CardManagerServiceImp implements CardManagerService {
    private CardManagerDao cardManagerDao=new CardManagerDaoImp();
    @Override
    public int transferCard(String userId, String userName, String phone, String sheng, String shi, String idCard)throws Exception {
        if(StringUtils.isBlank(userId)){
            throw new Exception("转卡人id不能为空");
        }

        if(StringUtils.isBlank(userName)){
            throw new Exception("得卡人姓名不能为空");
        }

        if(StringUtils.isBlank(phone)){
            throw new Exception("数据有误");
        }

        if(StringUtils.isBlank(sheng)){
            throw new Exception("数据有误");
        }
        if(StringUtils.isBlank(shi)){
            throw new Exception("数据有误");
        }
        if(StringUtils.isBlank(idCard)){
            throw new Exception("数据有误");
        }

        int i = cardManagerDao.transferCard(userId, userName, phone, sheng, shi, idCard);
        if(i==0){
            throw new Exception("数据有误");
        }

        return i;
    }

    @Override
    public List<Map<String, Object>> getVip() throws Exception{
        List<Map<String, Object>> vip = cardManagerDao.getVip();
        if(vip==null||vip.size()==0){
            throw  new Exception("数据获取有误");
        }
        return vip;
    }

    @Override
    public int updataCard(String userCardId1, String userCardId, String getMoney, String getLevel, String getFen) throws Exception {
        int res=0;
        res = cardManagerDao.updataParalleCar(userCardId);

        if(res==0){
            throw new Exception("数据有误");
        }
        res=cardManagerDao.updataCard(userCardId1,userCardId,getMoney,getLevel,getFen);
        if(res==0){
            throw new Exception("数据有误");
        }
        return res;
    }
}
