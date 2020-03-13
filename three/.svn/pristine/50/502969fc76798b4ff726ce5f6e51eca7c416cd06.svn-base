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

@WebServlet(name = "ParallelCarServlet" ,urlPatterns = "/ParallelCarServlet")
public class ParallelCarServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userCardId1=request.getParameter("userCardId1");
        String userCardId=request.getParameter("userCardId");
        String getMoney=request.getParameter("getMoney");
        String getLevel=request.getParameter("getLevel");
        String getFen=request.getParameter("getFen");

        ResponseDto dto=new ResponseDto();
        CardManagerService cardManagerService=new CardManagerServiceImp();
        try {
            int i = cardManagerService.updataCard(userCardId1, userCardId, getMoney, getLevel, getFen);
            dto.setStatus(ResponseDto.SUCCESS_CODE);
            dto.setData(i);
        } catch (Exception e) {
            dto.setStatus(ResponseDto.FAILURE_CODE);
            dto.setMessage(e.getMessage());
            e.printStackTrace();
        }
        response.getWriter().print(new Gson().toJson(dto));
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
