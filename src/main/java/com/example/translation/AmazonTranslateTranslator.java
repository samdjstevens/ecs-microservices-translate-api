package com.example.translation;

import software.amazon.awssdk.services.translate.TranslateClient;
import software.amazon.awssdk.services.translate.model.TranslateTextRequest;
import software.amazon.awssdk.services.translate.model.TranslateTextResponse;

class AmazonTranslateTranslator implements Translator {

    private final TranslateClient amazonTranslate;

    public AmazonTranslateTranslator(TranslateClient amazonTranslate) {
        this.amazonTranslate = amazonTranslate;
    }

    @Override
    public TranslationResult translate(String sourceLangCode, String targetLangCode, String text) {
        TranslateTextRequest request = createTranslateRequest(sourceLangCode, targetLangCode, text);
        TranslateTextResponse translateTextResponse = amazonTranslate.translateText(request);

        return createTranslationResult(translateTextResponse);
    }

    private TranslateTextRequest createTranslateRequest(String sourceLangCode, String targetLangCode, String translatedText) {
        return TranslateTextRequest.builder()
            .sourceLanguageCode(sourceLangCode)
            .targetLanguageCode(targetLangCode)
            .text(translatedText)
            .build();
    }

    private TranslationResult createTranslationResult(TranslateTextResponse response) {
        return new TranslationResult(response.sourceLanguageCode(), response.targetLanguageCode(), response.translatedText());
    }
}
