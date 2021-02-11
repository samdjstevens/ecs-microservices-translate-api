package com.example.translation;

interface Translator {
    TranslationResult translate(String sourceLangCode, String targetLangCode, String text);
}
