package com.service;


import com.mapper.AnswerMapper;
import com.pojo.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.*;

@Service
public class AnswerService {
    @Autowired
    private AnswerMapper answerMapper;

    @Autowired
    @Qualifier("student")
    private StudentService studentService;



    public void addAnswer(int paperId, int studentId, File pdfAnswer){
        byte[] context= File2byte(pdfAnswer);
        String studentName=studentService.queryStudentInfo(studentId).getName();
        Answer answer=new Answer(paperId,studentId,studentName,context);
        answerMapper.addAnswer(answer);
    }
    /**
     * 将文件转换成byte数组
     * @param tradeFile
     * @return
     */
    public static byte[] File2byte(File tradeFile){
        byte[] buffer = null;
        try
        {
            FileInputStream fis = new FileInputStream(tradeFile);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte[] b = new byte[1024];
            int n;
            while ((n = fis.read(b)) != -1)
            {
                bos.write(b, 0, n);
            }
            fis.close();
            bos.close();
            buffer = bos.toByteArray();
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
        return buffer;
    }
}
