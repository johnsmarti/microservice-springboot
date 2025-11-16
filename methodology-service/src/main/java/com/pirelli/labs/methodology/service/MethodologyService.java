package com.pirelli.labs.methodology.service;

import com.pirelli.labs.methodology.api.MethodologyRequest;
import com.pirelli.labs.methodology.model.Methodology;
import com.pirelli.labs.methodology.repository.MethodologyRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Collection;
import java.util.UUID;

@Service
public class MethodologyService {

    private final MethodologyRepository repository;

    public MethodologyService(MethodologyRepository repository) {
        this.repository = repository;
    }

    public Collection<Methodology> listAll() {
        return repository.findAll();
    }

    public Methodology find(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Metodologia não encontrada: " + id));
    }

    public Methodology create(MethodologyRequest request) {
        Methodology methodology = new Methodology(
                UUID.randomUUID(),
                request.name(),
                request.description(),
                request.processArea(),
                request.expectedDurationHours(),
                Instant.now()
        );
        return repository.save(methodology);
    }

    public Methodology update(UUID id, MethodologyRequest request) {
        Methodology current = find(id);
        Methodology updated = new Methodology(
                current.id(),
                request.name(),
                request.description(),
                request.processArea(),
                request.expectedDurationHours(),
                Instant.now()
        );
        return repository.save(updated);
    }

    public void delete(UUID id) {
        repository.deleteById(id);
    }
}
