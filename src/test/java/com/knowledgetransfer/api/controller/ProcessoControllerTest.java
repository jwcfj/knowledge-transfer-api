package com.knowledgetransfer.api.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
public class ProcessoControllerTest {
    
    @Autowired
    private MockMvc mvc;

    @Test
    @DisplayName("Deveria retornar o código HTTP 404 ao não encontrar um processo")
    void testEncontrarProcesso() throws Exception {

        Long id = (long) 0;

        var responseGET = mvc.perform(get("/processo/"+id)).andReturn().getResponse();
        assertThat(responseGET.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
    }
}
