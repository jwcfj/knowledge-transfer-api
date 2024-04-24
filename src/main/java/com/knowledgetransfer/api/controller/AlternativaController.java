package com.knowledgetransfer.api.controller;

import com.knowledgetransfer.api.DTO.*;
import com.knowledgetransfer.api.service.AlternativaService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/alternativa")
@Validated
public class AlternativaController {

    @Autowired
    AlternativaService alternativaService;


    @PostMapping
    @Transactional
    public ResponseEntity<Object> cadastrarAlternativa(@RequestBody @Valid AlternativaDTO alternativaDTO){
        try {
            return ResponseEntity.ok().body(alternativaService.cadastrarAlternativa(alternativaDTO));
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


    @GetMapping()
    public ResponseEntity<Object> listarAlternativaKtadmin(Pageable paginacao){
        try {
            return ResponseEntity.ok().body(alternativaService.listarAlternativas(paginacao));
        }catch(Exception e) {
            return ResponseEntity.internalServerError().body("Erro ao buscar Alternativas");
        }
    }

    @GetMapping("/client")
    public ResponseEntity<Object> listarAlternativaCliente(Pageable paginacao){
        try {
            return ResponseEntity.ok().body(alternativaService.listarAlternativaCliente(paginacao));
        }catch(Exception e) {
            return ResponseEntity.internalServerError().body("Erro ao buscar Alternativas");
        }
    }

    @GetMapping("/{alternativa_id}/processos")
    public ResponseEntity<Object> listarProcessosPossuidos(@PathVariable @NotNull @PositiveOrZero Long alternativa_id, Pageable paginacao){
        try {
            return ResponseEntity.ok().body(alternativaService.listarProcessosPossuidos(alternativa_id, paginacao));
        }catch(Exception e) {
            return ResponseEntity.internalServerError().body("Erro ao buscar Processos possuidos");
        }
    }

    @PutMapping
    @Transactional
    public ResponseEntity<Object> atualizarAlternativa(@RequestBody @Valid AtualizacaoAlternativaDTO dados){
        try {
            alternativaService.atualizarAlternativa(dados);
            return ResponseEntity.ok().build();
        }catch(Exception e) {
            return ResponseEntity.notFound().build();
        }
    }


    //    @PutMapping("/processo-possuido")
    //    @Transactional
    //    public ResponseEntity<AtualizacaoProcessoPossuidoDTO> atualizarProcessoPossuido(@RequestBody @Valid AtualizacaoProcessoPossuidoDTO dados){
    //        try {
    //       return  ResponseEntity.ok().body(alternativaService.atualizarProcessoPossuido(dados));
    //        }catch(Exception e) {
    //            return ResponseEntity.notFound().build();
    //        }
    //    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Object> deletar(@PathVariable @NotNull @PositiveOrZero Long id){
        try {
            alternativaService.deletar(id);
            return ResponseEntity.ok().build();
        }catch(Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/processo-possuido/{possui_id}")
    @Transactional
    public ResponseEntity<Object> deletarProcessoPossuido(@PathVariable @NotNull @PositiveOrZero Long possui_id){
        try {
            alternativaService.deletarProcessoPossuido(possui_id);
            return ResponseEntity.ok().build();
        }catch(Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
