package com.knowledgetransfer.api.controller;

import com.knowledgetransfer.api.DTO.AtualizacaoFormularioDTO;
import com.knowledgetransfer.api.DTO.FormularioDTO;
import com.knowledgetransfer.api.DTO.ListagemFormularioDTO;
import com.knowledgetransfer.api.service.FormularioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/formulario")
public class FormularioController {

   // @Autowired
    //private FormularioRepository repository;

    @Autowired
    FormularioService formularioService;

    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody @Valid FormularioDTO dados){
        //FormularioService formularioService = new FormularioService();
        formularioService.cadastrar(dados);


        //System.out.println(new ObjectToJson().transform(new Formulario(dados)));

        //var test = new Formulario(dados);
        //repository.save(test);

        //repository.save(new Formulario(dados));

    }

    @DeleteMapping("/{id}")
    @Transactional
    public void deletar(@PathVariable Long id){
        formularioService.deletar(id);
    }

    @GetMapping
    public Page<ListagemFormularioDTO> listar(Pageable paginacao){
        return formularioService.listar(paginacao);
    }

    @PutMapping
    @Transactional
    public String atualizar(@RequestBody @Valid AtualizacaoFormularioDTO dados){
        return formularioService.atualizar(dados);
    }

}
