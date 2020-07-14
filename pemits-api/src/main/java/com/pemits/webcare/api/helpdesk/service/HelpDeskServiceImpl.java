package com.pemits.webcare.api.helpdesk.service;

import com.pemits.webcare.api.helpdesk.entity.HelpDesk;
import com.pemits.webcare.api.helpdesk.repository.HelpDeskRepository;
import com.pemits.webcare.api.helpdesk.repository.spec.HelpDeskSpecBuilder;
import com.pemits.webcare.core.repository.BaseRepository;
import com.pemits.webcare.core.repository.BaseSpecBuilder;
import com.pemits.webcare.core.service.BaseServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @Author Mohammad Hussain
 * created on 7/14/2020
 */
@Service
public class HelpDeskServiceImpl extends BaseServiceImpl<HelpDesk, Long> implements HelpDeskService {

    public HelpDeskServiceImpl(HelpDeskRepository helpDeskRepository) {
        super(helpDeskRepository);
    }

    @Override
    protected BaseSpecBuilder<HelpDesk> getSpec(Map<String, String> filterParams) {
        return new HelpDeskSpecBuilder(filterParams);
    }
}
