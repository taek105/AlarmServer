package com.example.alarmserver.controller;

import com.example.alarmserver.dto.AddFCMTokenRequest;
import com.example.alarmserver.service.RedisService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/redis")
public class RedisController {

    private final RedisService redisService;

    @PostMapping("/postFCMToken")
    public  ResponseEntity<Void> addFCMToken(@RequestBody AddFCMTokenRequest request) {
        redisService.setValues(request.getMemberId(), request.getFCMToken());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/getFCMToken/{memberId}")
    public  ResponseEntity<String> getFCMToken(@PathVariable String memberId) {
        return ResponseEntity.ok().body(redisService.getValues(memberId));
    }
}
