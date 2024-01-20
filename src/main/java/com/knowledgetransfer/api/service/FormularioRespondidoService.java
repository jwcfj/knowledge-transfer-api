package com.knowledgetransfer.api.service;

import com.knowledgetransfer.api.DTO.*;
import com.knowledgetransfer.api.model.*;
import com.knowledgetransfer.api.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;

@Service
public class FormularioRespondidoService {

    @Autowired
    private FormularioRespondidoRepository formularioRespondidoRepository;

    @Autowired
    private StakeholderRepository stakeholderRepository;

    @Autowired FormularioRepository formularioRepository;

    @Autowired ProcessoRepository processoRepository;

    @Autowired
    QuestaoRepository questaoRepository;

    @Autowired AlternativaRepository alternativaRepository;

    @Autowired RespostaQuestaoRepository respostaQuestaoRepository;

    public void cadastrar(FormularioRespondidoDTO dados){
        //Stakeholder stakeholder = stakeholderRepository.getReferenceById(dados.stakeholder_id());
        //Formulario formulario = formularioRepository.getReferenceById(dados.formulario_id());
        //Processo processo = processoRepository.getReferenceById(dados.processo_id());
        FormularioRespondido formularioRespondido = new FormularioRespondido();
        //formularioRespondido.setData_criacao(dados.data_criacao());

        formularioRespondido.setData_criacao(Timestamp.from(Instant.now()));
        formularioRespondido.setStakeholder(stakeholderRepository.getReferenceById(dados.stakeholder_id()));
        formularioRespondido.setFormulario(formularioRepository.getReferenceById(dados.formulario_id()));
        formularioRespondido.setProcesso(processoRepository.getReferenceById(dados.processo_id()));
        formularioRespondido.setRespostasQuestoes(new ArrayList<>());
        for(int i=0;i<dados.respostasQuestoes().size();i++){
            RespostaQuestao respostaQuestao = new RespostaQuestao();
            respostaQuestao.setFormulario_respondido(formularioRespondido);
            respostaQuestao.setQuestao(questaoRepository.getReferenceById(dados.respostasQuestoes().get(i).questao_id()));
            respostaQuestao.setAlternativa(alternativaRepository.getReferenceById(dados.respostasQuestoes().get(i).alternativa_id()));
            formularioRespondido.getRespostasQuestoes().add(respostaQuestao);
        }
        formularioRespondidoRepository.save(formularioRespondido);

    }

    public Page<ListagemFormularioRespondidoDTO> listar(Pageable paginacao){
        return formularioRespondidoRepository.findAll(paginacao).map(ListagemFormularioRespondidoDTO::new);

    }

    public void deletar(Long id){
        formularioRespondidoRepository.deleteById(id);
    }


    public void atualizar(AtualizacaoFormularioRespondidoDTO dados) {
        if(dados.respostasQuestoes()!=null) {
            for (int i = 0; i < dados.respostasQuestoes().size(); i++) {
                if(dados.respostasQuestoes().get(i).alternativa_id()!=null){
                    RespostaQuestao respostaQuestao = respostaQuestaoRepository.getReferenceById(dados.respostasQuestoes().get(i).id());
                    respostaQuestao.setAlternativa(alternativaRepository.getReferenceById(dados.respostasQuestoes().get(i).alternativa_id()));
                    respostaQuestaoRepository.save(respostaQuestao);
                }
            }
        }

    }
}
