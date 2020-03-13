package com.whqfl.servlet;

import com.google.gson.Gson;
import com.whqfl.entity.CardType;
import com.whqfl.entity.ResponseDto;
import com.whqfl.service.CardService;
import com.whqfl.service.UserService;
import com.whqfl.service.impl.CardServiceImpl;
import com.whqfl.service.impl.UserServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "GetCardTypeServlet",urlPatterns = "/GetCardTypeServlet")
public class GetCardTypeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        CardService cardService=new CardServiceImpl();
        ResponseDto responseDto=new ResponseDto();
        Gson gson = new Gson();
        Logger logger=Logger.getLogger("GetCardTypeServlet");
       List<CardType> cardTypeList=cardService.getCardType();
       if(cardTypeList!=null && cardTypeList.size()>0){
           responseDto.setData(cardTypeList);
           responseDto.setMessage("操作成功");
           responseDto.setStatus(ResponseDto.SUCCESS_CODE);
       }else{
           responseDto.setStatus(ResponseDto.FAILURE_CODE);
           responseDto.setMessage("操作失败");
       }
       response.getWriter().print(gson.toJson(responseDto));
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
