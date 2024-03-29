package com.knowledgetransfer.api.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CheckboxAlternativaDTO(
        @NotNull
        Long processo_id,
        @NotNull
        @NotBlank
        String processo,
        @NotNull
        boolean checked
) {

}
