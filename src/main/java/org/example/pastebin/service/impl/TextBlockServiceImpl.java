package org.example.pastebin.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.pastebin.entity.TextBlock;
import org.example.pastebin.repository.TextBlockRepository;
import org.example.pastebin.service.KeyGenerationService;
import org.example.pastebin.service.TextBlockService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TextBlockServiceImpl implements TextBlockService {

    private final TextBlockRepository textBlockRepository;

    private final KeyGenerationService keyGenerationService;

    @Override
    public TextBlock save(TextBlock textBlock) {
        String uniqueKey = keyGenerationService.getPreGeneratedKey();
        textBlock.setUniqueKey(uniqueKey);
        return textBlockRepository.save(textBlock);
    }

    @Override
    public Optional<TextBlock> findByUniqueKey(String uniqueKey) {
        return textBlockRepository.findByUniqueKey(uniqueKey);
    }

    @Override
    public void deleteExpiredTextBlocks(int pageSize) {
        PageRequest pageRequest = PageRequest.of(0, pageSize);

        Page<TextBlock> page;
        do {
            page = textBlockRepository.findByExpirationTimeBefore(OffsetDateTime.now(), pageRequest);
            textBlockRepository.deleteAll(page.getContent());
        } while (page.hasNext());
    }
}
