package com.knowledgetransfer.api.model;

import com.knowledgetransfer.api.DTO.CheckboxAlternativaDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CheckboxAlternativa {
    Long processo_id;
    String processo_nome;
    boolean checked;

    public CheckboxAlternativa(CheckboxAlternativaDTO checkboxAlternativaDTO) {
        this.processo_id = checkboxAlternativaDTO.processo_id();
        this.processo_nome = checkboxAlternativaDTO.processo();
        this.checked = checkboxAlternativaDTO.checked();
    }
    public CheckboxAlternativa() {
    }

    public Long getProcesso_id() {
        return processo_id;
    }

    public void setProcesso_id(Long processo_id) {
        this.processo_id = processo_id;
    }

    public String getProcesso_nome() {
        return processo_nome;
    }

    public void setProcesso_nome(String processo_nome) {
        this.processo_nome = processo_nome;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}
