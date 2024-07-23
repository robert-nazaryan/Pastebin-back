package org.example.pastebin.endpoint;

import lombok.RequiredArgsConstructor;
import org.example.pastebin.dto.AddTextBlockRequestDto;
import org.example.pastebin.dto.TextBlockContentDto;
import org.example.pastebin.entity.TextBlock;
import org.example.pastebin.exception.TextBlockNotFoundException;
import org.example.pastebin.mapper.TextBlockMapper;
import org.example.pastebin.service.TextBlockService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/textBlocks")
@RequiredArgsConstructor
public class TextBlockEndpoint {

    private final TextBlockService textBlockService;

    private final TextBlockMapper mapper;

    @PostMapping("/add")
    public ResponseEntity<TextBlockContentDto> addTextBlock(
            @RequestBody @Validated AddTextBlockRequestDto requestDto) {
        TextBlock textBlock = mapper.toEntity(requestDto);
        TextBlockContentDto responseDto = mapper.toResponse(textBlockService.save(textBlock));
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping("/{uniqueKey}")
    public ResponseEntity<TextBlockContentDto> getTextBlockByUniqueKey(@PathVariable String uniqueKey) {
        TextBlock textBlock = textBlockService.findByUniqueKey(uniqueKey)
                .orElseThrow(TextBlockNotFoundException::new);
        TextBlockContentDto responseDto = mapper.toResponse(textBlock);
        return ResponseEntity.ok(responseDto);
    }

}
