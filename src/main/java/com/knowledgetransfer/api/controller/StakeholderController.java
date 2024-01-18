package com.knowledgetransfer.api.controller;

import com.knowledgetransfer.api.DTO.AtualizacaoStakeholderDTO;
import com.knowledgetransfer.api.DTO.ListagemStakeholderDTO;
import com.knowledgetransfer.api.DTO.StakeholderDTO;
import com.knowledgetransfer.api.service.StakeholderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/stakeholder")
public class StakeholderController {


    @Autowired
    StakeholderService stakeholderService;

    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody @Valid StakeholderDTO dados)
    {
        //var test = new Stakeholder(dados);
        //repository.save(test);
        stakeholderService.cadastrar(dados);
        //repository.save(new Stakeholder(dados));
    }

    @PutMapping
    @Transactional
    public void atualizar(@RequestBody @Valid AtualizacaoStakeholderDTO dados){
        stakeholderService.atualizar(dados);
    }

    @GetMapping
    public Page<ListagemStakeholderDTO> listar(Pageable paginacao){
        return stakeholderService.listar(paginacao);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void deletar(@PathVariable Long id){
        stakeholderService.deletar(id);
    }

}
