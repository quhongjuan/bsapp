package com.mapper;

import com.pojo.Exampapers;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ExamPaperMapper {
    Exampapers queryPaperById(int paperId);
    //根据考试码去查学科
    Exampapers queryPaperByCode(@Param("stuCode") String stuCode);

}
