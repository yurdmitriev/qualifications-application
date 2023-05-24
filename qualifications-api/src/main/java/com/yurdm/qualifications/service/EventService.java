package com.yurdm.qualifications.service;

import com.yurdm.qualifications.model.content.Event;
import com.yurdm.qualifications.repository.EventsRepository;
import com.yurdm.qualifications.util.PagedResponse;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EventService {
    private final EventsRepository repository;

    public EventService(EventsRepository repository) {
        this.repository = repository;
    }

    public PagedResponse<Event> listPublished(PageRequest pageRequest) {
        var page = repository.findAllByPublished(true, pageRequest);

        return PagedResponse.<Event>builder()
                .page(pageRequest.getPageNumber() + 1)
                .total(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .items(page.toList())
                .build();
    }

    public PagedResponse<Event> listAll(PageRequest pageRequest) {
        var page = repository.findAll(pageRequest);

        return PagedResponse.<Event>builder()
                .page(pageRequest.getPageNumber() + 1)
                .total(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .items(page.toList())
                .build();
    }

    public Optional<Event> getPublishedById(long id) {
        return repository.findByPublishedAndId(true, id);
    }

    public Event setPublished(long id, boolean state) {
        var event = repository.findById(id).orElseThrow();
        event.setPublished(state);
        return repository.save(event);
    }

    public void deleteById(long id) {
        repository.deleteById(id);
    }
}
