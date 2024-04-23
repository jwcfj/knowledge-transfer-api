package com.knowledgetransfer.api.repository;

import com.knowledgetransfer.api.DTO.ListagemProcessosPossuidosKtadminDTO;
import com.knowledgetransfer.api.model.Alternativa;
import com.knowledgetransfer.api.model.Possui;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PossuiRepository extends JpaRepository<Possui,Long> {

    Page<Possui> findAllByAlternativa(Alternativa alternativa, Pageable pageable);

    List<Possui> findAllByAlternativa(Alternativa alternativa);

    List<Possui> findAllByAlternativaIn(List<Alternativa> alternativa);


    @Query("SELECT DISTINCT p.alternativa.id FROM Possui p")
    List<Long> findAllDistinctAlternativa();
//    @Modifying
//    @Transactional
//    @Query("DELETE FROM Possui p WHERE p.id = :id")
//    void deleteByIdCustom(Long id);

}
