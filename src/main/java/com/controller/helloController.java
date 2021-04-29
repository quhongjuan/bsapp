package com.controller;

import com.utils.Img2PdfUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.logging.Log;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@CrossOrigin(origins = "*",maxAge = 3600)
@Controller
@Slf4j
public class helloController {

    @ResponseBody
    @RequestMapping("/hello")
    public String hello(HttpServletRequest request){
        int size=0;
        size= Integer.parseInt(request.getParameter("pageSize"));
        //size=pageSize;
        System.out.println("size=="+size);
        return "ok";
    }
    @GetMapping("testImgToPdf")
    public void testImgToPdf(HttpServletResponse response) throws Exception {
        File file1=new File("D:\\picture\\one.jpg");
        File file2=new File("D:\\picture\\two.jpg");
        List<File> files = new ArrayList<>();
        files.add(file1);
        files.add(file2);
        File file = Img2PdfUtil.imagesToPdf(files.toArray(new File[files.size()]));
        download(response, file);
    }

    private void download(HttpServletResponse response, File file) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Content-Disposition", String.format("attachment; filename=\"%s\"", "test.pdf"));
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");

        response.setContentType("application/force-download");
        response.setHeader("Content-Disposition", "attachment;fileName=" + "test.pdf");

        byte[] buffer = new byte[1024];
        FileInputStream fis = null; //文件输入流
        BufferedInputStream bis = null;

        OutputStream os; //输出流
        try {
            os = response.getOutputStream();
            fis = new FileInputStream(file);
            bis = new BufferedInputStream(fis);
            int i = bis.read(buffer);
            while (i != -1) {
                os.write(buffer);
                i = bis.read(buffer);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            bis.close();
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
