package com.knowledgetransfer.api.service;

import com.knowledgetransfer.api.DTO.AtualizacaoFormularioDTO;
import com.knowledgetransfer.api.DTO.FormularioDTO;
import com.knowledgetransfer.api.DTO.ListagemFormularioDTO;
import com.knowledgetransfer.api.model.Alternativa;
import com.knowledgetransfer.api.model.Formulario;
import com.knowledgetransfer.api.model.Processo;
import com.knowledgetransfer.api.model.Questao;
import com.knowledgetransfer.api.repository.AlternativaRepository;
import com.knowledgetransfer.api.repository.FormularioRepository;
import com.knowledgetransfer.api.repository.ProcessoRepository;
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

    @Autowired
    private ProcessoRepository processoRepository;

    public String atualizar(AtualizacaoFormularioDTO dados) {

    //versao genial
    if(dados.nome()!=null){
        Formulario formulario = formularioRepository.getReferenceById(dados.id());
        formulario.setNome(dados.nome());
        formularioRepository.save(formulario);
    }
    if(dados.questoes()!=null){
        for (int i = 0;i<dados.questoes().size();i++){
            if(dados.questoes().get(i).pergunta()!=null) {
                Questao questao = questaoRepository.getReferenceById(dados.questoes().get(i).id());
                questao.setPergunta(dados.questoes().get(i).pergunta());
                questaoRepository.save(questao);
            }
            if(dados.questoes().get(i).alternativas()!=null){
                for(int j=0;j<dados.questoes().get(i).alternativas().size();j++) {
                    if (dados.questoes().get(i).alternativas().get(j).alternativa() != null) {
                        Alternativa alternativa = alternativaRepository.getReferenceById(dados.questoes().get(i).alternativas().get(j).id());
                        alternativa.setAlternativa(dados.questoes().get(i).alternativas().get(j).alternativa());
                        if(dados.questoes().get(i).alternativas().get(j).processo()!=null){
                            alternativa.setProcesso(processoRepository.getReferenceById(dados.questoes().get(i).alternativas().get(j).processo().id()));
                        }
                        alternativaRepository.save(alternativa);
                    }
                    else if(dados.questoes().get(i).alternativas().get(j).processo()!=null){
                        Alternativa alternativa = alternativaRepository.getReferenceById(dados.questoes().get(i).alternativas().get(j).id());
                        alternativa.setProcesso(processoRepository.getReferenceById(dados.questoes().get(i).alternativas().get(j).processo().id()));
                        alternativaRepository.save(alternativa);
                    }
                }
            }
        }
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
            for(int j=0; j<formularioDados.alternativas().get(i).size();j++){
                Alternativa alternativa = new Alternativa();
                alternativa.setAlternativa(formularioDados.alternativas().get(i).get(j));
                Processo processo = processoRepository.getReferenceById(formularioDados.processos_id().get(i).get(j));
                alternativa.setProcesso(  processo);
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


    public Formulario encontrarFormulario(String nome){
        Formulario formulario = formularioRepository.findByNome(nome);
        if(formulario == null){
            System.out.println("error name not found form doesnt exist");
        }
        return formulario;
    }

    public Page<ListagemFormularioDTO> listar(Pageable paginacao){
        return formularioRepository.findAll(paginacao).map(ListagemFormularioDTO::new);

    }




}
