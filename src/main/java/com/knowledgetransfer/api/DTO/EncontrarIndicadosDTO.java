package com.knowledgetransfer.api.DTO;

import jakarta.validation.constraints.NotNull;

import java.util.List;

public record EncontrarIndicadosDTO(
        @NotNull
        List<CheckboxAlternativaDTO> respostas_alternativas
) {
}
