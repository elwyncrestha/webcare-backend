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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/reply")
    public ResponseEntity<?> replyQuery(@RequestParam Long helpDeskQueryId) {
        Optional<HelpDesk> helpDesk = service.findOne(helpDeskQueryId);

        if (!helpDesk.isPresent()) {
            return new RestResponseDto().fail(HttpStatus.NOT_FOUND, Optional.of("Help Desk Query Not Found"));
        }

        HelpDesk saved = service.save(helpDesk.get());

        // send email
        EmailDto emailDto = EmailDto.builder()
                .template(EmailConstant.Template.HELP_DESK_QUERY_REPLY)
                .to(saved.getEmail())
                .toName(saved.getName())
                .query(saved.getQuery())
                .message(saved.getReply())
                .build();
        emailService.send(emailDto);

        return new RestResponseDto().success(saved);
    }
}
