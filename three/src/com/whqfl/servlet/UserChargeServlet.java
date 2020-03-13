package com.whqfl.servlet;

import com.google.gson.Gson;
import com.whqfl.dao.RechargeRuleDao;
import com.whqfl.entity.ResponseDto;
import com.whqfl.service.CardService;
import com.whqfl.service.RechargeRuleService;
import com.whqfl.service.RechargerecordService;
import com.whqfl.service.impl.CardServiceImpl;
import com.whqfl.service.impl.RechargeRuleServiceImpl;
import com.whqfl.service.impl.RechargerecordServiceImpl;
import com.whqfl.util.IntegerUtils;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "UserChargeServlet",urlPatterns = "/UserChargeServlet")
public class UserChargeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        Integer cardId=IntegerUtils.ToInteger(request.getParameter("userCardId"));
        Integer userId=IntegerUtils.ToInteger(request.getParameter("userId"));
        Double amount=Double.parseDouble(request.getParameter("cardAmount"));
        Integer credit=IntegerUtils.ToInteger(request.getParameter("credit"));
        Integer userCredit=IntegerUtils.ToInteger(request.getParameter("userCredit"));
        Integer status=IntegerUtils.ToInteger(request.getParameter("status"));
        Double recharge=Double.parseDouble(request.getParameter("amount"));
        Integer staffId=IntegerUtils.ToInteger(request.getParameter("staffId"));
        Integer rechargeRule=IntegerUtils.ToInteger(request.getParameter("rechargeRule"));
        String momo=request.getParameter("momo");
        CardService cardService=new CardServiceImpl();
        RechargerecordService rechargerecordService=new RechargerecordServiceImpl();
        RechargeRuleService rechargeRuleService=new RechargeRuleServiceImpl();
        ResponseDto responseDto=new ResponseDto();
        Gson gson = new Gson();
        Logger logger=Logger.getLogger("UserChargeServlet");
        try {
            Double cofficient=rechargeRuleService.getRecharCoefficient(rechargeRule);
            Double afteramount=amount+recharge+recharge*cofficient;
            credit=credit+userCredit;
            int a=rechargerecordService.addUserRechargerecord(cardId,recharge,afteramount,amount,rechargeRule,null,staffId,momo);
            int b=cardService.updateCardAmount(afteramount,credit,cardId);
            if(a>0 && b>0){
                responseDto.setStatus(ResponseDto.SUCCESS_CODE);
                responseDto.setMessage("操作成功");
            }else{
                responseDto.setStatus(ResponseDto.FAILURE_CODE);
                responseDto.setMessage("操作失败");
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        response.getWriter().print(gson.toJson(responseDto));

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
