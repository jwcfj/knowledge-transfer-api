package com.knowledgetransfer.api.controller;

import com.knowledgetransfer.api.DTO.*;
import com.knowledgetransfer.api.model.Processo;
import com.knowledgetransfer.api.service.ProcessoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/processo")
public class ProcessoController {

    @Autowired
    ProcessoService processoService;

    @PostMapping
    @Transactional
    public  ResponseEntity<Object> cadastrar(@RequestBody @Valid ProcessoDTO dados){
        try {
            return ResponseEntity.ok().body(processoService.cadastrar(dados));
        }
        catch(Exception e) {
            return ResponseEntity.internalServerError().body("Erro ao tentar cadastrar Processo");
        }
    }

    @PutMapping
    @Transactional
    public ResponseEntity<Object> atualizar(@RequestBody @Valid AtualizacaoProcessoDTO dados){
        try {
            processoService.atualizar(dados);
            return ResponseEntity.ok().build();
        }
        catch(Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<Object> listar(Pageable paginacao){
        try {
            return ResponseEntity.ok().body(processoService.listar(paginacao));
        }catch(Exception e) {
            return ResponseEntity.internalServerError().body("Erro ao buscar Processos");
        }
    }

    @PostMapping("/indicados")

    //public RecorrenciaDTO encontrarIndicados(@RequestBody @Valid EncontrarIndicadosDTO indicados){
    public ResponseEntity<Object> encontrarIndicados(@RequestBody @Valid EncontrarIndicadosbyAlternativasDTOv2 indicados){
        try {
            return ResponseEntity.ok().body(processoService.encontrarIndicadosv2(indicados));
        }catch(Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Object> deletar(@PathVariable Long id){
        try {
            processoService.deletar(id);
            return ResponseEntity.ok().build();
        }catch(Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> encontrarProcesso(@PathVariable Long id){
        ListagemProcessoDTO processo = processoService.encontrarProcesso(id);
        if(processo == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("");
        }
        return ResponseEntity.status(HttpStatus.OK).body(processo);
    }

}
