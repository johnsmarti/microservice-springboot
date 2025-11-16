package com.pirelli.labs.testing.repository;

import com.pirelli.labs.testing.model.QualityTest;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

public interface QualityTestRepository {
    QualityTest save(QualityTest qualityTest);

    Optional<QualityTest> findById(UUID id);

    Collection<QualityTest> findAll();

    void deleteById(UUID id);
}
