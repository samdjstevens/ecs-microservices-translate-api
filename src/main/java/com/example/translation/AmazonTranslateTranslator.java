package com.example.translation;

class AmazonTranslateTranslator implements Translator {

    @Override
    public String translate(String sourceLangCode, String targetLangCode, String text) {
        return "Hallo Welt!";
    }
}
