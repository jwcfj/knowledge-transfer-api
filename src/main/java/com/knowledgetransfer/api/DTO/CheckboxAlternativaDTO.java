package com.knowledgetransfer.api.DTO;

import jakarta.validation.constraints.NotNull;

public record CheckboxAlternativaDTO(
        @NotNull
        Long alternativa_id,
        @NotNull
        boolean checked
) {

}
