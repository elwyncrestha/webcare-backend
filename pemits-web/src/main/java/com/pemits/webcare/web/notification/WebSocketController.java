package com.pemits.webcare.web.notification;

import java.util.Optional;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.pemits.webcare.api.notification.component.NotificationComponent;
import com.pemits.webcare.api.notification.entity.Notification;
import com.pemits.webcare.core.dto.RestResponseDto;

/**
 * @author Elvin Shrestha on 7/11/2020
 */
@RestController
public class WebSocketController {

    private final NotificationComponent notificationComponent;

    public WebSocketController(
        NotificationComponent notificationComponent) {
        this.notificationComponent = notificationComponent;
    }

    @MessageMapping("/send")
    public ResponseEntity<?> send(@Valid @RequestBody Notification notification) {
        if (notification.getFrom() == null || notification.getTo() == null) {
            return new RestResponseDto()
                .fail(HttpStatus.BAD_REQUEST, Optional.of("Source or target user is undefined"));
        }

        Notification saved = notificationComponent.sendAndSaveMessage(notification);

        return new RestResponseDto().success(saved);
    }
}
