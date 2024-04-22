package com.knowledgetransfer.api.service;

import com.knowledgetransfer.api.DTO.*;
import com.knowledgetransfer.api.model.Alternativa;
import com.knowledgetransfer.api.model.Possui;
import com.knowledgetransfer.api.model.Processo;
import com.knowledgetransfer.api.repository.AlternativaRepository;
import com.knowledgetransfer.api.repository.PossuiRepository;
import com.knowledgetransfer.api.repository.ProcessoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class AlternativaService {

    @Autowired
    PossuiRepository possuiRepository;

    @Autowired
    AlternativaRepository alternativaRepository;

    @Autowired
    ProcessoRepository processoRepository;

    public void cadastrar(AlternativaDTO alternativaDTO){
        Alternativa alternativa = new Alternativa();
        alternativa.setPergunta(alternativaDTO.pergunta());
        alternativaRepository.save(alternativa);

//        Pattern pattern = Pattern.compile("\\d+");
//        Matcher matcher = pattern.matcher(alternativaDTO.processos());
//        while (matcher.find()) {
//            Processo processo = processoRepository.getReferenceById(Long.parseLong(matcher.group()));
//            Possui possui = new Possui();
//            possui.setAlternativa(alternativa);
//            possui.setProcesso(processo);
//            possuiRepository.save(possui);
//        }

    }

    public AlternativaCadastradaDTO cadastrarv2(AlternativaDTO alternativaDTO){
        Alternativa alternativa = new Alternativa();
        alternativa.setPergunta(alternativaDTO.pergunta());
        alternativa = alternativaRepository.save(alternativa);
        AlternativaCadastradaDTO alternativaCadastradaDTO = new AlternativaCadastradaDTO(alternativa.getId(),alternativa.getPergunta());
        return alternativaCadastradaDTO;

    }

    public ProcessoPossuidoCadastradoDTO cadastrarProcessoPossuidov2(ProcessoPossuidoDTO processoPossuidoDTO){
//        Alternativa alternativa = new Alternativa();
//        alternativa.setPergunta(alternativaDTO.pergunta());
//        alternativaRepository.save(alternativa);

        Alternativa alternativa = alternativaRepository.getReferenceById(processoPossuidoDTO.alternativa_id());
        Processo processo = processoRepository.getReferenceById(processoPossuidoDTO.processo_id());
        Possui processo_possuido = new Possui();
        processo_possuido.setAlternativa(alternativa);
        processo_possuido.setProcesso(processo);
        processo_possuido = possuiRepository.save(processo_possuido);
        ProcessoPossuidoCadastradoDTO processoPossuidoCadastradoDTO = new ProcessoPossuidoCadastradoDTO(processo_possuido.getAlternativa().getId(),processo_possuido.getProcesso().getId(),processo_possuido.getProcesso().getNome());
        return processoPossuidoCadastradoDTO;
//        Pattern pattern = Pattern.compile("\\d+");
//        Matcher matcher = pattern.matcher(alternativaDTO.processos());
//        while (matcher.find()) {
//            Processo processo = processoRepository.getReferenceById(Long.parseLong(matcher.group()));
//            Possui possui = new Possui();
//            possui.setAlternativa(alternativa);
//            possui.setProcesso(processo);
//            possuiRepository.save(possui);
//        }

    }

//    public void cadastrarProcessoPossuido(AlternativaDTO alternativaDTO){
//        Alternativa alternativa = new Alternativa();
//        alternativa.setPergunta(alternativaDTO.pergunta());
//        alternativaRepository.save(alternativa);
//
//        Pattern pattern = Pattern.compile("\\d+");
//        Matcher matcher = pattern.matcher(alternativaDTO.processos());
//        while (matcher.find()) {
//            Processo processo = processoRepository.getReferenceById(Long.parseLong(matcher.group()));
//            Possui possui = new Possui();
//            possui.setAlternativa(alternativa);
//            possui.setProcesso(processo);
//            possuiRepository.save(possui);
//        }
//
//    }

    public Page<ListagemAlternativaDTO> listar(Pageable paginacao){
        return alternativaRepository.findAll(paginacao).map(ListagemAlternativaDTO::new);
    }

    //esse endpoint sera usado no client
    public Page<ListagemAlternativaClientDTO> listarAlternativas(Pageable paginacao){
        return alternativaRepository.findAll(paginacao).map(ListagemAlternativaClientDTO::new);
    }
    @Autowired
    private JdbcTemplate jdbcTemplate;

    //esse endpoint seria usado no ktadmin
    public Page<ListagemProcessosPossuidosKtadminDTO> listarProcessosPossuidos( Long alternativa_id, Pageable paginacao){
        Page<Possui> possuiList = possuiRepository.findAllByAlternativa(alternativaRepository.getReferenceById(alternativa_id),paginacao);
        return possuiList.map(ListagemProcessosPossuidosKtadminDTO::new);
    }

    public void atualizar(AtualizacaoAlternativaDTO dados) {
        Alternativa alternativa = alternativaRepository.getReferenceById(dados.id());
        if(dados.pergunta()!=null) alternativa.setPergunta(dados.pergunta());
       // if(dados.processo()!=null) alternativa.setProcesso(processoRepository.findByNome(dados.processo()));
    }

    public void atualizarAlternativa(AtualizacaoAlternativaDTOv2 dados) {
        Alternativa alternativa = alternativaRepository.getReferenceById(dados.id());
        if(dados.pergunta()!=null) alternativa.setPergunta(dados.pergunta());
    }

    //AJEITAR ISSO AQUI
    public void atualizarProcessoPossuido(AtualizacaoAlternativaDTOv2 dados) {
        Alternativa alternativa = alternativaRepository.getReferenceById(dados.id());
        if(dados.pergunta()!=null) alternativa.setPergunta(dados.pergunta());
    }

    public void deletar(Long id){
        alternativaRepository.deleteById(id);
    }

}
