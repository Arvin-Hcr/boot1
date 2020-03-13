package com.whqfl.dao.impl;

import com.whqfl.dao.UserDao;
import com.whqfl.entity.Card;
import com.whqfl.entity.User;
import com.whqfl.util.BaseDao;
import org.apache.commons.lang3.StringUtils;
import java.util.List;

public class UserDaoImpl implements UserDao {
    private BaseDao baseDao=new BaseDao();
    /**
     * 按照用户 id，名字，查询所有员工的信息
     * @param pageSize
     * @param pageNumber
     * @param searchId
     * @param searchName
     * @return
     */
    @Override

    public List<User> getAllUser(Integer pageSize, Integer pageNumber, String searchId, String searchName,String cardId) {
        String sql="select u.*,c.amount amount,c.credit credit,ct.`name` levelName" +
                " from user u, card c, cardtype ct " +
                "where u.cardId=c.cardId and ct.`level`=c.levelId and c.status != 2 ";

        if(!StringUtils.isBlank(searchId)){
            sql=sql+" and u.userId="+searchId;
        }
        if(!StringUtils.isBlank(searchName)){
            sql=sql+" and u.userName like '%"+searchName+"%'";
        }

        if(!StringUtils.isBlank(cardId)){
            sql=sql+" and c.cardId="+cardId;
        }

        sql=sql+" ORDER BY u.id desc limit ?,?";

        Object [] params={(pageNumber-1)*pageSize,pageSize};

        List<User> userList=baseDao.queryList(sql,params,User.class);
        if(userList!=null && userList.size()>0){
            return userList;
        }

        return userList;
    }

    /**
     * 按照用户ID，名字查询总条数
     * @param searchId
     * @param searchName
     * @return
     */
    @Override
    public int getAllUsercount(String searchId, String searchName) {
        String sql="select u.*,c.amount amount,c.credit credit,ct.`name` levelName from user u, card c, cardtype ct where u.cardId=c.cardId and ct.`level`=c.levelId and c.`status` !=2 ";
        if(!StringUtils.isBlank(searchId)){
            sql=sql+" and u.userId='"+searchId+"'";
        }
        if(!StringUtils.isBlank(searchName)){
            sql=sql+" and u.userName = '"+searchName+"'";
        }
        List<User> userList=baseDao.queryList(sql,null,User.class);
        int count=userList.size();
        return count;


    }

    /**
     * 通过会员Id修改会员信息
     * @param user
     * @return
     */
    @Override
    public int updateUser(User user) {
        String sql="update user set userName=?,phone=?,status=?,idCard=?,birthday=?,sex=?,address=?,momo=?,area=? where userId=? ";
       Object [] params={user.getUserName(),user.getPhone(),user.getStatus(),user.getIdCard(),user.getBirthday(),user.getSex(),user.getAddress(),user.getMomo(),user.getArea(),user.getUserId()};
        int result=baseDao.executeUpdate(sql,params);
        if(result>0){
            return  result;
        }
        return result;
    }

    /**
     * 通过会员Id规则删除
     * @param user
     * @return
     */
    @Override
    public int delUser(User user) {
       String sql="select status from user where userId=?";
       Object [] params={user.getUserId()};
       List<User> list=baseDao.queryList(sql,params,User.class);
       int status=0;
       if(list!=null && list.size()>0){
            status=list.get(0).getStatus();
        }
        sql="update user set status=? where userId=?";
       if(status==1){
           status=0;
       }else if(status==0){
           status=1;
       }
       Object [] params1={status,user.getUserId()};
       return  baseDao.executeUpdate(sql,params1);


    }

    @Override
    public int insertUser(User user) {
        String sql="insert into user (userId,userName,phone,status,idCard,birthday,sex,address,area,createdTime,cardId,momo,roleId) values (?,?,?,?,?,?,?,?,?,?,?,?,1)";
        Object [] params={user.getUserId(),user.getUserName(),user.getPhone(),user.getStatus(),user.getIdCard(),user.getBirthday(),user.getSex(),user.getAddress(),user.getArea(),
                user.getCreatedTime(),user.getCardId(),user.getMomo()};
        return baseDao.executeUpdate(sql,params);
    }

    @Override
    public int insertCard(Integer cardId,Integer userId,Double amount,Integer credit,Integer status,Integer staffId,Integer leavelId) {
       String sql="insert into card (cardId,userId,amount,credit,status,staffId,levelId) values (?,?,?,?,?,?,?)";
       Object [] params={cardId,userId,amount,credit,status,staffId,leavelId};
        return baseDao.executeUpdate(sql,params);
    }

    @Override
    public Integer getUserLastUserID() {
        String sql="select * from user order by userId desc limit 1";
        List<User> userList=baseDao.queryList(sql,null,User.class);
        return userList.get(0).getUserId();
    }

    @Override
    public Integer getUserLastCardId() {
        String sql="select * from user order by cardId desc limit 1";
        List<User> userList=baseDao.queryList(sql,null,User.class);
        return  userList.get(0).getCardId();
    }

  
}
