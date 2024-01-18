package com.knowledgetransfer.api.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.util.List;


public record AtualizacaoStakeholderDTO(
        @NotNull
        Long id,
        String filiacao,
        @Email
        String email,
        String nome,
        @Pattern(regexp = "\\d{11}")
        String cpf,
        String senioridade,
        String cargo){}
