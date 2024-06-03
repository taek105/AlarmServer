package com.example.alarmserver.service;

import com.example.alarmserver.dto.PushAlarmRequest;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class FCMService {

    private final RedisTemplate<String, String> redisTemplate;

    public void send(String memberId, PushAlarmRequest request) {

        ValueOperations<String, String> values = redisTemplate.opsForValue();
        if (values.get(memberId) == null)
            throw new IllegalArgumentException("Can't find MemberId");

        String token = values.get(memberId);

        if ( request.getType() == 1 )
            request.setContent("새로운 QnA 문의글이 있어요.");
        else if ( request.getType() == 2 )
            request.setContent("새로운 QnA 답변글이 있어요.");
        else if ( request.getType() == 3 )
            request.setContent("상위 입찰이 생겼어요.");
        else if ( request.getType() == 4 )
            request.setContent("자동 입찰이 끝났어요.");


//        System.out.println(token);
//        System.out.println(request.getContent());
//        System.out.println(reques.getImgURL());
//        System.out.println(request.getProductName());
        pushToToken(token, request.getContent(), request.getImgURL(), request.getProductName());
    }

    public void pushToToken(String token, String content, String imgURL, String productName) {
        Message message = Message.builder()
                .putData("title", "BidmarKit")
                .putData("content", content)
                .putData("imgurl", imgURL)
                .putData("productName", productName)
                .setToken(token)
                .build();

        FirebaseMessaging.getInstance().sendAsync(message);
    }
}
