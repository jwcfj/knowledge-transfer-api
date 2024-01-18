package com.knowledgetransfer.api.DTO;

import jakarta.validation.constraints.NotBlank;

public record EtapaDTO(
        @NotBlank
        String nome_etapa,
        @NotBlank
        String artefato,
        @NotBlank
        String instrumento
) {
}
