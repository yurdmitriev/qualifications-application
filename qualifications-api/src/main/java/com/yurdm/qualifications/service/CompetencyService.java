package com.yurdm.qualifications.service;

import com.yurdm.qualifications.model.knowledge.Competency;
import com.yurdm.qualifications.repository.CompetencyRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class CompetencyService {
    private final CompetencyRepository repository;

    public CompetencyService(CompetencyRepository repository) {
        this.repository = repository;
    }

    public List<Competency> getCompetenciesByParent(long parent) {
        return repository.findById(parent).orElseThrow().getMicroCompetencies();
    }

    public List<Competency> getCompetenciesByIds(Collection<Long> ids) {
        return repository.findCompetenciesByIdIn(ids);
    }
}
