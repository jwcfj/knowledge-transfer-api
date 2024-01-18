package com.knowledgetransfer.api.service;

import com.knowledgetransfer.api.DTO.RespostaDeFormularioDTO;
import com.knowledgetransfer.api.model.Formulario;
import com.knowledgetransfer.api.model.Stakeholder;
import com.knowledgetransfer.api.repository.FormularioRepository;
import com.knowledgetransfer.api.repository.ProcessoRepository;
import com.knowledgetransfer.api.repository.Resposta_de_FormularioRepository;
import com.knowledgetransfer.api.repository.StakeholderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RespostaService {

    @Autowired
    private Resposta_de_FormularioRepository respostaDeFormularioRepository;

    @Autowired
    private StakeholderRepository stakeholderRepository;

    @Autowired FormularioRepository formularioRepository;

    @Autowired ProcessoRepository processoRepository;

    public void cadastrar(RespostaDeFormularioDTO dados){
        Stakeholder stakeholder = stakeholderRepository.getReferenceById(dados.stakeholder_id());
        Formulario formulario = formularioRepository.getReferenceById(dados.formulario_id());


    }
}
