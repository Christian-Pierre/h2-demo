package com.dev.h2demo.core.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.dev.h2demo.core.dto.UserDto;
import com.dev.h2demo.core.dto.UserResponse;
import com.dev.h2demo.core.exceptions.UserNotFoundException;
import com.dev.h2demo.core.model.User;
import com.dev.h2demo.core.repository.UserRepository;

@Service
public class UserService {
    @Autowired private UserRepository userRepository;

    public UserDto getUser(Long id){
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User could not be found"));
        return mapToDto(user);
    }

    public UserResponse getAllUsers(int pageNo, int pageSize){
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<User> users = userRepository.findAll(pageable);
        List<User> listOfUser = users.getContent();
        List<UserDto> content = listOfUser.stream().map(p -> mapToDto(p)).collect(Collectors.toList());

        UserResponse userResponse = new UserResponse();
        userResponse.setContent(content);
        userResponse.setPageNo(users.getNumber());
        userResponse.setPageSize(users.getSize());
        userResponse.setTotalElements(users.getTotalElements());
        userResponse.setTotalPages(users.getTotalPages());
        userResponse.setLast(users.isLast());

        return userResponse;
    }

    private UserDto mapToDto(User user){
        UserDto userDto = new UserDto();
        userDto.setName(user.getName());
        userDto.setEmail(user.getEmail());
        userDto.setDepartamentName(user.getDepartment().getName());
        return userDto;
    }

}
