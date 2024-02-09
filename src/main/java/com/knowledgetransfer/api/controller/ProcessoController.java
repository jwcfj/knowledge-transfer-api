package com.knowledgetransfer.api.controller;

import com.knowledgetransfer.api.DTO.AtualizacaoProcessoDTO;
import com.knowledgetransfer.api.DTO.ListagemProcessoDTO;
import com.knowledgetransfer.api.DTO.ListagemStakeholderDTO;
import com.knowledgetransfer.api.DTO.ProcessoDTO;
import com.knowledgetransfer.api.model.Processo;
import com.knowledgetransfer.api.service.ProcessoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/processo")
public class ProcessoController {

    @Autowired
    ProcessoService processoService;

    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody @Valid ProcessoDTO dados){
        processoService.cadastrar(dados);
    }

    @PutMapping
    @Transactional
    public void atualizar(@RequestBody @Valid AtualizacaoProcessoDTO dados){
        processoService.atualizar(dados);
    }

    @GetMapping
    public Page<ListagemProcessoDTO> listar(Pageable paginacao){
        return processoService.listar(paginacao);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void deletar(@PathVariable Long id){
        processoService.deletar(id);
    }

    @GetMapping("/{id}")
    public ListagemProcessoDTO encontrarProcesso(@PathVariable Long id){

        return processoService.encontrarProcesso(id);
    }

}
