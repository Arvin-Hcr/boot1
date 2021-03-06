package com.whqfl.service;

import com.whqfl.entity.Card;
import com.whqfl.entity.CardType;
import com.whqfl.util.BusinessException;

import java.util.List;

public interface CardService {
    /**
     * 获得会员卡的所有等级
     * @return
     */
    List<CardType> getCardType();
    /**
     * 获得所有卡的信息
     * @param pageNumber
     * @param pageSize
     * @param searchName
     * @param searchId
     * @return
     */
    List<Card> getAllCardList(Integer pageNumber, Integer pageSize, String searchName, String searchId ) throws Exception;
    /**
     * 按照用户ID，名字查询卡的总条数
     * @param searchId
     * @param searchName
     * @return
     */
    int getAllCardCount(String searchId, String searchName);
    /**
     * 根据cardId改变cardId的状态
     * @param cardId
     * @return
     */
    int delCardStatus(Integer cardId) throws Exception;

    /**
     * 会员充值之后余额和积分对应改变
     * @param amount
     * @param credit
     * @param cardId
     * @return
     */
    int updateCardAmount(Double amount,Integer credit,Integer cardId) throws Exception;
    /**
     * 通过cardId获得卡的信息
     * @param cardID
     * @return
     */
    List<Card> getCardByCardId(Integer cardID) throws Exception;
    /**
     * 销卡
     * @param cardId
     * @return
     */
    int updateCardStatus(Integer cardId) throws Exception;



    /** 查询已注销的卡的信息
     * @param pageNumber
     * @param pageSize
     * @param searchName
     * @param searchId
     * @return
             */
    List<Card> getZhuXiaoCard(Integer pageNumber,Integer pageSize,String searchName,String searchId ) throws Exception;

    /**
     * 获得注销的卡的条数
     * @param searchId
     * @param searchName
     * @return
     */
    int getZhuCardCount(String searchId, String searchName);

    /**
     * 补卡
     * @param cardId
     * @return
     */
    int updateZhuCardStatus(Integer cardId) throws Exception;
}
