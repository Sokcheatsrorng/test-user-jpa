package co.istad.testmvc.service;

import co.istad.testmvc.dto.UserDtoRequest;
import co.istad.testmvc.dto.UserDtoResponse;
import co.istad.testmvc.dto.UserEditRequest;
import co.istad.testmvc.model.User;
import co.istad.testmvc.respository.UserRespository;

import java.util.List;

public interface UserService {
    List<UserDtoResponse> getAllUsers();
    UserDtoResponse  findUserById(Integer id);
    void createNewUser(UserDtoRequest request);
    void editUserById(Integer id, UserEditRequest request);
    void deleteUserById(Integer id);

}
