package com.pirelli.labs.testing.api;

import com.pirelli.labs.testing.model.QualityTest;
import com.pirelli.labs.testing.service.QualityTestService;
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
@RequestMapping("/api/tests")
public class QualityTestController {

    private final QualityTestService service;

    public QualityTestController(QualityTestService service) {
        this.service = service;
    }

    @GetMapping
    public Collection<QualityTest> list() {
        return service.listAll();
    }

    @GetMapping("/{id}")
    public QualityTest get(@PathVariable UUID id) {
        return service.find(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public QualityTest create(@Valid @RequestBody QualityTestRequest request) {
        return service.create(request);
    }

    @PutMapping("/{id}")
    public QualityTest update(@PathVariable UUID id, @Valid @RequestBody QualityTestRequest request) {
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        service.delete(id);
    }
}
