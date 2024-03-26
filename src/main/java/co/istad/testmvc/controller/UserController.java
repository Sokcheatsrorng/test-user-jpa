package co.istad.testmvc.controller;

import co.istad.testmvc.dto.UserDtoRequest;
import co.istad.testmvc.dto.UserDtoResponse;
import co.istad.testmvc.dto.UserEditRequest;
import co.istad.testmvc.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserService userService;
    @GetMapping
    public Map<String, List<UserDtoResponse>> getAllUser(){
        return Map.of("Users",
                userService.getAllUsers());
    }
    @GetMapping("/{id}")
    public UserDtoResponse getUserById (@PathVariable Integer id){
        return userService.findUserById(id);
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createNewUser(@RequestBody UserDtoRequest request){
      userService.createNewUser(request);
    }
    @PutMapping("/{id}")
    public void updateUser(@PathVariable Integer id, @RequestBody UserEditRequest request){
       userService.editUserById(id,request);
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable Integer id){
        userService.deleteUserById(id);
    }
 }
