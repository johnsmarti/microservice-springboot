package com.pirelli.labs.methodology;

import com.pirelli.labs.methodology.api.MethodologyRequest;
import com.pirelli.labs.methodology.model.Methodology;
import com.pirelli.labs.methodology.repository.InMemoryMethodologyRepository;
import com.pirelli.labs.methodology.service.MethodologyService;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class MethodologyServiceTest {

    private final MethodologyService service = new MethodologyService(new InMemoryMethodologyRepository());

    @Test
    void shouldCreateMethodology() {
        MethodologyRequest request = new MethodologyRequest("Rayonização", "Processo de rayon", "Montagem", 8);
        Methodology created = service.create(request);

        assertThat(created.id()).isNotNull();
        assertThat(created.name()).isEqualTo("Rayonização");
    }

    @Test
    void shouldUpdateMethodology() {
        Methodology created = service.create(new MethodologyRequest("Cura", "Metodologia inicial", "Forno", 10));

        Methodology updated = service.update(created.id(), new MethodologyRequest("Cura", "Etapas revisadas", "Forno", 12));

        assertThat(updated.expectedDurationHours()).isEqualTo(12);
        assertThat(updated.lastReviewedAt()).isAfter(created.lastReviewedAt());
    }

    @Test
    void shouldFailWhenIdUnknown() {
        assertThatThrownBy(() -> service.find(UUID.randomUUID()))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
