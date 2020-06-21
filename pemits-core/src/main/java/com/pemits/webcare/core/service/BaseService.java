package com.pemits.webcare.core.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @param <T> Type
 * @param <I> ID
 * @author Elvin Shrestha on 6/21/2020
 */
public interface BaseService<T, I> {

    T save(T t);

    List<T> saveAll(List<T> entities);

    Optional<T> findOne(I i);

    List<T> findAll();

    void delete(T entity);

    void deleteById(I i);

    Page<T> findPageableBySpec(Map<String, String> filterParams, Pageable pageable);

    List<T> findAllBySpec(Map<String, String> filterParams);

    Optional<T> findOneBySpec(Map<String, String> filterParams);
}
