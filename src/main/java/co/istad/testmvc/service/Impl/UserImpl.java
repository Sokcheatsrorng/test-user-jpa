package co.istad.testmvc.service.Impl;

import co.istad.testmvc.dto.UserDtoRequest;
import co.istad.testmvc.dto.UserDtoResponse;
import co.istad.testmvc.dto.UserEditRequest;
import co.istad.testmvc.model.User;
import co.istad.testmvc.respository.UserRespository;
import co.istad.testmvc.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserImpl implements UserService {

   private final UserRespository userRespository;
    @Override
    public List<UserDtoResponse> getAllUsers() {
        List<User> userList = userRespository.findAll();
        return userList.stream()
                .map(user ->
                        new UserDtoResponse(

                                user.getUUID(),
                                user.getUserName(),
                                user.getEmail(),
                                user.getStatus(),
                                user.getStartDate()
                        )).toList();
    }
    @Override
    public UserDtoResponse findUserById(Integer id) {
        User existingUser = userRespository.findById(id)
                .orElseThrow(()->{
                    throw new ResponseStatusException(
                            HttpStatus.NOT_FOUND
                            , "Sorry try next time!"
                    );
                });
        return new UserDtoResponse  (
                existingUser.getUserName(),
                existingUser.getUserName(),
                existingUser.getEmail(),
                existingUser.getStatus(),
                existingUser.getStartDate()

        );
        }

    @Override
    public void createNewUser(UserDtoRequest request) {
        User user = new User();
        user.setUUID(UUID.randomUUID().toString());
        user.setUserName(request.userName());
        user.setEmail(request.email());
        user.setPassword(request.password());
        user.setStatus(true);
        user.setStartDate(LocalDate.now());
        userRespository.save(user);
    }

    @Override
    public void editUserById(Integer id, UserEditRequest request) {
        User userExisting  = userRespository.findById(id)
                .orElseThrow(
                        ()-> new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Sorry userId Not found!!"
                        )
                );

       userExisting.setUserName(request.userName());
       userExisting.setEmail(request.email());
       userExisting.setPassword(request.password());
       userExisting.setStatus(request.status());
       userRespository.save(userExisting);
    }

    @Override
    public void deleteUserById(Integer id) {
        User existingUserId = userRespository.findById(id)
                        .orElseThrow(()->new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "ID not found!!"
                        ));
        userRespository.delete(existingUserId);
    }
}

