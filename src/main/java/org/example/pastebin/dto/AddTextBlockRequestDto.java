package org.example.pastebin.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.example.pastebin.bean.OffsetDateTimeDeserializer;

import java.time.OffsetDateTime;

@Data
public class AddTextBlockRequestDto {

    @NotBlank
    private String content;

    @NotNull
    @JsonDeserialize(using = OffsetDateTimeDeserializer.class)
    private OffsetDateTime expirationTime;

}
