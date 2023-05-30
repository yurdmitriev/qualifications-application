package com.yurdm.qualifications.repository;

import com.yurdm.qualifications.model.knowledge.Competency;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface CompetencyRepository extends JpaRepository<Competency, Long> {
    List<Competency> findCompetenciesByIdIn(Collection<Long> ids);
}
