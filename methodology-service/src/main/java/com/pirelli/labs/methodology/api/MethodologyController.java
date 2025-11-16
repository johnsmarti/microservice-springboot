package com.pirelli.labs.methodology.api;

import com.pirelli.labs.methodology.model.Methodology;
import com.pirelli.labs.methodology.service.MethodologyService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.UUID;

@RestController
@RequestMapping("/api/methodologies")
public class MethodologyController {

    private final MethodologyService service;

    public MethodologyController(MethodologyService service) {
        this.service = service;
    }

    @GetMapping
    public Collection<Methodology> list() {
        return service.listAll();
    }

    @GetMapping("/{id}")
    public Methodology get(@PathVariable UUID id) {
        return service.find(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Methodology create(@Valid @RequestBody MethodologyRequest request) {
        return service.create(request);
    }

    @PutMapping("/{id}")
    public Methodology update(@PathVariable UUID id, @Valid @RequestBody MethodologyRequest request) {
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        service.delete(id);
    }
}
