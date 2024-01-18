package com.knowledgetransfer.api.repository;

import com.knowledgetransfer.api.model.Formulario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FormularioRepository extends JpaRepository <Formulario, Long> {

    Formulario findByNome(String nome);


}
