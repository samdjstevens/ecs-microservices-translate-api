package com.example.translation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import software.amazon.awssdk.services.translate.TranslateClient;
import software.amazon.awssdk.services.translate.model.TranslateTextRequest;
import software.amazon.awssdk.services.translate.model.TranslateTextResponse;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AmazonTranslateTest {

    private TranslateClient amazonTranslate;
    private Translator translator;

    @BeforeEach
    void setUp() {
        amazonTranslate = mock(TranslateClient.class);
        translator = new AmazonTranslateTranslator(amazonTranslate);
    }

    @ParameterizedTest
    @MethodSource("getTranslationExamples")
    void testTranslation(String sourceLang, String targetLang, String text, String translatedText) {
        mockAmazonTranslate(sourceLang, targetLang, text, translatedText);

        TranslationResult result = translator.translate(sourceLang, targetLang, text);

        assertEquals(sourceLang, result.getSourceLangCode());
        assertEquals(targetLang, result.getTargetLangCode());
        assertEquals(translatedText, result.getTranslatedText());
    }

    static Stream<Arguments> getTranslationExamples() {
        return Stream.of(
            arguments("en", "de", "Hello World!", "Hallo Welt!"),
            arguments("de", "en", "Hallo Welt!", "Hello World!"),
            arguments("en", "fr", "Hello World!", "Bonjour le monde!")
        );
    }

    private void mockAmazonTranslate(String sourceLang, String targetLang, String text, String translatedText) {
        TranslateTextRequest expectedRequest = TranslateTextRequest.builder()
                .sourceLanguageCode(sourceLang)
                .targetLanguageCode(targetLang)
                .text(text)
                .build();
        when(amazonTranslate.translateText(eq(expectedRequest)))
                .thenReturn(getAwsResponse(sourceLang, targetLang, translatedText));
    }

    private TranslateTextResponse getAwsResponse(String sourceLangCode, String targetLangCode, String translatedText) {
        return TranslateTextResponse.builder()
            .sourceLanguageCode(sourceLangCode)
            .targetLanguageCode(targetLangCode)
            .translatedText(translatedText)
            .build();
    }
}
