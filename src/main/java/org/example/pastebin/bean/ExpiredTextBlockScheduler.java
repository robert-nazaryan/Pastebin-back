package org.example.pastebin.bean;

import lombok.RequiredArgsConstructor;
import org.example.pastebin.service.TextBlockService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ExpiredTextBlockScheduler {

    private static final int MINUTE = 60_000;
    private static final int PAGE_SIZE = 3;

    private final TextBlockService textBlockService;

    @Scheduled(fixedRate = MINUTE)
    public void deleteExpiredTextBlocks() {
        textBlockService.deleteExpiredTextBlocks(PAGE_SIZE);
    }

}
