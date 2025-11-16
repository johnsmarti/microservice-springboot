package com.pirelli.labs.methodology.repository;

import com.pirelli.labs.methodology.model.Methodology;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

public interface MethodologyRepository {
    Methodology save(Methodology methodology);

    Optional<Methodology> findById(UUID id);

    Collection<Methodology> findAll();

    void deleteById(UUID id);
}
