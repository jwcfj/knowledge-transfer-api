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

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.knowledgetransfer.api.DTO.StakeholderDTO;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
public class StakeholderControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<StakeholderDTO> stakeholderDTOJson;

    @Test
    @DisplayName("Deveria atualizar corretamente o email do stakeholder e receber como resposta o código HTTP 200")
    void testAtualizar() throws Exception{
        ObjectMapper mapper = new ObjectMapper();
        
        Long id = (long) 0;
        String filiacao = "filiacao_exemplo";
        String email_antes = "email@fake";
        String email_depois = "email@teste";
        String nome = "nome_exemplo";
        String cpf = "01234567891";
        String senioridade = "senioridade_exemplo";
        String cargo = "cargo_exemplo";

        var responsePOST = mvc.perform(
                                post("/stakeholder")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(stakeholderDTOJson.write(
                                    new StakeholderDTO(filiacao, email_antes, nome, cpf, senioridade, cargo)
                                ).getJson()))
            .andReturn().getResponse();

        String responseGET = mvc.perform(
                                get("/stakeholder"))
            .andReturn().getResponse().getContentAsString();
        JsonNode jsonNode = mapper.readTree(responseGET);
        for(JsonNode stakeholder : jsonNode.get("content")){
            if(stakeholder.get("email").asText().equals(email_antes)){
                id = Long.parseLong(stakeholder.get("id").asText());
            }
        }

        var responsePUT = mvc.perform(
                                put("/stakeholder")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("{\"id\":"+id+",\"email\":\""+email_depois+"\"}"))
            .andReturn().getResponse();
        assertThat(responsePUT.getStatus()).isEqualTo(HttpStatus.OK.value());

        var responseDELETE = mvc.perform(
                                delete("/stakeholder/"+id))
            .andReturn().getResponse();
    }
}
