package org.example.pastebin.exception;

public class TextBlockNotFoundException extends RuntimeException {
    public TextBlockNotFoundException() {
        super("TextBlock not found");
    }
}
