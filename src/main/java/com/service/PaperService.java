package com.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mapper.ExamPaperMapper;
import com.pojo.Exampapers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("exam")
public class PaperService {
    @Autowired
    private ExamPaperMapper examPaperMapper;

    //根据paperId 获取考卷
    public Exampapers queryPaperByPaperId(int paperId){
        return examPaperMapper.queryPaperById(paperId);
    }

    //查学生考的是哪门学科
    public String querySubject(String code) throws JsonProcessingException {
        boolean flag=false;
        int subjectId=0;
        Exampapers exampapers=null;
        exampapers=examPaperMapper.queryPaperByCode(code);
        Map<String,Object> map=new HashMap<String, Object>();

        if(exampapers!=null){
           flag=true;
           subjectId=exampapers.getPaperId();
        }
        //System.out.println("flag="+flag);
        map.put("flags",flag);
        map.put("subjectId",subjectId);
        String str=new ObjectMapper().writeValueAsString(map);
        return str;
    }
}
