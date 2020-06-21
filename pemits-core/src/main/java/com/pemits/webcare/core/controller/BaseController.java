package com.pemits.webcare.core.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.validation.Valid;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.pemits.webcare.core.dto.RestResponseDto;
import com.pemits.webcare.core.service.BaseService;
import com.pemits.webcare.core.utils.PaginationUtils;

/**
 * @param <E> Entity
 * @param <I> ID
 * @author Elvin Shrestha on 6/21/2020
 */
public abstract class BaseController<E, I> {

    private final Logger logger;

    private final BaseService<E, I> service;

    protected BaseController(
        BaseService<E, I> service,
        Class<?> loggerClass
    ) {
        this.service = service;
        this.logger = LoggerFactory.getLogger(loggerClass);
    }

    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody E e) {

        final E saved = service.save(e);

        if (null == saved) {
            logger.error("Error while saving {}", e);
            return new RestResponseDto()
                .fail(HttpStatus.INTERNAL_SERVER_ERROR, Optional.of("Error while saving " + e));
        }

        return new RestResponseDto().success(saved);
    }

    @PostMapping("/all")
    public ResponseEntity<?> saveAll(@Valid @RequestBody List<E> e) {

        final List<E> saved = service.saveAll(e);

        if (null == saved) {
            logger.error("Error while saving {}", e);
            return new RestResponseDto()
                .fail(HttpStatus.INTERNAL_SERVER_ERROR, Optional.of("Error while saving " + e));
        }

        return new RestResponseDto().success(saved);
    }

    @GetMapping("/{i}")
    public ResponseEntity<?> getById(@PathVariable I i) {
        Optional<E> e = service.findOne(i);

        if (!e.isPresent()) {
            return new RestResponseDto().fail(HttpStatus.NOT_FOUND, Optional.empty());
        }

        return new RestResponseDto().success(e.get());
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAll() {
        return new RestResponseDto().success(service.findAll());
    }

    @PostMapping("/one")
    public ResponseEntity<?> getOneBySpec(@RequestBody Object filter) {
        final Map<String, String> filters = getFilterParams(filter);
        Optional<E> e = service.findOneBySpec(filters);

        if (!e.isPresent()) {
            return new RestResponseDto().fail(HttpStatus.NOT_FOUND, Optional.empty());
        }

        return new RestResponseDto().success(e.get());
    }

    @PostMapping("/list")
    public ResponseEntity<?> getPageable(@RequestBody Object filter, @RequestParam int page,
        @RequestParam int size) {

        final Pageable pageable = PaginationUtils.pageable(page, size);
        final Map<String, String> filters = getFilterParams(filter);

        final Page<E> entities = service.findPageableBySpec(filters, pageable);

        return new RestResponseDto().success(entities);
    }

    @DeleteMapping("/{i}")
    public ResponseEntity<?> delete(@PathVariable I i) {
        service.deleteById(i);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/list/all")
    public ResponseEntity<?> getAllBySpec(@RequestBody Object filter) {

        final Map<String, String> filters = getFilterParams(filter);

        return new RestResponseDto()
            .success(service.findAllBySpec(filters));
    }

    private Map<String, String> getFilterParams(Object filter) {
        final ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.convertValue(filter, Map.class);
    }

}
