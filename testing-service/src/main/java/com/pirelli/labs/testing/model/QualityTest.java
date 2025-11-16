package com.pirelli.labs.testing.model;

import java.time.Instant;
import java.util.UUID;

public record QualityTest(
        UUID id,
        String batchCode,
        UUID methodologyId,
        String stage,
        String status,
        String summary,
        Instant executedAt
) {
    public QualityTest {
        if (batchCode == null || batchCode.isBlank()) {
            throw new IllegalArgumentException("Batch code is required");
        }
        if (status == null || status.isBlank()) {
            throw new IllegalArgumentException("Status is required");
        }
    }
}
