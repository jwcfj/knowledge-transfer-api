package com.knowledgetransfer.api.service;

import com.knowledgetransfer.api.DTO.AlternativaDTO;
import com.knowledgetransfer.api.DTO.AtualizacaoAlternativaDTO;
import com.knowledgetransfer.api.DTO.AtualizacaoProcessoDTO;
import com.knowledgetransfer.api.DTO.ListagemAlternativaDTO;
import com.knowledgetransfer.api.model.Alternativa;
import com.knowledgetransfer.api.model.Processo;
import com.knowledgetransfer.api.repository.AlternativaRepository;
import com.knowledgetransfer.api.repository.ProcessoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AlternativaService {

    @Autowired
    AlternativaRepository alternativaRepository;

    @Autowired
    ProcessoRepository processoRepository;

    public void cadastrar(AlternativaDTO alternativaDTO){
        Processo processo = processoRepository.findByNome(alternativaDTO.processo());
        Alternativa alternativa = new Alternativa();
        alternativa.setPergunta(alternativaDTO.pergunta());
        alternativa.setProcesso(processo);
        alternativaRepository.save(alternativa);
    }

    public Page<ListagemAlternativaDTO> listar(Pageable paginacao){
        return alternativaRepository.findAll(paginacao).map(ListagemAlternativaDTO::new);
    }

    public void atualizar(AtualizacaoAlternativaDTO dados) {
        Alternativa alternativa = alternativaRepository.getReferenceById(dados.id());
        if(dados.pergunta()!=null) alternativa.setPergunta(dados.pergunta());
        if(dados.processo()!=null) alternativa.setProcesso(processoRepository.findByNome(dados.processo()));
    }

    public void deletar(Long id){
        alternativaRepository.deleteById(id);
    }

}
