package com.example.translation;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class TranslateControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private Translator translator;

    @Test
    void testEndpoint() throws Exception {
        String sourceLangCode = "en";
        String targetLangCode = "de";
        String text = "Hello World!";
        String translatedText = "Hallo Welt!";

        // Mock the translator
        TranslationResult result = new TranslationResult(sourceLangCode, targetLangCode, translatedText);
        when(translator.translate(eq(sourceLangCode), eq(targetLangCode), eq(text))).thenReturn(result);

        // Build the request
        TranslationRequest request = new TranslationRequest(sourceLangCode, targetLangCode, text);
        String body = new ObjectMapper().writeValueAsString(request);

        mockMvc.perform(
                post("/translate")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(body)
            )
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.sourceLangCode", is(sourceLangCode)))
            .andExpect(jsonPath("$.targetLangCode", is(targetLangCode)))
            .andExpect(jsonPath("$.translatedText", is(translatedText)))
        ;
    }
}
