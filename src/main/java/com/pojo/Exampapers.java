package com.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Exampapers {
    private int paperId;
    private int authorId;
    private String releaseTime;
    private String endTime;
    private byte[]  context;
    private String stuCode;
    private String teaCode;
    private int isVisible;
    private String title;


}
