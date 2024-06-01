package com.example.alarmserver.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
public class AddFCMTokenRequest {
    private String memberId;

    @JsonProperty("FCMToken")
    private String FCMToken;
}
