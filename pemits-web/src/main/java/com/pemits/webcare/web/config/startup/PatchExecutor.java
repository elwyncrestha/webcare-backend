package com.pemits.webcare.web.config.startup;

import java.io.File;
import javax.sql.DataSource;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.stereotype.Component;

import com.pemits.webcare.core.enums.PatchType;


/**
 * @author Elvin Shrestha on 6/21/2020
 */
@Component
@Slf4j
public class PatchExecutor {

    private static final String BASE_PATH = "/db/patch";
    private final DataSource dataSource;

    public PatchExecutor(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void execute(PatchType patchType) {
        log.info("Start executing script: {}", patchType.toString());
        String path = BASE_PATH + File.separator + patchType.toString();
        ClassPathResource resource = new ClassPathResource(path);
        ResourceDatabasePopulator populator = new ResourceDatabasePopulator(resource);
        populator.execute(dataSource);
        log.info("End executing script: {}", patchType.toString());
    }
}
