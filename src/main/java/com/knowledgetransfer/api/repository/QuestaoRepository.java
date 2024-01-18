package com.knowledgetransfer.api.repository;

import com.knowledgetransfer.api.model.Questao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestaoRepository extends JpaRepository<Questao, Long> {
}
