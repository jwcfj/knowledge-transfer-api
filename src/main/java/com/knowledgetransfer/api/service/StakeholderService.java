package com.knowledgetransfer.api.service;

import com.knowledgetransfer.api.DTO.*;
import com.knowledgetransfer.api.model.Stakeholder;
import com.knowledgetransfer.api.repository.StakeholderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class StakeholderService {
    @Autowired
    private StakeholderRepository repository;

    public void cadastrar(StakeholderDTO dados){
        repository.save(new Stakeholder(dados));
    }

    public void atualizar(AtualizacaoStakeholderDTO dados) {
        Stakeholder stakeholder = repository.getReferenceById(dados.id());
        if(dados.filiacao()!=null) stakeholder.setFiliacao(dados.filiacao());
        if(dados.email()!=null)stakeholder.setEmail(dados.email());
        //if(dados.nome()!=null)stakeholder.setNome(dados.nome());
        //if(dados.cpf()!=null)stakeholder.setCpf(dados.cpf());
        if(dados.senioridade()!=null)stakeholder.setSenioridade(dados.senioridade());
        if(dados.cargo()!=null)stakeholder.setCargo(dados.cargo());
        repository.save(stakeholder);
    }

    public Page<ListagemStakeholderDTO> listar(Pageable paginacao){
        //retornar como list
        //return repository.findAll(paginacao).stream().map(ListagemFormularioDTO::new).toList();
        return repository.findAll(paginacao).map(ListagemStakeholderDTO::new);

    }

    public void deletar(Long id){
        repository.deleteById(id);
    }
}
