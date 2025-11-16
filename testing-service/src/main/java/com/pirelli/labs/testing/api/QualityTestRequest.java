package com.pirelli.labs.testing.api;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.UUID;

public record QualityTestRequest(
        @NotBlank
        String batchCode,
        @NotNull
        UUID methodologyId,
        @NotBlank
        String stage,
        @NotBlank
        String status,
        @Size(max = 500)
        String summary
) {}
