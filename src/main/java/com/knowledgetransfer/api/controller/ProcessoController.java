package com.knowledgetransfer.api.controller;

import com.knowledgetransfer.api.DTO.*;
import com.knowledgetransfer.api.model.Processo;
import com.knowledgetransfer.api.service.ProcessoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    @PostMapping("/indicados")
    //public IndicadosDTO encontrarIndicados(EncontrarIndicadosDTO indicados){
//        public List<RecorrenciaProcessoDTO> encontrarIndicados(@RequestBody @Valid EncontrarIndicadosDTO indicados){
    public RecorrenciaDTO encontrarIndicados(@RequestBody @Valid EncontrarIndicadosDTO indicados){
//    public void encontrarIndicados(@RequestBody Object indicados){
        return processoService.encontrarIndicados(indicados);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void deletar(@PathVariable Long id){
        processoService.deletar(id);
    }

    @PostMapping("/{id}")
    public void encontrarProcesso(@PathVariable Long id){

        processoService.encontrarProcesso(id);
    }

}
