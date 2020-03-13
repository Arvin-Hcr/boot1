package com.whqfl.dao;

import java.util.List;
import java.util.Map;

public interface CardManagerDao {
    /**
     * 转卡操作
     * @param userId 转卡人id
     * @param userName 的卡人名字
     * @param phone
     * @param sheng
     * @param shi
     * @return
     */
    int transferCard(String userId,String userName,String phone,String sheng,String shi,String idCard);

    /**
     * 获取等级
     * @return
     */
    List<Map<String,Object>> getVip();

    int updataParalleCar(String userCardId);

    /**
     * 并卡操作
     * @param userCardId1
     * @param userCardId
     * @param getMoney
     * @param getLevel
     * @param getFen
     * @return
     */
    int updataCard(String userCardId1, String userCardId,String getMoney, String getLevel,String getFen);



}
