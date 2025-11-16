package com.pirelli.labs.methodology.repository;

import com.pirelli.labs.methodology.model.Methodology;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class InMemoryMethodologyRepository implements MethodologyRepository {

    private final Map<UUID, Methodology> storage = new ConcurrentHashMap<>();

    public InMemoryMethodologyRepository() {
        // seed with an example methodology so the API returns data on first boot
        Methodology seed = new Methodology(
                UUID.randomUUID(),
                "Análise de Composição",
                "Verifica a uniformidade dos compostos químicos em cada lote.",
                "Laboratório",
                12,
                Instant.now()
        );
        storage.put(seed.id(), seed);
    }

    @Override
    public Methodology save(Methodology methodology) {
        storage.put(methodology.id(), methodology);
        return methodology;
    }

    @Override
    public Optional<Methodology> findById(UUID id) {
        return Optional.ofNullable(storage.get(id));
    }

    @Override
    public Collection<Methodology> findAll() {
        return storage.values();
    }

    @Override
    public void deleteById(UUID id) {
        storage.remove(id);
    }
}
