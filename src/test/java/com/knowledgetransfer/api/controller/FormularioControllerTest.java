package com.knowledgetransfer.api.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.knowledgetransfer.api.DTO.FormularioDTO;

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
public class FormularioControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<FormularioDTO> formularioDTOJson;

    @Test
    @DisplayName("Deveria devolver código HTTP 400 quando o nome do formulário é nulo")
    void testCadastrar() throws Exception {
        List<String> perguntas = Arrays.asList("Pergunta 1", "Pergunta 2", "Pergunta 3");
        List<List<String>> alternativas = Arrays.asList( Arrays.asList("Alternativa 1.1", "Alternativa 1.2"), Arrays.asList("Alternativa 2.1", "Alternativa 2.2"), Arrays.asList("Alternativa 3.1", "Alternativa 3.2"));
        Long processo_id_1 = (long) 1;
        Long processo_id_2 = (long) 2;
        List<List<Long>> processos_id = Arrays.asList( Arrays.asList(processo_id_1, processo_id_2), Arrays.asList(processo_id_1, processo_id_2), Arrays.asList(processo_id_1, processo_id_2));

        var response = mvc.perform(
                                post("/formulario")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(formularioDTOJson.write(
                                    new FormularioDTO(null, perguntas, alternativas, processos_id)
                                ).getJson()))
            .andReturn().getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }
}
