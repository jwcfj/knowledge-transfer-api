package com.knowledgetransfer.api.controller;

import com.knowledgetransfer.api.DTO.RespostaDeFormularioDTO;
import com.knowledgetransfer.api.service.RespostaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/resposta_de_formulario")
public class Resposta_de_FormularioController {

    @Autowired
    RespostaService respostaService;


    @PostMapping
    @Transactional
    public  void cadastrar(@RequestBody @Valid RespostaDeFormularioDTO dados){

    }

}
