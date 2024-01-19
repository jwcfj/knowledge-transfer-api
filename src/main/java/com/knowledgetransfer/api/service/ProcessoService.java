package com.knowledgetransfer.api.service;

import com.knowledgetransfer.api.DTO.*;
import com.knowledgetransfer.api.model.Processo;
import com.knowledgetransfer.api.model.Stakeholder;
import com.knowledgetransfer.api.repository.ProcessoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class ProcessoService {

    @Autowired
    private ProcessoRepository processoRepository;

    public void cadastrar(ProcessoDTO dados){
        processoRepository.save(new Processo(dados));
    }

    public void atualizar(AtualizacaoProcessoDTO dados) {
        Processo processo = processoRepository.getReferenceById(dados.id());
        if(dados.descricao()!=null) processo.setDescricao(dados.descricao());
        if(dados.nome()!=null) processo.setNome(dados.nome());
    }

    public Page<ListagemProcessoDTO> listar(Pageable paginacao){
        //retornar como list
        //return repository.findAll(paginacao).stream().map(ListagemFormularioDTO::new).toList();
        return processoRepository.findAll(paginacao).map(ListagemProcessoDTO::new);

    }

    public void deletar(Long id){
        processoRepository.deleteById(id);
    }

    public void encontrarProcesso(Long id){
        Processo processo = processoRepository.getReferenceById(id);
        System.out.println();
    }

}
