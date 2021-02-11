package com.example.translation;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TranslateController {

    Translator translator;

    public TranslateController(Translator translator) {
        this.translator = translator;
    }

    @PostMapping(value = "/translate", consumes = { "application/json" }, produces = { "application/json" })
    public TranslationResult translate(@RequestBody TranslationRequest request) {
        return translator.translate(request.getSourceLangCode(), request.getTargetLangCode(), request.getText());
    }
}
