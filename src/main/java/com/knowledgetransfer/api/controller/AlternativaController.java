package com.knowledgetransfer.api.controller;

import com.knowledgetransfer.api.DTO.AlternativaDTO;
import com.knowledgetransfer.api.DTO.AtualizacaoAlternativaDTO;
import com.knowledgetransfer.api.DTO.ListagemAlternativaDTO;
import com.knowledgetransfer.api.service.AlternativaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/alternativa")
public class AlternativaController {

    @Autowired
    AlternativaService alternativaService;

    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody @Valid AlternativaDTO alternativaDTO){
        alternativaService.cadastrar(alternativaDTO);
    }

    @GetMapping
    public Page<ListagemAlternativaDTO> listar(Pageable paginacao){
        return alternativaService.listar(paginacao);
    }

    @PutMapping
    @Transactional
    public void atualizar(@RequestBody @Valid AtualizacaoAlternativaDTO dados){
        alternativaService.atualizar(dados);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void deletar(@PathVariable Long id){
        alternativaService.deletar(id);
    }
}
