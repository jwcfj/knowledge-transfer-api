package com.knowledgetransfer.api.repository;

import com.knowledgetransfer.api.model.Processo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProcessoRepository extends JpaRepository<Processo,Long> {
    Processo findByNome(String nome);
}
