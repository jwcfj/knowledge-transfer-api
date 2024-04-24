package com.knowledgetransfer.api.DTO;

import jakarta.validation.constraints.NotNull;

import java.util.List;

public record EncontrarIndicadosbyAlternativasDTO(

        @NotNull
        List<CheckboxAlternativaDTO> alternativas
) {
}
