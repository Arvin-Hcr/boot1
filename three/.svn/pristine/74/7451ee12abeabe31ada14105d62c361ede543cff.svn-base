package com.whqfl.servlet;

import com.google.gson.Gson;
import com.whqfl.entity.ResponseDto;
import com.whqfl.service.CardManagerService;
import com.whqfl.service.impl.CardManagerServiceImp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "TransferCardServlet",urlPatterns = "/TransferCardServlet")
public class TransferCardServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        ResponseDto dto=new ResponseDto();

        String cardId=request.getParameter("carId");
        String userName=request.getParameter("getCardName");
        String phone=request.getParameter("getCardphone");
        String idCard=request.getParameter("idCard");
        String sheng=request.getParameter("sheng");
        String shi=request.getParameter("shi");

        CardManagerService cardManagerService=new CardManagerServiceImp();
        try {
           int i= cardManagerService.transferCard(cardId,userName,phone,sheng,shi,idCard);
            dto.setData(i);
            dto.setStatus(ResponseDto.SUCCESS_CODE);
        } catch (Exception e) {
            e.printStackTrace();
            dto.setStatus(ResponseDto.FAILURE_CODE);
            dto.setMessage(e.getMessage());
        }
         response.getWriter().print(new Gson().toJson(dto));

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
