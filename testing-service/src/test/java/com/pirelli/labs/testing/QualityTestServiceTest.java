package com.pirelli.labs.testing;

import com.pirelli.labs.testing.api.QualityTestRequest;
import com.pirelli.labs.testing.model.QualityTest;
import com.pirelli.labs.testing.repository.InMemoryQualityTestRepository;
import com.pirelli.labs.testing.service.QualityTestService;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class QualityTestServiceTest {

    private final QualityTestService service = new QualityTestService(new InMemoryQualityTestRepository());

    @Test
    void shouldCreateQualityTest() {
        UUID methodologyId = UUID.randomUUID();
        QualityTestRequest request = new QualityTestRequest(
                "LOTE-01",
                methodologyId,
                "Cura",
                "EM_EXECUCAO",
                "Aguardando inspeção final"
        );

        QualityTest created = service.create(request);

        assertThat(created.id()).isNotNull();
        assertThat(created.methodologyId()).isEqualTo(methodologyId);
    }

    @Test
    void shouldUpdateQualityTest() {
        QualityTest created = service.create(new QualityTestRequest(
                "LOTE-02",
                UUID.randomUUID(),
                "Vulcanização",
                "PENDENTE",
                "Preparando setup"
        ));

        QualityTest updated = service.update(created.id(), new QualityTestRequest(
                created.batchCode(),
                created.methodologyId(),
                created.stage(),
                "APROVADO",
                "Teste concluído sem desvios"
        ));

        assertThat(updated.status()).isEqualTo("APROVADO");
        assertThat(updated.executedAt()).isAfter(created.executedAt());
    }

    @Test
    void shouldDeleteQualityTest() {
        QualityTest created = service.create(new QualityTestRequest(
                "LOTE-03",
                UUID.randomUUID(),
                "Inspeção",
                "EM_ANALISE",
                "Checklist parcial"
        ));

        service.delete(created.id());

        assertThatThrownBy(() -> service.find(created.id()))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
