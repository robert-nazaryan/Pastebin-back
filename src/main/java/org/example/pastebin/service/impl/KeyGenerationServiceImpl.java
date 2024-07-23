package org.example.pastebin.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.pastebin.service.KeyGenerationService;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KeyGenerationServiceImpl implements KeyGenerationService {

    private static final String BASE62 = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static final String KEY_PREFIX = "key:";

    private final StringRedisTemplate redisTemplate;

    public String generateUniqueKey() {
        long sequenceValue = redisTemplate.opsForValue().increment(KEY_PREFIX + "sequence", 1);
        return toBase62(sequenceValue);
    }

    public String getPreGeneratedKey() {
        String key = redisTemplate.opsForList().leftPop("preGeneratedKeys");
        if (key == null) {
            return generateUniqueKey();
        }
        return key;
    }

    private String toBase62(long value) {
        StringBuilder result = new StringBuilder();
        while (value > 0) {
            int remainder = (int) (value % 62);
            result.insert(0, BASE62.charAt(remainder));
            value = value / 62;
        }

        while (result.length() < 8) {
            result.insert(0, '0');
        }

        return result.toString();
    }

}
