package com.example.alarmserver.dto;

import lombok.Getter;

@Getter
public class PushAlarmRequest  {
    private String productName;
    private String content;
    private int type;
}
