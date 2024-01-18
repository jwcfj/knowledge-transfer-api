package com.knowledgetransfer.api.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record StakeholderDTO(
        @NotBlank
        String filiacao,
        @NotBlank
        @Email
        String email,
        @NotBlank
        String nome,
        @NotBlank
        @Pattern(regexp = "\\d{11}")
        String cpf,
        @NotBlank
        String senioridade,
        @NotBlank
        String cargo) {
}
