package it.popso.popsogift.utility;


import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

@RequestScope
@Component
@Data
public class HeaderSupport {
    private final String conversationId = UUIDGenerator.conversationId();
    private String sessionId;
}
