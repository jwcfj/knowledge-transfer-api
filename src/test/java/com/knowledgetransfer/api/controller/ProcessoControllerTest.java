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

import com.knowledgetransfer.api.DTO.EncontrarIndicadosbyAlternativasDTOv2;
import com.knowledgetransfer.api.DTO.ProcessoDTO;
import com.knowledgetransfer.api.DTO.AlternativaDTO;
import com.knowledgetransfer.api.DTO.CheckboxAlternativaDTOv2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
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

    @Autowired
    private JacksonTester<AlternativaDTO> alternativaDTOJson;

    @Autowired
    private JacksonTester<EncontrarIndicadosbyAlternativasDTOv2> encontrarIndicadosDTOJson;

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
                break;
            }
        }
        var responseDELETE = mvc.perform(delete("/processo/"+id)).andReturn().getResponse();
        assertThat(encontrouProcesso).isEqualTo(true);
    }

    @Test
    @DisplayName("Deveria retornar a distribuição das recorrências dos processos")
    void testEncontrarIndicados() throws Exception {
        List<CheckboxAlternativaDTOv2> alternativas=new ArrayList<CheckboxAlternativaDTOv2>();
        ObjectMapper mapper = new ObjectMapper();
        List<Long> lista_id_processos = new ArrayList<>();
        List<Long> lista_id_alternativas = new ArrayList<>();
        List<Long> lista_id_possui = new ArrayList<>();
        List<Integer> recorrenciasProcessos = new ArrayList<>(Collections.nCopies(5,0));
        int j = 0;

        for(int i = 0; i < 5; i++){ //criando 3 processos de teste
            mvc.perform(
                post("/processo")
                .contentType(MediaType.APPLICATION_JSON)
                .content(processoDTOJson.write(
                    new ProcessoDTO("processo_teste_"+i, "descricao_teste_"+i)
                ).getJson()));
        }

        String responseGET = mvc.perform( //pegando os IDs dos processos criados acima
                                get("/processo"))
            .andReturn().getResponse().getContentAsString();
        JsonNode jsonNode = mapper.readTree(responseGET);
        for(JsonNode processo : jsonNode.get("content")){
            if(processo.get("nome").asText().equals("processo_teste_"+j) && processo.get("descricao").asText().equals("descricao_teste_"+j)){
                lista_id_processos.add((long) processo.get("id").asInt());
                j++;
            }
        }
        j = 0;

        for(int i = 0; i < 100; i++){ //criando 100 alternativas de teste
            mvc.perform(
                post("/alternativa")
                .contentType(MediaType.APPLICATION_JSON)
                .content(alternativaDTOJson.write(
                    new AlternativaDTO("pergunta_teste_"+i)
                ).getJson()));
        }

        responseGET = mvc.perform( //pegando os IDs das alternativas criadas acima
                                get("/alternativa?size=100&page=0"))
            .andReturn().getResponse().getContentAsString();
        jsonNode = mapper.readTree(responseGET);
        for(JsonNode alternativa : jsonNode.get("content")){
            if(alternativa.get("pergunta").asText().equals("pergunta_teste_"+j)){
                lista_id_alternativas.add((long) alternativa.get("alternativa_id").asInt());
                j++;
            }
        }
        j = 0;

        //total de 100 alternativas
        //5 apontam para os processos 0, 1 e 2
        //11 apontam somente para os processos 0 e 2
        //12 apontam somente para os processos 0 e 1
        //13 apontam somente para os processos 1 e 2
        //14 apontam somente para o processo 0
        //15 apontam somente para o processo 1
        //16 apontam somente para o processo 2
        //14 não apontam para nenhum processo

        //em relação às 5 que apontam para os processos 0, 1 e 2
        for(int i = 0; i < 5; i++){
            mvc.perform(post("/alternativa/possuir")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"alternativa_id\":"+lista_id_alternativas.get(i)+",\"processo_id\":"+lista_id_processos.get(0)+"}"));
            mvc.perform(post("/alternativa/possuir")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"alternativa_id\":"+lista_id_alternativas.get(i)+",\"processo_id\":"+lista_id_processos.get(1)+"}"));
            mvc.perform(post("/alternativa/possuir")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"alternativa_id\":"+lista_id_alternativas.get(i)+",\"processo_id\":"+lista_id_processos.get(2)+"}"));
        }

        //em relação às 11 que apontam somente para os processos 0 e 2
        for(int i = 5; i < 16; i++){
            mvc.perform(post("/alternativa/possuir")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"alternativa_id\":"+lista_id_alternativas.get(i)+",\"processo_id\":"+lista_id_processos.get(0)+"}"));
            mvc.perform(post("/alternativa/possuir")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"alternativa_id\":"+lista_id_alternativas.get(i)+",\"processo_id\":"+lista_id_processos.get(2)+"}"));
        }

        //em relação às 12 que apontam somente para os processos 0 e 1
        for(int i = 16; i < 28; i++){
            mvc.perform(post("/alternativa/possuir")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"alternativa_id\":"+lista_id_alternativas.get(i)+",\"processo_id\":"+lista_id_processos.get(0)+"}"));
            mvc.perform(post("/alternativa/possuir")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"alternativa_id\":"+lista_id_alternativas.get(i)+",\"processo_id\":"+lista_id_processos.get(1)+"}"));
        }

        //em relação às 13 que apontam somente para os processos 1 e 2
        for(int i = 28; i < 41; i++){
            mvc.perform(post("/alternativa/possuir")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"alternativa_id\":"+lista_id_alternativas.get(i)+",\"processo_id\":"+lista_id_processos.get(1)+"}"));
            mvc.perform(post("/alternativa/possuir")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"alternativa_id\":"+lista_id_alternativas.get(i)+",\"processo_id\":"+lista_id_processos.get(2)+"}"));
        }

        //em relação às 14 que apontam somente para o processo 0
        for(int i = 41; i < 55; i++){
            mvc.perform(post("/alternativa/possuir")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"alternativa_id\":"+lista_id_alternativas.get(i)+",\"processo_id\":"+lista_id_processos.get(0)+"}"));
        }

        //em relação às 15 apontam somente para o processo 1
        for(int i = 55; i < 70; i++){
            mvc.perform(post("/alternativa/possuir")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"alternativa_id\":"+lista_id_alternativas.get(i)+",\"processo_id\":"+lista_id_processos.get(1)+"}"));
        }

        //em relação às 16 apontam somente para o processo 2
        for(int i = 70; i < 86; i++){
            mvc.perform(post("/alternativa/possuir")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"alternativa_id\":"+lista_id_alternativas.get(i)+",\"processo_id\":"+lista_id_processos.get(2)+"}"));
        }

        for(int i = 0; i < lista_id_alternativas.size(); i++){
            responseGET = mvc.perform( //pegando os IDs dos possui criados acima
                                get("/alternativa/"+i+"/processos?size=100&page=0"))
            .andReturn().getResponse().getContentAsString();
            jsonNode = mapper.readTree(responseGET);
            for(JsonNode possui : jsonNode.get("content")){
                lista_id_possui.add((long) possui.get("possui_id").asInt());
            }
        }

        //o teste será da seguinte maneira:
        //das 86 alternativas em POSSUI, serão marcadas somente:
        //as 5 que apontam para os processos 0, 1 e 2
        //as 11 que apontam somente para os processos 0 e 2
        //as 12 que apontam somente para os processos 0 e 1
        //as 13 que apontam somente para os processos 1 e 2

        for(int i = 0; i < 41; i++){//41 checked
            alternativas.add(new CheckboxAlternativaDTOv2(lista_id_alternativas.get(i), true));
        }

        for(int i = 41; i < 86; i++){//45 unchecked
            alternativas.add(new CheckboxAlternativaDTOv2(lista_id_alternativas.get(i), false));
        }

        String responsePOST = mvc.perform( //realizando o POST em "/processo/indicados"
                                    post("/processo/indicados")
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .content(encontrarIndicadosDTOJson.write(
                                        new EncontrarIndicadosbyAlternativasDTOv2(alternativas)
                                    ).getJson()))
                                    .andReturn().getResponse().getContentAsString();

        jsonNode = mapper.readTree(responsePOST); //coletando a quantidade de recorrências retornadas pelo post em "/processo/indicados"
        for(JsonNode recorrencia : jsonNode.get("recorrencia_processos")){
            for(int i = 0; i < 5; i++){
                if(recorrencia.get("nome").asText().equals("processo_teste_"+i)){
                    recorrenciasProcessos.set(i, recorrencia.get("recorrencia").asInt());
                }
            }
        }

        assertThat(jsonNode.get("total").asInt()).isEqualTo(87); //checando se as quantidades de recorrências estão corretas
        assertThat(recorrenciasProcessos.get(0)).isEqualTo(28);
        assertThat(recorrenciasProcessos.get(1)).isEqualTo(30);
        assertThat(recorrenciasProcessos.get(2)).isEqualTo(29);

        for(int i = 0; i < lista_id_processos.size(); i++){ //apagando os processos criados para o teste
            mvc.perform(delete("/processo/"+lista_id_processos.get(i)));
        }

        for(int i = 0; i < lista_id_alternativas.size(); i++){ //apagando as alternativas criadas para o teste
            mvc.perform(delete("/alternativa/"+lista_id_alternativas.get(i)));
        }

        for(int i = 0; i < lista_id_possui.size(); i++){ //apagando os possui criados para o teste
            mvc.perform(delete("/alternativa/processo-possuido/"+lista_id_possui.get(i)));
        }

    }
}
