package com.pirelli.labs.testing.repository;

import com.pirelli.labs.testing.model.QualityTest;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class InMemoryQualityTestRepository implements QualityTestRepository {

    private final Map<UUID, QualityTest> storage = new ConcurrentHashMap<>();

    public InMemoryQualityTestRepository() {
        QualityTest seed = new QualityTest(
                UUID.randomUUID(),
                "LOTE-2024-05-A",
                UUID.randomUUID(),
                "Validação dinâmica",
                "APROVADO",
                "Rodagem a 160km/h por 2h sem falhas",
                Instant.now()
        );
        storage.put(seed.id(), seed);
    }

    @Override
    public QualityTest save(QualityTest qualityTest) {
        storage.put(qualityTest.id(), qualityTest);
        return qualityTest;
    }

    @Override
    public Optional<QualityTest> findById(UUID id) {
        return Optional.ofNullable(storage.get(id));
    }

    @Override
    public Collection<QualityTest> findAll() {
        return storage.values();
    }

    @Override
    public void deleteById(UUID id) {
        storage.remove(id);
    }
}
