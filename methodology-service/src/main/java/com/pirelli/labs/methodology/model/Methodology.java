package com.pirelli.labs.methodology.model;

import java.time.Instant;
import java.util.UUID;

public record Methodology(
        UUID id,
        String name,
        String description,
        String processArea,
        int expectedDurationHours,
        Instant lastReviewedAt
) {
    public Methodology {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name is required");
        }
        if (expectedDurationHours < 1) {
            throw new IllegalArgumentException("Expected duration must be positive");
        }
    }
}
