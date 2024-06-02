package com.example.alarmserver.dto;

import lombok.Getter;

@Getter
public class PushAlarmRequest  {
    private String productName;
    private String imgURL;
    private String content;
    private int type;

    public void setContent (String content) {
        this.content = content;
    }
}
