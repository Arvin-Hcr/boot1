package com.whqfl.servlet;

import com.google.gson.Gson;
import com.whqfl.entity.Order;
import com.whqfl.entity.ResponseDto;
import com.whqfl.service.OderService;
import com.whqfl.service.impl.OderServiceImpl;
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

@WebServlet(name = "GetOrderByIdServlet",urlPatterns = "/GetOrderByIdServlet")
public class GetOrderByIdServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        Integer cardId= IntegerUtils.ToInteger(request.getParameter("cardId"));
        Integer pageNumber=IntegerUtils.ToInteger(request.getParameter("pageNumber"));
        Integer pageSize=IntegerUtils.ToInteger(request.getParameter("pageSize"));
        ResponseDto responseDto=new ResponseDto();
        Gson gson = new Gson();
        Logger logger=Logger.getLogger("GetOrderByIdServlet");
        OderService oderService=new OderServiceImpl();
        Map<String,Object> map=new HashMap<>();
        try {
            List<Order> orderList=oderService.getOrderById(cardId,pageNumber,pageSize);
            int count=oderService.getOrderByIdCount(cardId);
            if(orderList!=null && orderList.size()>0){
                map.put("count",count);
                map.put("list",orderList);
                responseDto.setData(map);
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
        doPost(request, response);
    }
}
