package com.deeRav.controllers;

import com.deeRav.payloads.ApiResponse;
import com.deeRav.payloads.UserDto;
import com.deeRav.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;


    // POST:- create user
    @PostMapping("/")
    public ResponseEntity<UserDto>createUser(@Valid @RequestBody UserDto userDto)
    {
        UserDto addUser = this.userService.addUser(userDto);
        return new ResponseEntity<UserDto>(addUser, HttpStatus.CREATED);

    }
    // PUT:- update User
    @PutMapping("/{id}")
    public ResponseEntity<UserDto>updateUser(@Valid @RequestBody UserDto userDto , @PathVariable("id")Integer id){
        UserDto updateUser = this.userService.updateUser(userDto, id);
        return ResponseEntity.ok(updateUser);
    }

    // DELETE :- delete User
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse>deleteUser(@PathVariable("id")Integer id){
        this.userService.deleteUser(id);

        return new ResponseEntity<>(new ApiResponse("User deleted ", true),HttpStatus.OK);
    }

    // GET:  get User

    @GetMapping("/")
    public ResponseEntity<List<UserDto>>getAllUsers(){

        List<UserDto>list= this.userService.getAllUser();
        return new ResponseEntity<>(list,HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<UserDto>getUserByID(@PathVariable("id")Integer id){
        return ResponseEntity.ok(this.userService.getUserBYId(id));
    }


}
