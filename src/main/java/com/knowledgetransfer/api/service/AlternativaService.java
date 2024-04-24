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
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
    }

    public AlternativaCadastradaDTO cadastrarAlternativa(AlternativaDTO alternativaDTO){
        Alternativa alternativa = new Alternativa();
        alternativa.setPergunta(alternativaDTO.pergunta());
        alternativa = alternativaRepository.save(alternativa);
        AlternativaCadastradaDTO alternativaCadastradaDTO = new AlternativaCadastradaDTO(alternativa.getId(),alternativa.getPergunta());
        return alternativaCadastradaDTO;

    }

    public ProcessoPossuidoCadastradoDTO cadastrarProcessoPossuidov2(ProcessoPossuidoDTO processoPossuidoDTO){

        Alternativa alternativa = alternativaRepository.getReferenceById(processoPossuidoDTO.alternativa_id());
        Processo processo = processoRepository.getReferenceById(processoPossuidoDTO.processo_id());
        Possui processo_possuido = new Possui();
        processo_possuido.setAlternativa(alternativa);
        processo_possuido.setProcesso(processo);
        processo_possuido = possuiRepository.save(processo_possuido);
        ProcessoPossuidoCadastradoDTO processoPossuidoCadastradoDTO = new ProcessoPossuidoCadastradoDTO(
                processo_possuido.getProcesso().getId(),processo_possuido.getProcesso().getId(),processo_possuido.getProcesso().getNome());
        return processoPossuidoCadastradoDTO;

    }


    public Page<ListagemAlternativaDTOvold> listar(Pageable paginacao){
        return alternativaRepository.findAll(paginacao).map(ListagemAlternativaDTOvold::new);
    }

//    public Page<ListagemAlternativaDTO> listarAlternativas(Pageable paginacao){
//        return alternativaRepository.findAll(paginacao).map(ListagemAlternativaDTO::new);
//    }

    public Page<ListagemAlternativaKtadminDTO> listarAlternativas(Pageable paginacao){
        return alternativaRepository.findAll(paginacao).map(ListagemAlternativaKtadminDTO::new);
    }

    public List<ListagemAlternativaClientDTO> listarAlternativaCliente(Pageable paginacao){

        List<Long> alternativas_id = possuiRepository.findAllDistinctAlternativa();

        List<Alternativa> alternativas = alternativas_id.stream()
                .map(alternativaRepository::getReferenceById)
                .collect(Collectors.toList());


        return alternativas.stream()
                .map(ListagemAlternativaClientDTO::new)
                .collect(Collectors.toList());

    }


    public Page<ListagemProcessosPossuidosKtadminDTO> listarProcessosPossuidos( Long alternativa_id, Pageable paginacao){
        Page<Possui> possuiList = possuiRepository.findAllByAlternativa(alternativaRepository.getReferenceById(alternativa_id),paginacao);
        return possuiList.map(ListagemProcessosPossuidosKtadminDTO::new);
    }


    public AtualizacaoAlternativaDTO atualizarAlternativa(AtualizacaoAlternativaDTO dados) {
        Alternativa alternativa = alternativaRepository.getReferenceById(dados.alternativa_id());
        alternativa.setPergunta(dados.pergunta());
        return dados;
    }

    public AtualizacaoProcessoPossuidoDTO atualizarProcessoPossuido(AtualizacaoProcessoPossuidoDTO dados) {
        Possui possui = possuiRepository.getReferenceById(dados.possui_id());
        possui.setProcesso(processoRepository.getReferenceById(dados.processo_id()));
        return dados;

    }

    public void deletar(Long id){
        alternativaRepository.deleteById(id);
    }

    public void deletarProcessoPossuido(Long possui_id){
        possuiRepository.deleteById(possui_id);

    }

}
