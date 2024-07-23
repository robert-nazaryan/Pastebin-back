package org.example.pastebin.endpoint;

import lombok.RequiredArgsConstructor;
import org.example.pastebin.service.KeyGenerationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/keys")
@RequiredArgsConstructor
public class KeyGenerationEndpoint {

    private KeyGenerationService keyGenerationService;

    @GetMapping("/generate")
    public String generateKey() {
        return keyGenerationService.generateUniqueKey();
    }

}
