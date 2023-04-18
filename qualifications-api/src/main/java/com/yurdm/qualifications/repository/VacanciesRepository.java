package com.yurdm.qualifications.repository;

import com.yurdm.qualifications.model.content.Vacancy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VacanciesRepository extends JpaRepository<Vacancy, Long> {
}
