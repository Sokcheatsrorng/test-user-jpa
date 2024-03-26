package co.istad.testmvc.dto;

import java.time.LocalDate;

public record UserDtoResponse(
        String UUId,
        String uerName,
        String email,
        Boolean status,
        LocalDate startDate
) {
}
