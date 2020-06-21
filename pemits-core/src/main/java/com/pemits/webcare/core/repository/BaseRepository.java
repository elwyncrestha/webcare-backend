package com.pemits.webcare.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * @param <T> Type
 * @param <I> ID
 * @author Elvin Shrestha on 6/21/2020
 */
@NoRepositoryBean
public interface BaseRepository<T, I> extends JpaRepository<T, I>, JpaSpecificationExecutor<T> {

}
