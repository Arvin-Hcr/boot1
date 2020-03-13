package com.whqfl.service.impl;

import com.whqfl.dao.UserDao;
import com.whqfl.dao.impl.UserDaoImpl;
import com.whqfl.entity.Card;
import com.whqfl.entity.User;
import com.whqfl.service.UserService;
import com.whqfl.util.BusinessException;
import com.whqfl.util.DateUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;
import java.util.List;

public class UserServiceImpl implements UserService {
    private UserDao userDao=new UserDaoImpl();
    Card card=new Card();
    User user=new User();
    @Override
    public List<User> getAllUser(Integer pageSize, Integer pageNumber, String searchId, String searchName,String cardId) throws BusinessException {
        if(pageSize==null && pageSize==0){
            throw new BusinessException("页数不能为空");
        }
        if(pageNumber==null){
            throw new BusinessException("当前页不能为空");
        }

        return userDao.getAllUser(pageSize,pageNumber,searchId,searchName,cardId);

    }

    /**
     * 按照用户ID，名字查询总条数
     * @param searchId
     * @param searchName
     * @return
     */
    @Override
    public int getAllUsercount(String searchId, String searchName) {
       return userDao.getAllUsercount(searchId,searchName);
    }

    /**
     * 按照以下参数修改会员信息
     * @param userName
     * @param phone
     * @param status
     * @param idCard
     * @param birthday
     * @param sex
     * @param address
     * @param momo
     * @param area
     * @return
     */
    @Override
    public int updateUser(String userName, String phone, Integer status, String idCard, String birthday, Integer sex, String address, String momo, String area,Integer userId) throws BusinessException {
        if(userId==0 && userId==null ){
            throw  new BusinessException("用户ID不能为空");
        }
        user.setUserName(userName);
        user.setPhone(phone);
        user.setStatus(status);
        user.setIdCard(idCard);
        user.setBirthday(birthday);
        user.setSex(sex);
        user.setAddress(address);
        user.setMomo(momo);
        user.setArea(area);
        user.setUserId(userId);
        return userDao.updateUser(user);
    }

    @Override
    public int delUser(Integer userId) throws BusinessException {
        if(userId==0 && userId==null){
            throw  new BusinessException("用户Id不能为空");
        }
        user.setUserId(userId);
        return  userDao.delUser(user);
    }

    @Override
    public int insertUser(Integer userId, String userName, String phone, Integer userStatus, String idCard, String birthday,
                          Integer sex, String address, String area, String createdTime, Integer cardId,
                          String momo,  Integer roleId) throws BusinessException {
        userId=userDao.getUserLastUserID();
        cardId=userDao.getUserLastCardId();
        if(userId ==null && userId==0){
           throw  new BusinessException("用户ID不能为空");
       }
        if(StringUtils.isBlank(userName)){
           throw  new BusinessException("用户名字不能为空");
       }
        if(StringUtils.isBlank(idCard)){
            throw  new BusinessException("用户身份证不能为空");
        }
        if(userStatus ==null && userStatus==0){
            throw  new BusinessException("用户状态不能为空");
        }

        if(cardId ==null && cardId==0){
            throw  new BusinessException("用户状态不能为空");
        }
        if(roleId ==null && roleId==0){
            throw  new BusinessException("操作人员ID不能为空");
        }
        user.setUserId(userId+1);
        user.setUserName(userName);
        user.setPhone(phone);
        user.setStatus(userStatus);
        user.setIdCard(idCard);
        user.setBirthday(idCard.substring(6,10)+"-"+idCard.substring(10,12)+"-"+idCard.substring(12,14));
        user.setSex(sex);
        user.setAddress(address);
        user.setArea(area);
        user.setCreatedTime(DateUtils.toFormat(new Date()));
        user.setCardId(cardId+1);
        user.setMomo(momo);
        user.setRoleId(roleId);
        return  userDao.insertUser(user);
    }

    @Override
    public int insertCard(Integer cardId, Integer userId, Double amount, Integer credit, Integer cardStatus, Integer staffId, Integer levelId) throws BusinessException {


        if(cardStatus==0 && cardStatus==null){
            throw  new BusinessException("卡状态不能为空");
        }

        if(staffId==0 && staffId==null){
            throw  new BusinessException("操作人员ID不能为空");
        }

        return  userDao.insertCard(cardId,userId,amount,credit,cardStatus,staffId,levelId);

    }


    @Override
    public Integer getUserLastUserID() {
       return userDao.getUserLastUserID();
    }

    @Override
    public Integer getUserLastCardId() {
        return userDao.getUserLastCardId();
    }


}
