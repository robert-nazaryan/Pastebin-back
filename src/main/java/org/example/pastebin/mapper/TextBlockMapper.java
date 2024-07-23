package org.example.pastebin.mapper;

import org.example.pastebin.dto.AddTextBlockRequestDto;
import org.example.pastebin.dto.TextBlockContentDto;
import org.example.pastebin.entity.TextBlock;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.OffsetDateTime;

@Mapper(componentModel = "spring", imports = OffsetDateTime.class)
public interface TextBlockMapper {

    @Mapping(target = "expirationTime", source = "expirationTime")
    TextBlock toEntity(AddTextBlockRequestDto requestDto);

    TextBlockContentDto toResponse(TextBlock textBlock);
}
