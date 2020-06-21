package com.pemits.webcare.web.config.startup;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import com.pemits.webcare.core.enums.PatchType;

/**
 * @author Elvin Shrestha on 6/21/2020
 */
@Component
public class StartUp {

    private final PatchExecutor patchExecutor;

    public StartUp(
        PatchExecutor patchExecutor
    ) {
        this.patchExecutor = patchExecutor;
    }

    @PostConstruct
    public void populate() {
        patchExecutor.execute(PatchType.INITIAL_USER);
    }
}
