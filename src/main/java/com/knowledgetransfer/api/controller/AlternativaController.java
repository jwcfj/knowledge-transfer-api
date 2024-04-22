package com.knowledgetransfer.api.controller;

import com.knowledgetransfer.api.DTO.*;
import com.knowledgetransfer.api.service.AlternativaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/alternativa")
public class AlternativaController {

    @Autowired
    AlternativaService alternativaService;

//    @PostMapping
//    @Transactional
//    public void cadastrar(@RequestBody @Valid AlternativaDTO alternativaDTO){
//        alternativaService.cadastrar(alternativaDTO);
//    }

    @PostMapping
    @Transactional
    public ResponseEntity<Object> cadastrarv2(@RequestBody @Valid AlternativaDTO alternativaDTO){
        try {
            return ResponseEntity.ok().body(alternativaService.cadastrarv2(alternativaDTO));
        }
        catch(Exception e) {
            return ResponseEntity.internalServerError().body("Erro ao tentar cadastrar alternativa");
        }
    }

    @PostMapping("/possuir")
    @Transactional
    public ResponseEntity<Object> cadastrarProcessoPossuido(@RequestBody @Valid ProcessoPossuidoDTO processoPossuidoDTO){
        try {
            return ResponseEntity.ok().body(alternativaService.cadastrarProcessoPossuidov2(processoPossuidoDTO));
        }
        catch(Exception e) {
            return ResponseEntity.internalServerError().body("Erro ao tentar cadastrar alternativa");
        }
    }

//    @GetMapping
//    public Page<ListagemAlternativaDTO> listar(Pageable paginacao){
//
//
//        return alternativaService.listar(paginacao);
//    }

    @GetMapping
    public Page<ListagemAlternativaClientDTO> listar(Pageable paginacao){


        return alternativaService.listarAlternativas(paginacao);
    }

    //http://localhost:8080/alternativa/1/processos?size=10&page=0
    @GetMapping("/{alternativa_id}/processos")
    public Page<ListagemProcessosPossuidosKtadminDTO> listarProcessosPossuidos(@PathVariable Long alternativa_id, Pageable paginacao){
        return alternativaService.listarProcessosPossuidos(alternativa_id,paginacao);
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
