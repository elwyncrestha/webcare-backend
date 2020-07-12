package com.pemits.webcare.web.appointment;

import static com.pemits.webcare.core.constant.AppConstant.NOTIFY_APPROVED_APPOINTMENT;
import static com.pemits.webcare.core.constant.AppConstant.NOTIFY_REJECTED_APPOINTMENT;
import static com.pemits.webcare.web.appointment.AppointmentController.URL;

import java.util.Optional;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pemits.webcare.api.appointment.entity.Appointment;
import com.pemits.webcare.api.appointment.service.AppointmentService;
import com.pemits.webcare.api.notification.component.NotificationComponent;
import com.pemits.webcare.api.notification.entity.Notification;
import com.pemits.webcare.api.user.entity.User;
import com.pemits.webcare.api.user.service.UserService;
import com.pemits.webcare.core.constant.EmailConstant.Template;
import com.pemits.webcare.core.controller.BaseController;
import com.pemits.webcare.core.dto.EmailDto;
import com.pemits.webcare.core.dto.RestResponseDto;
import com.pemits.webcare.core.enums.AppointmentStatus;
import com.pemits.webcare.core.enums.NotificationStatus;
import com.pemits.webcare.core.service.EmailService;

/**
 * @Author Mohammad Hussain
 * created on 7/5/2020
 */
@RestController
@RequestMapping(URL)
@Slf4j
public class AppointmentController extends BaseController<Appointment, Long> {

    static final String URL = "/v1/appointment";
    private final AppointmentService service;
    private final UserService userService;
    private final NotificationComponent notificationComponent;
    private final EmailService emailService;

    protected AppointmentController(
        AppointmentService appointmentService,
        UserService userService,
        NotificationComponent notificationComponent,
        EmailService emailService) {
        super(appointmentService, log.getClass());

        this.service = appointmentService;
        this.userService = userService;
        this.notificationComponent = notificationComponent;
        this.emailService = emailService;
    }

    @GetMapping("/confirm")
    public ResponseEntity<?> confirmAppointment(@RequestParam Long appointmentId, @RequestParam
        AppointmentStatus status) {
        Optional<Appointment> appointment = service.findOne(appointmentId);

        if (!appointment.isPresent()) {
            return new RestResponseDto().fail(HttpStatus.NOT_FOUND, Optional.of("Appointment not found"));
        }

        appointment.get().setStatus(status);
        Appointment saved = service.save(appointment.get());

        // send socket notification
        User logged = userService.getAuthenticated();
        String msg = null;
        if (saved.getStatus().equals(AppointmentStatus.APPROVED)) {
            msg = String.format(NOTIFY_APPROVED_APPOINTMENT, saved.getDoctor().getUser().getName());
        } else if (saved.getStatus().equals(AppointmentStatus.REJECTED)) {
            msg = String.format(NOTIFY_REJECTED_APPOINTMENT, saved.getDoctor().getUser().getName());
        }
        Notification notification = Notification.builder()
            .status(NotificationStatus.UNSEEN)
            .from(logged)
            .to(saved.getPatient().getUser())
            .message(msg)
            .build();
        notificationComponent.sendAndSaveMessage(notification);

        // send email
        EmailDto emailDto = EmailDto.builder()
            .template(Template.APPOINTMENT_CONFIRMATION)
            .to(saved.getPatient().getUser().getEmail())
            .toName(saved.getPatient().getUser().getName())
            .message(msg)
            .build();
        emailService.send(emailDto);

        return new RestResponseDto().success(saved);
    }
}
