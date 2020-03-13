package com.whqfl.servlet;

import com.google.gson.Gson;
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

@WebServlet(name = "UpdateZhuStatusServlet",urlPatterns = "/UpdateZhuStatusServlet")
public class UpdateZhuStatusServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        ResponseDto responseDto=new ResponseDto();
        Gson gson = new Gson();
        Logger logger=Logger.getLogger("UpdateCardStatusServlet");
        Integer cardId= IntegerUtils.ToInteger(request.getParameter("cardId"));
        CardService cardService=new CardServiceImpl();
        try {
            int status = cardService.updateZhuCardStatus(cardId);
            if(status>0){
                responseDto.setData(status);
                responseDto.setMessage("草哦做成功");
                responseDto.setStatus(ResponseDto.SUCCESS_CODE);
            }else{
                responseDto.setMessage("操作失败");
                responseDto.setStatus(ResponseDto.FAILURE_CODE);
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        response.getWriter().print(gson.toJson(responseDto));
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
