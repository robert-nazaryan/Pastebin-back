package org.example.pastebin.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.pastebin.service.KeyGenerationService;
import org.example.pastebin.service.KeyPreGenerationService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KeyPreGenerationServiceImpl implements KeyPreGenerationService {

    private final KeyGenerationService keyGenerationService;
    private final RedisTemplate<String, String> redisTemplate;

    @Scheduled(fixedRate = 60_000)
    public void preGenerateKeys() {
        Long currentSize = redisTemplate.opsForList().size("preGeneratedKeys");

        if (currentSize == null) {
            currentSize = 0L;
        }

        if (currentSize < 50) {
            int keysToGenerate = 100 - currentSize.intValue();
            for (int i = 0; i < keysToGenerate; i++) {
                String key = keyGenerationService.generateUniqueKey();
                redisTemplate.opsForList().leftPush("preGeneratedKeys", key);
            }
        }
    }
}
