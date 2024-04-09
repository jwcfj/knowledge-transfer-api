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
import com.knowledgetransfer.api.DTO.ProcessoDTO;

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

    @Autowired
    private JacksonTester<ProcessoDTO> processoDTOJson;

    @Test
    @DisplayName("Deveria retornar o código HTTP 200 ao deletar uma alternativa com qualquer id")
    void testDeletar() throws Exception {

        Random rand = new Random();

        Long id = (long) rand.nextInt(101); //id é um número aleatório entre 0 e 100

        var responseDELETE = mvc.perform(delete("/alternativa/"+id)).andReturn().getResponse();
        assertThat(responseDELETE.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    @DisplayName("Deveria retornar o código HTTP 200 ao criar uma alternativa com sucesso")
    void testCadastrar() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        Long id = (long) 0;

        String nome = "processo_exemplo_test";
        String descricao = "descricao_exemplo_test";

        String processo_da_alternativa = "processo_exemplo_test";
        String pergunta_da_alternativa = "pergunta_exemplo";

        mvc.perform(
            post("/processo")
            .contentType(MediaType.APPLICATION_JSON)
            .content(processoDTOJson.write(
                new ProcessoDTO(nome, descricao))
            .getJson()))
            .andReturn().getResponse();

        var responsePOST = mvc.perform(
                                post("/alternativa")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(alternativaDTOJson.write(
                                    new AlternativaDTO(pergunta_da_alternativa, processo_da_alternativa)
                                ).getJson()))
            .andReturn().getResponse();
        assertThat(responsePOST.getStatus()).isEqualTo(HttpStatus.OK.value());

        String responseGET = mvc.perform(
                                get("/alternativa"))
            .andReturn().getResponse().getContentAsString();
        JsonNode jsonNode = mapper.readTree(responseGET);
        for(JsonNode alternativa : jsonNode.get("content")){
            if(alternativa.get("pergunta").asText().equals(pergunta_da_alternativa) && alternativa.get("processo").asText().equals(processo_da_alternativa)){
                id = (long) alternativa.get("id").asInt();
                mvc.perform(delete("/alternativa/"+id)).andReturn().getResponse();
                id = (long) alternativa.get("processo_id").asInt();
                mvc.perform(delete("/processo/"+id)).andReturn().getResponse();
                break;
            }
        }
    }

    @Test
    @DisplayName("Deveria retornar o código HTTP 200 ao atualizar uma alternativa com sucesso")
    void testAtualizar() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        Long id = (long) 0;

        Boolean pergunta_foi_atualizada = false;

        String nome = "processo_exemplo_test";
        String descricao = "descricao_exemplo_test";

        String processo_da_alternativa = "processo_exemplo_test";
        String pergunta_da_alternativa = "pergunta_exemplo";
        String pergunta_da_alternativa_nova = "pergunta_exemplo_nova";

        mvc.perform(
            post("/processo")
            .contentType(MediaType.APPLICATION_JSON)
            .content(processoDTOJson.write(
                new ProcessoDTO(nome, descricao))
            .getJson()))
            .andReturn().getResponse();

        var responsePOST = mvc.perform(
                                post("/alternativa")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(alternativaDTOJson.write(
                                    new AlternativaDTO(pergunta_da_alternativa, processo_da_alternativa)
                                ).getJson()))
            .andReturn().getResponse();

        String responseGET = mvc.perform(
                                get("/alternativa"))
            .andReturn().getResponse().getContentAsString();
        JsonNode jsonNode = mapper.readTree(responseGET);
        for(JsonNode alternativa : jsonNode.get("content")){
            if(alternativa.get("pergunta").asText().equals(pergunta_da_alternativa) && alternativa.get("processo").asText().equals(processo_da_alternativa)){
                id = (long) alternativa.get("id").asInt();
                var responsePUT = mvc.perform(
                                put("/alternativa")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("{\"id\":"+id+",\"pergunta\":\""+pergunta_da_alternativa_nova+"\",\"processo_da_alternativa\":\""+processo_da_alternativa+"\"}"))
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
            if(alternativa.get("pergunta").asText().equals(pergunta_da_alternativa_nova) && alternativa.get("processo").asText().equals(processo_da_alternativa)){
                id = (long) alternativa.get("id").asInt();
                mvc.perform(delete("/alternativa/"+id)).andReturn().getResponse();
                id = (long) alternativa.get("processo_id").asInt();
                mvc.perform(delete("/processo/"+id)).andReturn().getResponse();
                pergunta_foi_atualizada = true;
                break;
            }
        }

        assertThat(pergunta_foi_atualizada).isEqualTo(true);
    }

    @Test
    @DisplayName("Deveria retornar uma alternativa que fora criada")
    void testListar() throws Exception {
        ObjectMapper mapper = new ObjectMapper();

        Long id = (long) 0;

        Boolean encontrou_alternativa = false;

        String nome = "processo_exemplo_test";
        String descricao = "descricao_exemplo_test";

        String processo_da_alternativa = "processo_exemplo_test";
        String pergunta_da_alternativa = "pergunta_exemplo";

        mvc.perform(
            post("/processo")
            .contentType(MediaType.APPLICATION_JSON)
            .content(processoDTOJson.write(
                new ProcessoDTO(nome, descricao))
            .getJson()))
            .andReturn().getResponse();

        var responsePOST = mvc.perform(
                                post("/alternativa")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(alternativaDTOJson.write(
                                    new AlternativaDTO(pergunta_da_alternativa, processo_da_alternativa)
                                ).getJson()))
            .andReturn().getResponse();

        String responseGET = mvc.perform(
                                get("/alternativa"))
            .andReturn().getResponse().getContentAsString();
        JsonNode jsonNode = mapper.readTree(responseGET);
        for(JsonNode alternativa : jsonNode.get("content")){
            if(alternativa.get("pergunta").asText().equals(pergunta_da_alternativa) && alternativa.get("processo").asText().equals(processo_da_alternativa)){
                id = (long) alternativa.get("id").asInt();
                mvc.perform(delete("/alternativa/"+id)).andReturn().getResponse();
                id = (long) alternativa.get("processo_id").asInt();
                mvc.perform(delete("/processo/"+id)).andReturn().getResponse();
                encontrou_alternativa = true;
                break;
            }
        }

        assertThat(encontrou_alternativa).isEqualTo(true);
    }
}
