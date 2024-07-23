package org.example.pastebin.service;

import org.example.pastebin.entity.TextBlock;

import java.util.Optional;

public interface TextBlockService {

    TextBlock save(TextBlock textBlock);

    Optional<TextBlock> findByUniqueKey(String uniqueKey);

    void deleteExpiredTextBlocks(int pageSize);

}
