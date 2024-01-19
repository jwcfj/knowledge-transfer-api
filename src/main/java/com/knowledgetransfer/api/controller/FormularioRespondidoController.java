package com.knowledgetransfer.api.controller;

import com.knowledgetransfer.api.DTO.*;
import com.knowledgetransfer.api.service.FormularioRespondidoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/resposta_de_formulario")
public class FormularioRespondidoController {

    @Autowired
    FormularioRespondidoService formularioRespondidoService;


    @PostMapping
    @Transactional
    public  void cadastrar(@RequestBody @Valid FormularioRespondidoDTO dados){
        formularioRespondidoService.cadastrar(dados);
    }

    @GetMapping
    public Page<ListagemFormularioRespondidoDTO> listar(Pageable paginacao){
        return formularioRespondidoService.listar(paginacao);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void deletar(@PathVariable Long id){
        formularioRespondidoService.deletar(id);
    }


    @PutMapping
    @Transactional
    public void atualizar(@RequestBody @Valid AtualizacaoFormularioRespondidoDTO dados){
        formularioRespondidoService.atualizar(dados);
    }
}
