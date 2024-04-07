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

import com.knowledgetransfer.api.DTO.ProcessoDTO;

import java.util.Random;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
public class ProcessoControllerTest {
    
    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<ProcessoDTO> processoDTOJson;

    @Test
    @DisplayName("Deveria retornar o código HTTP 404 ao não encontrar um processo")
    void testEncontrarProcesso() throws Exception {

        Long id = (long) 0;

        var responseGET = mvc.perform(get("/processo/"+id)).andReturn().getResponse();
        assertThat(responseGET.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
    }

    @Test
    @DisplayName("Deveria retornar o código HTTP 200 ao deletar um processo com qualquer id")
    void testDeletar() throws Exception {

        Random rand = new Random();

        Long id = (long) rand.nextInt(101); //id é um número aleatório entre 0 e 100

        var responseDELETE = mvc.perform(delete("/processo/"+id)).andReturn().getResponse();
        assertThat(responseDELETE.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    @DisplayName("Deveria retornar o código HTTP 400 ao tentar criar um processo com o campo de nome em branco")
    void testCadastrar() throws Exception {

        String nome = "";
        String descricao = "descricao_exemplo";

        var responsePOST = mvc.perform(
                                post("/processo")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(processoDTOJson.write(
                                    new ProcessoDTO(nome, descricao)
                                ).getJson()))
            .andReturn().getResponse();
        assertThat(responsePOST.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Deveria retornar o código HTTP 400 ao tentar atualizar um processo sem o campo de id")
    void testAtualizar() throws Exception {

        String nome = "nome_exemplo";
        String descricao = "descricao_exemplo";

        var responsePUT = mvc.perform(
                                put("/processo")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("{\"nome\":\""+nome+"\",\"descricao\":\""+descricao+"\"}"))
            .andReturn().getResponse();
        assertThat(responsePUT.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Deveria retornar o nome e a descrição de um processo que fora criado")
    void testListar() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        Long id = (long) 0;
        String nome = "nome_exemplo";
        String descricao = "descricao_exemplo";
        Boolean encontrouProcesso = false;

        var responsePOST = mvc.perform(
                                post("/processo")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(processoDTOJson.write(
                                    new ProcessoDTO(nome, descricao)
                                ).getJson()))
            .andReturn().getResponse();
        String responseGET = mvc.perform(
                                get("/processo"))
            .andReturn().getResponse().getContentAsString();
        JsonNode jsonNode = mapper.readTree(responseGET);
        for(JsonNode processo : jsonNode.get("content")){
            if(processo.get("nome").asText().equals(nome) && processo.get("descricao").asText().equals(descricao)){
                encontrouProcesso = true;
                id = (long) processo.get("id").asInt();
            }
        }
        var responseDELETE = mvc.perform(delete("/processo/"+id)).andReturn().getResponse();
        assertThat(encontrouProcesso).isEqualTo(true);
    }
}
