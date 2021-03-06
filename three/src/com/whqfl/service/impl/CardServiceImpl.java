package com.whqfl.service.impl;

import com.whqfl.dao.CardDao;
import com.whqfl.dao.impl.CardDaoImpl;
import com.whqfl.entity.Card;
import com.whqfl.entity.CardType;
import com.whqfl.service.CardService;
import com.whqfl.util.BusinessException;

import java.util.List;

public class CardServiceImpl implements CardService {
    private CardDao cardDao=new CardDaoImpl();
    private  Card card=new Card();
    @Override
    public List<CardType> getCardType() {
        return cardDao.getCardType();
    }

    @Override
    public List<Card> getAllCardList(Integer pageNumber, Integer pageSize, String searchName, String searchId) throws BusinessException {
        if(pageNumber == 0 && pageNumber == null){
            throw new BusinessException("当前页不能为空");
        }
        if(pageSize == 0 && pageSize == null){
            throw new BusinessException("页数不能为空");
        }
        return cardDao.getAllCardList(pageNumber,pageSize,searchName,searchId);

    }

    @Override
    public int getAllCardCount(String searchId, String searchName) {
        return cardDao.getAllCardCount(searchId,searchName);
    }

    @Override
    public int delCardStatus(Integer cardId) throws BusinessException {
        if(cardId==0 && cardId==null){
            throw new BusinessException("cardId不能为空");
        }
        card.setCardId(cardId);
        return cardDao.delCardStatus(card);
    }

    @Override
    public int updateCardAmount(Double amount, Integer credit, Integer cardId) throws BusinessException {
        if(amount==0.0){
            throw  new BusinessException("amount不能为空");
        }
        if(credit==0 && credit==null){
            throw  new BusinessException("credit不能为空");
        }
        if(cardId ==0 && cardId==null){
            throw  new BusinessException("cardId不能为空");
        }
        card.setAmount(amount);
        card.setCredit(credit);
        card.setCardId(cardId);
        return cardDao.updateCardAmount(card);
    }

    @Override
    public List<Card> getCardByCardId(Integer cardID) throws BusinessException {
        if(cardID==0 && cardID==null){
            throw new BusinessException("cardId不能为空");
        }
        return cardDao.getCardByCardId(cardID);
    }

    @Override
    public int updateCardStatus(Integer cardId) throws BusinessException {
        if(cardId==0 && cardId==null){
            throw new BusinessException("cardId不能为空");
        }
        return cardDao.updateCardStatus(cardId);
    }

    @Override
    public List<Card> getZhuXiaoCard(Integer pageNumber, Integer pageSize, String searchName, String searchId) throws BusinessException {
        if(pageNumber == 0 && pageNumber == null){
            throw new BusinessException("当前页不能为空");
        }
        if(pageSize == 0 && pageSize == null){
            throw new BusinessException("页数不能为空");
        }
        return cardDao.getZhuXiaoCard(pageNumber,pageSize,searchName,searchId);
    }

    @Override
    public int getZhuCardCount(String searchId, String searchName) {
        return cardDao.getZhuCardCount(searchId,searchName);
    }

    @Override
    public int updateZhuCardStatus(Integer cardId) throws BusinessException {
        if(cardId==null && cardId==0){
            throw new BusinessException("cardId不能为空");
        }
        return cardDao.updateZhuCardStatus(cardId);
    }

}
