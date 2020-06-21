package com.pemits.webcare.core.utils;

import lombok.experimental.UtilityClass;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

/**
 * @author Elvin Shrestha on 6/21/2020
 */
@UtilityClass
public class PaginationUtils {

    public Pageable pageable(int page, int size) {
        return PageRequest.of(page - 1, size);
    }
}
