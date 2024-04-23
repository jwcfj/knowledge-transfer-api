package com.knowledgetransfer.api.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

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

import com.knowledgetransfer.api.DTO.AlternativaDTO;

import java.util.Random;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
public class AlternativaControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<AlternativaDTO> alternativaDTOJson;

    @Test
    @DisplayName("Deveria retornar o código HTTP 200 ao deletar uma alternativa com qualquer id")
    void testDeletar() throws Exception {

        Random rand = new Random();

        Long id = (long) rand.nextInt(101); //id é um número aleatório entre 0 e 100

        var responseDELETE = mvc.perform(delete("/alternativa/"+id)).andReturn().getResponse();
        assertThat(responseDELETE.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    @DisplayName("Deveria retornar o código HTTP 200 ao deletar um processo possuído com qualquer id")
    void testDeletarProcessoPossuido() throws Exception {

        Random rand = new Random();

        Long id = (long) rand.nextInt(101); //id é um número aleatório entre 0 e 100

        var responseDELETE = mvc.perform(delete("/alternativa/processo-possuido/"+id)).andReturn().getResponse();
        assertThat(responseDELETE.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    @DisplayName("Deveria retornar o código HTTP 200 ao criar uma alternativa com sucesso")
    void testCadastrarAlternativa() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        Long id = (long) 0;

        String pergunta_da_alternativa = "pergunta_exemplo";

        var responsePOST = mvc.perform(
                                post("/alternativa")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(alternativaDTOJson.write(
                                    new AlternativaDTO(pergunta_da_alternativa)
                                ).getJson()))
            .andReturn().getResponse();
        assertThat(responsePOST.getStatus()).isEqualTo(HttpStatus.OK.value());

        String responseGET = mvc.perform(
                                get("/alternativa"))
            .andReturn().getResponse().getContentAsString();
        JsonNode jsonNode = mapper.readTree(responseGET);
        for(JsonNode alternativa : jsonNode.get("content")){
            if(alternativa.get("pergunta").asText().equals(pergunta_da_alternativa)){
                id = (long) alternativa.get("alternativa_id").asInt();
                mvc.perform(delete("/alternativa/"+id)).andReturn().getResponse();
                break;
            }
        }
    }

    @Test
    @DisplayName("Deveria retornar uma alternativa que fora criada")
    void testListarAlternativaKtadmin() throws Exception {
        ObjectMapper mapper = new ObjectMapper();

        Long id = (long) 0;

        Boolean encontrou_alternativa = false;

        String pergunta_da_alternativa = "pergunta_exemplo";

        var responsePOST = mvc.perform(
                                post("/alternativa")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(alternativaDTOJson.write(
                                    new AlternativaDTO(pergunta_da_alternativa)
                                ).getJson()))
            .andReturn().getResponse();

        String responseGET = mvc.perform(
                                get("/alternativa"))
            .andReturn().getResponse().getContentAsString();
        JsonNode jsonNode = mapper.readTree(responseGET);
        for(JsonNode alternativa : jsonNode.get("content")){
            if(alternativa.get("pergunta").asText().equals(pergunta_da_alternativa)){
                id = (long) alternativa.get("alternativa_id").asInt();
                mvc.perform(delete("/alternativa/"+id)).andReturn().getResponse();
                encontrou_alternativa = true;
                break;
            }
        }

        assertThat(encontrou_alternativa).isEqualTo(true);
    }

    @Test
    @DisplayName("Deveria retornar o código HTTP 200 ao atualizar uma alternativa com sucesso")
    void testAtualizarAlternativa() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        Long id = (long) 0;

        Boolean pergunta_foi_atualizada = false;

        String pergunta_da_alternativa = "pergunta_exemplo";
        String pergunta_da_alternativa_nova = "pergunta_exemplo_nova";

        var responsePOST = mvc.perform(
                                post("/alternativa")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(alternativaDTOJson.write(
                                    new AlternativaDTO(pergunta_da_alternativa)
                                ).getJson()))
            .andReturn().getResponse();

        String responseGET = mvc.perform(
                                get("/alternativa"))
            .andReturn().getResponse().getContentAsString();
        JsonNode jsonNode = mapper.readTree(responseGET);
        for(JsonNode alternativa : jsonNode.get("content")){
            if(alternativa.get("pergunta").asText().equals(pergunta_da_alternativa)){
                id = (long) alternativa.get("alternativa_id").asInt();
                var responsePUT = mvc.perform(
                                put("/alternativa")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("{\"alternativa_id\":"+id+",\"pergunta\":\""+pergunta_da_alternativa_nova+"\"}"))
                    .andReturn().getResponse();
                assertThat(responsePUT.getStatus()).isEqualTo(HttpStatus.OK.value());
                break;
            }
        }

        responseGET = mvc.perform(
                                get("/alternativa"))
            .andReturn().getResponse().getContentAsString();
        jsonNode = mapper.readTree(responseGET);
        for(JsonNode alternativa : jsonNode.get("content")){
            if(alternativa.get("pergunta").asText().equals(pergunta_da_alternativa_nova)){
                id = (long) alternativa.get("alternativa_id").asInt();
                mvc.perform(delete("/alternativa/"+id)).andReturn().getResponse();
                pergunta_foi_atualizada = true;
                break;
            }
        }

        assertThat(pergunta_foi_atualizada).isEqualTo(true);
    }

    @Test
    @DisplayName("Deveria retornar o código HTTP 400 ao tentar criar um processo possuído sem processo_id")
    void testCadastrarProcessoPossuido() throws Exception {

        Random rand = new Random();

        Long alternativa_id = (long) rand.nextInt(101); //id é um número aleatório entre 0 e 100

        var responsePOST = mvc.perform(
                                post("/alternativa/possuir")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("{\"alternativa_id\":"+alternativa_id+",\"processo_id\":\"\"}"))
            .andReturn().getResponse();
        assertThat(responsePOST.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }
}
