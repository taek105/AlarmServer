package com.example.alarmserver.service;

import com.example.alarmserver.dto.PushAlarmRequest;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class FCMService {

    private final RedisTemplate<String, Object> redisTemplate;

    public void send(String memberId, PushAlarmRequest request) {
        if (redisTemplate.hasKey(memberId))
            throw new IllegalArgumentException("Can't find MemberId");
        String token = (String) redisTemplate.opsForValue().get(memberId);

        if ( request.getType() == 1 )
            request.setContent("새로운 QnA 문의글이 있어요.");
        else if ( request.getType() == 2 )
            request.setContent("새로운 QnA 답변글이 있어요.");
        else if ( request.getType() == 3 )
            request.setContent("상위 입찰이 있어요.");

        pushToToken(token, request.getContent(), request.getProductName());
    }

    public void pushToToken(String token, String content, String productName) {
        Message message = Message.builder()
                .putData("title", "BidmarKit")
                .putData("content", content)
                .putData("productName", productName)
                .setToken(token)
                .build();

        FirebaseMessaging.getInstance().sendAsync(message);
    }
}
