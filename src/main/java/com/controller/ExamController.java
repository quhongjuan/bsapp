package com.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.pojo.Exampapers;
import com.service.PaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;


@CrossOrigin(origins = "*",maxAge = 3600)//解决跨域
@Controller
public class ExamController {
    @Autowired
    @Qualifier("exam")
    private PaperService paperService;

    @RequestMapping("/pdf")
    public void queryPdf(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id= Integer.parseInt(request.getParameter("id"));
        System.out.println("id="+id);
        Optional<Exampapers> exampapers= Optional.ofNullable(paperService.queryPaperByPaperId(id));
        if(exampapers.isPresent()){
            byte[] fileBytes=exampapers.get().getContext();
            response.getOutputStream().write(fileBytes);
        }
    }
    @GetMapping("/showPdf")
    public String show(){
        return "showPdf.html";
    }

    @ResponseBody
    @PostMapping("/exam/code")
    public String verCode(HttpServletRequest request) throws JsonProcessingException {
        //int studentId= Integer.parseInt(request.getParameter("studentId"));
        String code=request.getParameter("code");
        return paperService.querySubject(code);
    }
}
