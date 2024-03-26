package co.istad.testmvc.dto;

public record UserDtoRequest(
        String userName,
        String email,
        String password
) {
}
