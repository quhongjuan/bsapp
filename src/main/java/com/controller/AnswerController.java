package com.controller;

import com.pojo.Exampapers;
import com.service.AnswerService;
import com.utils.Img2PdfUtil;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.utils.bytesToFile.bytesToFile;

@CrossOrigin(origins = "*",maxAge = 3600)//解决跨域
@Controller
public class AnswerController {
    @Autowired
    AnswerService answerService;

    @ResponseBody
    @PostMapping(value = "/answer/uploadAnswer")
    public String upload(HttpServletRequest request,HttpServletResponse response) throws Exception {
        MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest)(request);
        List<File> files=new ArrayList<>();
        int paperId= Integer.parseInt(multiRequest.getParameter("paperId"));
        int studentId= Integer.parseInt(multiRequest.getParameter("userId"));
        int length= Integer.parseInt(multiRequest.getParameter("imgLength"));
        System.out.println("studentId:"+studentId+"  paperId:"+paperId);
        for(int i=0;i<length;i++){
           String n="images"+i;
           String pathname=n+".jpg";
            File file = new File(pathname);
            FileUtils.copyInputStreamToFile(multiRequest.getFile(n).getInputStream(), file);
            //System.out.println("图片信息"+i+":"+file.length());
           files.add(file);
       }
       File pdfFile = Img2PdfUtil.imagesToPdf(files.toArray(new File[files.size()]));
        answerService.addAnswer(paperId,studentId,pdfFile);
        return "ok";
    }



}
