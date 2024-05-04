package com.dev.h2demo.core.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.h2demo.core.dto.UserDto;
import com.dev.h2demo.core.dto.UserResponse;
import com.dev.h2demo.core.service.UserService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping(value = "/api/")
public class UserController {
    @Autowired private UserService userService;

    @GetMapping("user")
    public ResponseEntity<UserResponse> getUsers(
        @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
        @RequestParam(value = "pageSize", defaultValue = "0", required = false) int pageSize
        ) {
        return new ResponseEntity<>(userService.getAllUsers(pageNo, pageSize), HttpStatus.OK);
    }

    @GetMapping("user/{id}")
    public ResponseEntity<UserDto> userDetail(@PathVariable long id) {
        return new ResponseEntity<UserDto>(userService.getUser(id), HttpStatus.OK);
    }
    

    
}
