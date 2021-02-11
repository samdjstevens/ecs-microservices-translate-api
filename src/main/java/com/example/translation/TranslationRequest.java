package com.example.translation;

public class TranslationRequest {
    private final String sourceLangCode;
    private final String targetLangCode;
    private final String text;

    public TranslationRequest(String sourceLangCode, String targetLangCode, String text) {
        this.sourceLangCode = sourceLangCode;
        this.targetLangCode = targetLangCode;
        this.text = text;
    }

    public String getSourceLangCode() {
        return sourceLangCode;
    }

    public String getTargetLangCode() {
        return targetLangCode;
    }

    public String getText() {
        return text;
    }
}
