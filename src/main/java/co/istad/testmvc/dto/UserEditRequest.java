package co.istad.testmvc.dto;

public record UserEditRequest(
        String userName,
        String email,
        String password,
        Boolean status
) {
}
