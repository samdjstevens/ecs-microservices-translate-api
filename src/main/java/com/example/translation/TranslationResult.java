package com.example.translation;

public class TranslationResult {

    private final String sourceLangCode;
    private final String targetLangCode;
    private final String translatedText;

    public TranslationResult(String sourceLangCode, String targetLangCode, String translatedText) {
        this.sourceLangCode = sourceLangCode;
        this.targetLangCode = targetLangCode;
        this.translatedText = translatedText;
    }

    public String getSourceLangCode() {
        return sourceLangCode;
    }

    public String getTargetLangCode() {
        return targetLangCode;
    }

    public String getTranslatedText() {
        return translatedText;
    }
}
