package com.yurdm.qualifications.service;

import com.yurdm.qualifications.model.knowledge.Competency;
import com.yurdm.qualifications.model.knowledge.CompetencyViewDTO;
import com.yurdm.qualifications.repository.CompetencyRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class CompetencyService {
    private final CompetencyRepository repository;

    public static CompetencyViewDTO toViewDTO(Competency competency) {
        var result = new CompetencyViewDTO();
        result.setId(competency.getId());
        result.setTitle(competency.getTitle());

        if (competency.getParentCompetency() != null) {
            result.setParent(toViewDTO(competency.getParentCompetency()));
        }
        return result;
    }

    public CompetencyService(CompetencyRepository repository) {
        this.repository = repository;
    }

    public List<Competency> getCompetenciesByParent(long parent) {
        return repository.findById(parent).orElseThrow().getMicroCompetencies();
    }

    public List<Competency> getCompetenciesByIds(Collection<Long> ids) {
        return repository.findCompetenciesByIdIn(ids);
    }

    public List<Competency> findByTitle(String title) {
        return repository.findCompetenciesByTitleContainingIgnoreCaseAndParentCompetencyIsNotNull(title);
    }

    public List<Competency> findByParentTitle(String title) {
        return repository.findCompetenciesByTitleContainingIgnoreCaseAndParentCompetencyIsNull(title);
    }

    public List<Competency> getAll() {
        return repository.findAllByParentCompetencyIsNotNull();
    }
}
