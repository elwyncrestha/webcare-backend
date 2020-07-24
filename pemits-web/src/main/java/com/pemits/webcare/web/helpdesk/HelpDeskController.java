package com.pemits.webcare.web.helpdesk;

import com.pemits.webcare.api.helpdesk.entity.HelpDesk;
import com.pemits.webcare.api.helpdesk.service.HelpDeskService;
import com.pemits.webcare.core.constant.EmailConstant;
import com.pemits.webcare.core.controller.BaseController;
import com.pemits.webcare.core.dto.EmailDto;
import com.pemits.webcare.core.dto.RestResponseDto;
import com.pemits.webcare.core.service.EmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

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

    private final EmailService emailService;
    private final HelpDeskService service;

    protected HelpDeskController(
            HelpDeskService helpDeskService,
            EmailService emailService) {
        super(helpDeskService, log.getClass());
        this.emailService = emailService;
        this.service = helpDeskService;
    }

    @PostMapping("/reply")
    public ResponseEntity<?> replyQuery(@RequestBody HelpDesk helpDesk) {
        HelpDesk getHelpDesk = service.save(helpDesk);

        if (null == getHelpDesk ) {
            return new RestResponseDto().fail(HttpStatus.NOT_FOUND, Optional.of("Help Desk Not Found"));
        }

        // send email
        EmailDto emailDto = EmailDto.builder()
                .template(EmailConstant.Template.HELP_DESK_QUERY_REPLY)
                .to(getHelpDesk.getEmail())
                .toName(getHelpDesk.getName())
                .query(getHelpDesk.getQuery())
                .message(getHelpDesk.getReply())
                .build();
        emailService.send(emailDto);

        return new RestResponseDto().success(getHelpDesk);
    }
}
