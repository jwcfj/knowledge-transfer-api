package com.knowledgetransfer.api.DTO;

import jakarta.validation.constraints.NotNull;

import java.util.List;

public record EncontrarIndicadosbyAlternativasDTOv2(

        @NotNull
        List<CheckboxAlternativaDTOv2> alternativas
) {
}
