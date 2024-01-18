package com.knowledgetransfer.api.DTO;

import jakarta.validation.constraints.NotNull;

import java.util.List;

public record AlternativaDTO(
        @NotNull
        String alternativa,
        @NotNull
        String processo) {
}
