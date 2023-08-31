package it.popso.popsogift.utility;

import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.UUID;

@UtilityClass
public class UUIDGenerator {

    public static String conversationId() {
        return UUID.randomUUID().toString(); // 36 length string
    }

    public static String operationId() {
        return RandomStringUtils.randomNumeric(20); // 20 length string
    }

}
