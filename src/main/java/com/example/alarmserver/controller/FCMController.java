package com.example.alarmserver.controller;

import com.example.alarmserver.dto.PushAlarmRequest;
import com.example.alarmserver.service.FCMService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class FCMController {
    private final FCMService fcmService;

    @PostMapping("/push/{memberId}")
    public ResponseEntity<Void> sendPushDefaultAlarm (@PathVariable String memberId, @RequestBody PushAlarmRequest request) {
        fcmService.send(memberId, request);

        return ResponseEntity.ok().build();
    }
}
