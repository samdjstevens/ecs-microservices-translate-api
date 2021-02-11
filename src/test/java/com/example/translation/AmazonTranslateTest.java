package com.example.translation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AmazonTranslateTest {

    @Test
    void testTranslation() {
        Translator translator = new AmazonTranslateTranslator();

        String translated = translator.translate("EN", "DE", "Hello World");

        assertEquals("Hallo Welt!", translated);
    }
}
