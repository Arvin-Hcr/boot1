package com.whqfl.service;

import java.util.List;
import java.util.Map;

public interface CardManagerService {
    /**
     *
     * @param userId
     * @param userName
     * @param phone
     * @param sheng
     * @param shi
     * @param idCard
     * @return
     */
    int transferCard(String userId,String userName,String phone,String sheng,String shi,String idCard)throws Exception;

    /**
     * 等级
     * @return
     */
    List<Map<String,Object>> getVip() throws Exception;

    /**
     * 并卡
     * @param userCardId1
     * @param userCardId
     * @param getMoney
     * @param getLevel
     * @param getFen
     * @return
     * @throws Exception
     */
    int updataCard(String userCardId1, String userCardId,String getMoney, String getLevel,String getFen)throws Exception;
}
