package com.whqfl.dao.impl;

import com.whqfl.dao.CardDao;
import com.whqfl.entity.Card;
import com.whqfl.entity.CardType;
import com.whqfl.entity.User;
import com.whqfl.util.BaseDao;
import javafx.beans.binding.ObjectExpression;
import jdk.internal.org.objectweb.asm.tree.MultiANewArrayInsnNode;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class CardDaoImpl implements CardDao {
    private BaseDao baseDao=new BaseDao();
    @Override
    public List<CardType> getCardType() {
        String sql="select * from cardtype where status=1";
        List<CardType> cardTypeList= baseDao.queryList(sql,null,CardType.class);
        if(cardTypeList!=null && cardTypeList.size()>0){
            return  cardTypeList;
        }

        return null;
    }

    @Override
    public List<Card> getAllCardList(Integer pageNumber, Integer pageSize, String searchName, String searchId) {
        String sql="select c.*,u.userName,ct.`name` levelName from user u, card c,cardtype ct where u.cardId=c.cardId and ct.id=c.levelId and c.`status` !=2";
        if(!StringUtils.isBlank(searchId)){
            sql+=" and c.cardId= "+searchId;
        }
        if(!StringUtils.isBlank(searchName)){
            sql+=" and u.userName like '%"+searchName+"%'";
        }
        sql+=" limit ?,?";
        Object [] params={(pageNumber-1)*pageSize,pageSize};
        List<Card> cardList=baseDao.queryList(sql,params,Card.class);
        if(cardList!=null && cardList.size()>0){
            return cardList;
        }

        return null;
    }

    @Override
    public int getAllCardCount(String searchId, String searchName) {
        String sql="select c.*,u.userName,ct.`name` levelName from user u, card c,cardtype ct where u.cardId=c.cardId and ct.id=c.levelId and c.`status` !=2";
        if(!StringUtils.isBlank(searchId)){
            sql+=" and c.userId='"+searchId+"'";
        }
        if(!StringUtils.isBlank(searchName)){
            sql+=" and u.userName like '%"+searchName+"%'";
        }
        List<Card> cardList=baseDao.queryList(sql,null,Card.class);
        int count=cardList.size();
        return  count;
    }

    @Override
    public int delCardStatus(Card card) {
        String sql="select status from card where cardId=?";
        Object [] params={card.getCardId()};
        List<Card> list=baseDao.queryList(sql,params,Card.class);
        int status=0;
        if(list!=null && list.size()>0){
            status=list.get(0).getStatus();
        }
        sql="update card set status=? where cardId=?";
        if(status==1){
            status=0;
        }else if(status==0){
            status=1;
        }
        Object [] params1={status,card.getCardId()};
        return  baseDao.executeUpdate(sql,params1);
    }

    @Override
    public int updateCardAmount(Card card) {
        String sql="update card set amount=?,credit=? where cardId=?";
        Object [] params={card.getAmount(),card.getCredit(),card.getCardId()};
        int a=baseDao.executeUpdate(sql,params);
        int levelId=0;
        if(a>0){
            String sql1="select * from card where cardId=?";
            Object [] params1={card.getCardId()};
            List<Card> cardList=baseDao.queryList(sql1,params1,Card.class);
            Integer credit=cardList.get(0).getCredit();
            if(credit>=100 && credit<1000){
                levelId=2;
            }else if(credit>=1000 && credit<10000){
                levelId=3;
            }else if(credit>=10000 && credit<100000){
                levelId=4;
            }else if (credit>=100000){
                levelId=5;
            }
            String sql3="update card set levelId=? where cardId=?";
            Object [] params2={levelId,card.getCardId()};
            return baseDao.executeUpdate(sql3,params2);
        }
        return 0;
    }

    @Override
    public List<Card> getCardByCardId(Integer cardID) {
        String sql="select c.*,u.userName,ct.`name` levelName from user u, card c,cardtype ct where u.cardId=c.cardId and ct.id=c.levelId and c.cardId=? and u.status!=0";
        Object [] params={cardID};
        List<Card> cardList=baseDao.queryList(sql,params,Card.class);
        return  cardList;
    }

    @Override
    public int updateCardStatus(Integer cardId) {
        String sql="update card set status=2 where cardId=?";
        Object [] params={cardId};
        return baseDao.executeUpdate(sql,params);
    }

    @Override
    public List<Card> getZhuXiaoCard(Integer pageNumber, Integer pageSize, String searchName, String searchId) {
        String sql="select c.*,u.userName,ct.`name` levelName from user u, card c,cardtype ct where u.cardId=c.cardId and ct.id=c.levelId and c.`status` = 2 ";
        if(!StringUtils.isBlank(searchId)){
            sql+=" and c.cardId= "+searchId;
        }
        if(!StringUtils.isBlank(searchName)){
            sql+=" and u.userName like '%"+searchName+"%'";
        }
        sql+=" limit ?,?";
        Object [] params={(pageNumber-1)*pageSize,pageSize};
        return baseDao.queryList(sql,params,Card.class);
    }

    @Override
    public int getZhuCardCount(String searchId, String searchName) {
        String sql="select c.*,u.userName,ct.`name` levelName from user u, card c,cardtype ct where u.cardId=c.cardId and ct.id=c.levelId and c.`status` = 2 ";
        if(!StringUtils.isBlank(searchId)){
            sql+=" and c.cardId= "+searchId;
        }
        if(!StringUtils.isBlank(searchName)){
            sql+=" and u.userName like '%"+searchName+"%'";
        }
        List<Card> cardList=baseDao.queryList(sql,null,Card.class);
        int a=cardList.size();
        return a;
    }

    @Override
    public int updateZhuCardStatus(Integer cardId) {
        String sql="update card set status=1 where cardId=?";
        Object [] params={cardId};
        return baseDao.executeUpdate(sql,params);
    }


}
