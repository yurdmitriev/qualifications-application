package com.yurdm.qualifications.repository;

import com.yurdm.qualifications.model.content.Vacancy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VacanciesRepository extends JpaRepository<Vacancy, Long> {
    Page<Vacancy> findAllByPublished(boolean published, PageRequest pageRequest);
    Optional<Vacancy> findByPublishedAndId(boolean published, long id);
}
