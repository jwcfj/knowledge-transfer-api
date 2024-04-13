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

import com.knowledgetransfer.api.DTO.EncontrarIndicadosDTO;
import com.knowledgetransfer.api.DTO.ProcessoDTO;
import com.knowledgetransfer.api.DTO.CheckboxAlternativaDTO;

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
    private JacksonTester<EncontrarIndicadosDTO> encontrarIndicadosDTOJson;

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
        List<CheckboxAlternativaDTO> respostas_alternativas=new ArrayList<CheckboxAlternativaDTO>();
        ObjectMapper mapper = new ObjectMapper();
        List<Long> lista_id = new ArrayList<>();
        List<Integer> recorrenciasProcessos = new ArrayList<>(Collections.nCopies(5,0));
        int j = 0;

        for(int i = 0; i < 5; i++){ //criando 5 processos de teste
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
                lista_id.add((long) processo.get("id").asInt());
                j++;
            }
        }
        j = 0;

        //o teste será da seguinte maneira:
        //total de 100 alternativas
        //5 checked para processo 0 + 4 unchecked para processo 0
        //15 checked para processo 1 + 4 unchecked para processo 1
        //40 checked para processo 2 + 4 unchecked para processo 2
        //10 checked para processo 3 + 4 unchecked para processo 3
        //10 checked para processo 4 + 4 unchecked para processo 4

        //para o processo 0
        for(int i = 0; i < 5; i++){ //5 checked
            respostas_alternativas.add(new CheckboxAlternativaDTO(lista_id.get(0), "processo_teste_0", true));
        }
        for(int i = 0; i < 4; i++){ //4 unchecked
            respostas_alternativas.add(new CheckboxAlternativaDTO(lista_id.get(0), "processo_teste_0", false));
        }

        //para o processo 1
        for(int i = 0; i < 15; i++){ //15 checked
            respostas_alternativas.add(new CheckboxAlternativaDTO(lista_id.get(1), "processo_teste_1", true));
        }
        for(int i = 0; i < 4; i++){ //4 unchecked
            respostas_alternativas.add(new CheckboxAlternativaDTO(lista_id.get(1), "processo_teste_1", false));
        }

        //para o processo 2
        for(int i = 0; i < 40; i++){ //40 checked
            respostas_alternativas.add(new CheckboxAlternativaDTO(lista_id.get(2), "processo_teste_2", true));
        }
        for(int i = 0; i < 4; i++){ //4 unchecked
            respostas_alternativas.add(new CheckboxAlternativaDTO(lista_id.get(2), "processo_teste_2", false));
        }

        //para o processo 3
        for(int i = 0; i < 10; i++){ //10 checked
            respostas_alternativas.add(new CheckboxAlternativaDTO(lista_id.get(3), "processo_teste_3", true));
        }
        for(int i = 0; i < 4; i++){ //4 unchecked
            respostas_alternativas.add(new CheckboxAlternativaDTO(lista_id.get(3), "processo_teste_3", false));
        }

        //para o processo 4
        for(int i = 0; i < 10; i++){ //10 checked
            respostas_alternativas.add(new CheckboxAlternativaDTO(lista_id.get(4), "processo_teste_4", true));
        }
        for(int i = 0; i < 4; i++){ //4 unchecked
            respostas_alternativas.add(new CheckboxAlternativaDTO(lista_id.get(4), "processo_teste_4", false));
        }

        String responsePOST = mvc.perform( //realizando o POST em "/processo/indicados"
                                    post("/processo/indicados")
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .content(encontrarIndicadosDTOJson.write(
                                        new EncontrarIndicadosDTO(respostas_alternativas)
                                    ).getJson()))
                                    .andReturn().getResponse().getContentAsString();

        //assertThat(responsePOST).isEqualTo("jooj");

        jsonNode = mapper.readTree(responsePOST); //coletando a quantidade de recorrências retornadas pelo post em "/processo/indicados"
        for(JsonNode recorrencia : jsonNode.get("recorrencia_processos")){
            for(int i = 0; i < 5; i++){
                if(recorrencia.get("nome").asText().equals("processo_teste_"+i)){
                    recorrenciasProcessos.set(i, recorrencia.get("recorrencia").asInt());
                }
            }
        }

        assertThat(jsonNode.get("total").asInt()).isEqualTo(80); //checando se as quantidades de recorrências estão corretas
        assertThat(recorrenciasProcessos.get(0)).isEqualTo(5);
        assertThat(recorrenciasProcessos.get(1)).isEqualTo(15);
        assertThat(recorrenciasProcessos.get(2)).isEqualTo(40);
        assertThat(recorrenciasProcessos.get(3)).isEqualTo(10);
        assertThat(recorrenciasProcessos.get(4)).isEqualTo(10);

        for(int i = 0; i < 5; i++){ //apagando os processos criados para o teste
            mvc.perform(delete("/processo/"+lista_id.get(i)));
        }

    }
}
