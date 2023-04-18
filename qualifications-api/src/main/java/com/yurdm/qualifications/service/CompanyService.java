package com.yurdm.qualifications.service;

import com.yurdm.qualifications.model.users.Company;
import com.yurdm.qualifications.repository.CompanyRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
