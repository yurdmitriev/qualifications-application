package com.yurdm.qualifications.service;

import com.yurdm.qualifications.model.users.Company;
import com.yurdm.qualifications.model.users.dto.CompanyDTO;
import com.yurdm.qualifications.repository.CompanyRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Comparator;

@Service
public class CompanyService {
    private final CompanyRepository repository;

    @Autowired
    public CompanyService(CompanyRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public Company createCompany(Company company) {
        return repository.save(company);
    }

    public Company findById(long id) {
        return repository.findById(id).orElseThrow();
    }

    public Collection<Company> listCompanies() {
        return repository.findAll();
    }

    public CompanyDTO convert(Company company) {
        return CompanyDTO.builder()
                .id(company.getId())
                .title(company.getTitle())
                .description(company.getDescription())
                .url(company.getUrl())
                .build();
    }

    public Collection<CompanyDTO> convertList(Collection<Company> original) {
        return original.stream()
                .map(this::convert)
                .sorted(Comparator.comparingLong(CompanyDTO::getId))
                .toList();
    }
}
