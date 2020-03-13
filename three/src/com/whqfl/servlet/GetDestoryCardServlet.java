package com.whqfl.servlet;

import com.google.gson.Gson;
import com.whqfl.entity.Card;
import com.whqfl.entity.ResponseDto;
import com.whqfl.service.CardService;
import com.whqfl.service.impl.CardServiceImpl;
import com.whqfl.util.IntegerUtils;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "GetDestoryCardServlet",urlPatterns = "/GetDestoryCardServlet")
public class GetDestoryCardServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        ResponseDto responseDto=new ResponseDto();
        Gson gson = new Gson();
        Logger logger=Logger.getLogger("GetDestoryCardServlet");
        Integer pageNumber= IntegerUtils.ToInteger(request.getParameter("pageNumber"));
        Integer pageSize=IntegerUtils.ToInteger(request.getParameter("pageSize"));
        String searchId=request.getParameter("searchId");
        String searchName=request.getParameter("searchName");
        CardService cardService=new CardServiceImpl();
        Map<String,Object> map=new HashMap<>();
        try {
            List<Card> cardList=cardService.getZhuXiaoCard(pageNumber,pageSize,searchName,searchId);
            int count=cardService.getZhuCardCount(searchId,searchName);
            map.put("count",count);
            map.put("list",cardList);
            responseDto.setStatus(ResponseDto.SUCCESS_CODE);
            responseDto.setMessage("操作成功");
            responseDto.setData(map);
        } catch (Exception e) {
           logger.error(e.getMessage());
        }
        response.getWriter().print(gson.toJson(responseDto));
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
