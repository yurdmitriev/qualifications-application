package com.yurdm.qualifications.repository;

import com.yurdm.qualifications.model.content.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EventsRepository extends JpaRepository<Event, Long> {
    Page<Event> findAllByPublished(boolean published, PageRequest pageRequest);
    Optional<Event> findByPublishedAndId(boolean published, long id);
}
