package com.whqfl.dao;

import com.whqfl.entity.Card;
import com.whqfl.entity.CardType;
import com.whqfl.util.IntegerUtils;

import java.util.List;
import java.util.Map;

public interface CardDao {
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
    List<Card> getAllCardList(Integer pageNumber,Integer pageSize,String searchName,String searchId );
    /**
     * 按照用户ID，名字查询总条数
     * @param searchId
     * @param searchName
     * @return
     */
    int getAllCardCount(String searchId, String searchName);

    /**
     * 根据cardId改变cardId的状态
     * @param card
     * @return
     */
    int delCardStatus(Card card);

    /**
     * 会员充值之后余额和积分对应改变
     * @return
     */
    int updateCardAmount(Card card);

    /**
     * 通过cardId获得卡的信息
     * @param cardID
     * @return
     */
    List<Card> getCardByCardId(Integer cardID);

    /**
     * 销卡
     * @param cardId
     * @return
     */
    int updateCardStatus(Integer cardId);

    /**
     * 查询已注销的卡的信息
     * @param pageNumber
     * @param pageSize
     * @param searchName
     * @param searchId
     * @return
     */
    List<Card> getZhuXiaoCard(Integer pageNumber,Integer pageSize,String searchName,String searchId );
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
    int updateZhuCardStatus(Integer cardId);
}
