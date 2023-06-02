package com.yurdm.qualifications.controller;

import com.yurdm.qualifications.model.users.dto.CompanyDTO;
import com.yurdm.qualifications.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/api/companies")
public class CompanyController {
    private final CompanyService service;

    @Autowired
    public CompanyController(CompanyService service) {
        this.service = service;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<Collection<CompanyDTO>> listAllCompanies() {
        return ResponseEntity.ok(service.convertList(service.listCompanies()));
    }
}
