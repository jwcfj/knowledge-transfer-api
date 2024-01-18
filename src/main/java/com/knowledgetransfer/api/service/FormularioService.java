package com.knowledgetransfer.api.service;

import com.knowledgetransfer.api.DTO.AtualizacaoFormularioDTO;
import com.knowledgetransfer.api.DTO.FormularioDTO;
import com.knowledgetransfer.api.DTO.ListagemFormularioDTO;
import com.knowledgetransfer.api.model.Alternativa;
import com.knowledgetransfer.api.model.Formulario;
import com.knowledgetransfer.api.model.Questao;
import com.knowledgetransfer.api.repository.AlternativaRepository;
import com.knowledgetransfer.api.repository.FormularioRepository;
import com.knowledgetransfer.api.repository.QuestaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FormularioService {
    //model com regra de negocio
    @Autowired
    private FormularioRepository formularioRepository;

    @Autowired
    private QuestaoRepository questaoRepository;

    @Autowired
    private AlternativaRepository alternativaRepository;

    public String atualizar(AtualizacaoFormularioDTO dados) {
        Formulario formulario = formularioRepository.getReferenceById(dados.id());
    if(dados.nome()!=null) formulario.setNome(dados.nome());
    if(dados.perguntas()!=null) {
        List<Questao> questionario = new ArrayList<>();
        for(int i = 0;i<formulario.getQuestionario().size();i++){
            formulario.getQuestionario().get(i).setPergunta(dados.perguntas().get(i));
            for(int j = 0;j<formulario.getQuestionario().get(i).getAlternativas().size();j++) {
                formulario.getQuestionario().get(i).getAlternativas().get(j).setAlternativa(dados.alternativas().get(i).get(j));
                formulario.getQuestionario().get(i).getAlternativas().get(j).setProcesso(dados.processos_alternativas().get(i).get(j));
            }
        }
        if(formulario.getQuestionario().size() < dados.perguntas().size()){
            for(int i = formulario.getQuestionario().size(); i < dados.perguntas().size();i++){
                Questao questao = new Questao();
                questao.setPergunta(dados.perguntas().get(i));

                for(int j = 0; j < dados.alternativas().size();j++){
                    Alternativa alternativa = new Alternativa();
                    alternativa.setAlternativa(dados.alternativas().get(i).get(j));
                    alternativa.setProcesso(dados.processos_alternativas().get(i).get(j));
                    questao.getAlternativas().add(alternativa);
                }

                formulario.getQuestionario().add(questao);
            }
        }

        /*for(int i = 0;i<formulario.getQuestionario().size();i++){
            if(formulario.getQuestionario().get(i).getPergunta() == dados.perguntas())
        }*/

    }

        return "okay";

    }
    public void deletar(Long id){
        formularioRepository.deleteById(id);
    }

    public String cadastrar(FormularioDTO formularioDados){
        Formulario formulario = new Formulario();
        formulario.setNome(formularioDados.nome());
        formulario.setQuestionario(new ArrayList<>());
        for (int i =0;i<formularioDados.perguntas().size();i++){
            Questao questao = new Questao();
            questao.setPergunta(formularioDados.perguntas().get(i));
            questao.setFormulario(formulario);
            questao.setAlternativas(new ArrayList<>());
            for(int j=0; j<formularioDados.alternativas().size();j++){
                Alternativa alternativa = new Alternativa();
                alternativa.setAlternativa(formularioDados.alternativas().get(i).get(j));
                alternativa.setProcesso(formularioDados.processos_alternativas().get(i).get(j));
                alternativa.setQuestao(questao);
                questao.getAlternativas().add(alternativa);
            }
            formulario.getQuestionario().add(questao);
        }
        formularioRepository.save(formulario);


        //formulario.setNome(formularioDados.nome());
        //formulario.setQuestionario(new ArrayList<>());
        //formulario.setQuestionario(generateQuestaoList(formularioDados.perguntas(),formularioDados.alternativas(),formularioDados.processos_alternativas(), formulario));
        /*for(int i=0;i<formularioDados.perguntas().size();i++){
            Questao novaQuestao = new Questao(new QuestaoDTO(formularioDados.perguntas().remove(0),formularioDados.alternativas().remove(0),formularioDados.processos_alternativas().remove(0)));
            novaQuestao.setFormulario(formulario);
            formulario.getQuestionario().add(novaQuestao);
        }*/

        //formularioRepository.save(formulario);
        return "okay";
    }

    private List<Questao> generateQuestaoList(List<String> perguntas, List<List<String>> alternativas, List<List<String>> processos_alternativas, Formulario formulario){
            List<Questao> questionario = new ArrayList<>();
            for(int i=0, max = perguntas.size();i<max;i++) {
                Questao novaQuestao = new Questao();
                novaQuestao.setPergunta(perguntas.remove(0));
                novaQuestao.setAlternativas(generateAlternativaList(alternativas.remove(0),processos_alternativas.remove(0),novaQuestao));
                novaQuestao.setFormulario(formulario);
                questionario.add(novaQuestao);
            }
        return questionario;
    }

    private List<Alternativa> generateAlternativaList(List<String> alternativas, List<String> processos_alternativas, Questao questao){
        List<Alternativa> alternativasReturn = new ArrayList<>();
        for(int i=0, max =alternativas.size(); i<max;i++){
            Alternativa novaAlternativa = new Alternativa();
            novaAlternativa.setAlternativa(alternativas.remove(0));
            novaAlternativa.setProcesso(processos_alternativas.remove(0));
            novaAlternativa.setQuestao(questao);

            alternativasReturn.add(novaAlternativa);
        }
        return alternativasReturn;

    }

    public Formulario encontrarFormulario(String nome){
        Formulario formulario = formularioRepository.findByNome(nome);
        if(formulario == null){
            System.out.println("error name not found form doesnt exist");
        }
        return formulario;
    }

    public Page<ListagemFormularioDTO> listar(Pageable paginacao){
        //retornar como list
        //return repository.findAll(paginacao).stream().map(ListagemFormularioDTO::new).toList();
        return formularioRepository.findAll(paginacao).map(ListagemFormularioDTO::new);

    }



}
