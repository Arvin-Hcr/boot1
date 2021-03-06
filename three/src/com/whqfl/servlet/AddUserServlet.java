package com.whqfl.servlet;

import com.google.gson.Gson;
import com.whqfl.entity.ResponseDto;
import com.whqfl.service.RechargerecordService;
import com.whqfl.service.UserService;
import com.whqfl.service.impl.RechargerecordServiceImpl;
import com.whqfl.service.impl.UserServiceImpl;
import com.whqfl.util.IntegerUtils;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AddUserServlet",urlPatterns = "/AddUserServlet")
public class AddUserServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        Integer cardId= IntegerUtils.ToInteger(request.getParameter("cardId"));
        Integer userId=IntegerUtils.ToInteger(request.getParameter("userId"));
        Double amount=Double.parseDouble(request.getParameter("amount"));
        Integer credit=IntegerUtils.ToInteger(request.getParameter("credit"));
        Integer userStatus=IntegerUtils.ToInteger(request.getParameter("userStatus"));
        Integer staffId=IntegerUtils.ToInteger(request.getParameter("staffId"));
        Integer roleId=IntegerUtils.ToInteger(request.getParameter("roleId"));
        Integer levelId=IntegerUtils.ToInteger(request.getParameter("userLevel"));
        String userName = request.getParameter("userName");
        String phone = request.getParameter("userPhone");
        String idCard = request.getParameter("idno");
        String birthday = request.getParameter("birthday");
        Integer sex=IntegerUtils.ToInteger(request.getParameter("userSex"));
        String address = request.getParameter("address");
        String area = request.getParameter("area");
        String createdTime = request.getParameter("createdTime");
        String momo = request.getParameter("momo");
        Integer ruleId=1;
        UserService userService=new UserServiceImpl();
        RechargerecordService rechargerecordService=new RechargerecordServiceImpl();
        ResponseDto responseDto=new ResponseDto();
        Gson gson = new Gson();
        Logger logger=Logger.getLogger("AddUserServlet");
        try {
            Integer userId1=(userService.getUserLastUserID()+1);

            int user= userService.insertUser(userId,userName,phone,userStatus,idCard,birthday,sex,address,area,createdTime,cardId,momo,roleId);
            int card=userService.insertCard(cardId,userId1,amount,credit,userStatus,staffId,levelId);
            int rechargerecord=rechargerecordService.addRechargerecord(cardId,amount,amount,0.0,ruleId,createdTime,staffId,momo);
            if(user>0 && card>0 && rechargerecord>0){
                responseDto.setMessage("操作成功");
                responseDto.setStatus(ResponseDto.SUCCESS_CODE);
            }else {
                responseDto.setMessage("操作失败");
                responseDto.setStatus(ResponseDto.FAILURE_CODE);
            }

        } catch (Exception e) {
            responseDto.setMessage("操作失败");
            responseDto.setStatus(ResponseDto.FAILURE_CODE);
            logger.error(e.getMessage());
        }
        response.getWriter().print(gson.toJson(responseDto));

    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
