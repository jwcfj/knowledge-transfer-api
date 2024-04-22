package com.knowledgetransfer.api.DTO;

import com.knowledgetransfer.api.model.Processo;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record ProcessoDTO(
        @NotNull @NotBlank
        String nome,
        @NotNull @NotBlank
        String descricao
) {
        public ProcessoDTO(Processo processo){
                this(processo.getNome(), processo.getDescricao());
       }

}
