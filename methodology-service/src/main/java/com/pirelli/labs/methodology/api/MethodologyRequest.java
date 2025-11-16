package com.pirelli.labs.methodology.api;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record MethodologyRequest(
        @NotBlank(message = "Nome obrigatório")
        @Size(max = 80)
        String name,
        @Size(max = 500)
        String description,
        @NotBlank
        String processArea,
        @Min(1)
        int expectedDurationHours
) {}
