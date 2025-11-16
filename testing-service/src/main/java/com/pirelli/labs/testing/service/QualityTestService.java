package com.pirelli.labs.testing.service;

import com.pirelli.labs.testing.api.QualityTestRequest;
import com.pirelli.labs.testing.model.QualityTest;
import com.pirelli.labs.testing.repository.QualityTestRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Collection;
import java.util.UUID;

@Service
public class QualityTestService {

    private final QualityTestRepository repository;

    public QualityTestService(QualityTestRepository repository) {
        this.repository = repository;
    }

    public Collection<QualityTest> listAll() {
        return repository.findAll();
    }

    public QualityTest find(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Teste não encontrado: " + id));
    }

    public QualityTest create(QualityTestRequest request) {
        QualityTest qualityTest = new QualityTest(
                UUID.randomUUID(),
                request.batchCode(),
                request.methodologyId(),
                request.stage(),
                request.status(),
                request.summary(),
                Instant.now()
        );
        return repository.save(qualityTest);
    }

    public QualityTest update(UUID id, QualityTestRequest request) {
        QualityTest current = find(id);
        QualityTest updated = new QualityTest(
                current.id(),
                request.batchCode(),
                request.methodologyId(),
                request.stage(),
                request.status(),
                request.summary(),
                Instant.now()
        );
        return repository.save(updated);
    }

    public void delete(UUID id) {
        repository.deleteById(id);
    }
}
