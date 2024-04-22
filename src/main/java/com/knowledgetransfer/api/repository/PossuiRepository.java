package com.knowledgetransfer.api.repository;

import com.knowledgetransfer.api.DTO.ListagemProcessosPossuidosKtadminDTO;
import com.knowledgetransfer.api.model.Alternativa;
import com.knowledgetransfer.api.model.Possui;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PossuiRepository extends JpaRepository<Possui,Long> {

    Page<Possui> findAllByAlternativa(Alternativa alternativa, Pageable pageable);

}
