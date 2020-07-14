package com.pemits.webcare.web.helpdesk;

import com.pemits.webcare.api.helpdesk.entity.HelpDesk;
import com.pemits.webcare.api.helpdesk.service.HelpDeskService;
import com.pemits.webcare.core.controller.BaseController;
import com.pemits.webcare.core.service.BaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.pemits.webcare.web.helpdesk.HelpDeskController.URL;

/**
 * @Author Mohammad Hussain
 * created on 7/14/2020
 */
@RestController
@RequestMapping(URL)
@Slf4j
public class HelpDeskController extends BaseController<HelpDesk, Long> {
    static final String URL = "/v1/helpdesk";

    protected HelpDeskController(HelpDeskService helpDeskService) {
        super(helpDeskService, log.getClass());
    }
}
