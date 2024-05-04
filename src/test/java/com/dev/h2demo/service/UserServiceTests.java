package com.dev.h2demo.service;

import static org.mockito.Mockito.when;

import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.dev.h2demo.core.dto.UserDto;
import com.dev.h2demo.core.dto.UserResponse;
import com.dev.h2demo.core.model.Department;
import com.dev.h2demo.core.model.User;
import com.dev.h2demo.core.repository.UserRepository;
import com.dev.h2demo.core.service.UserService;

@ExtendWith(MockitoExtension.class)
public class UserServiceTests {
    @Mock private UserRepository userRepository;
    @InjectMocks private UserService userService;

    @Test
    public void UserService_GetUser_ReturnUserDto(){
        Long userId = 1L;
        Department department = Department.builder().id(1L).name("Mock Department Test").build();
        User user = User.builder().id(userId).name("Mockito Test User").email("testUser@email.com").department(department).build();

        when(userRepository.findById(userId)).thenReturn(Optional.ofNullable(user));

        UserDto userReturn = userService.getUser(userId);

        Assertions.assertThat(userReturn).isNotNull();
    }
    @SuppressWarnings("unchecked")
    @Test 
    public void UserService_GetUsers_ReturnUserResponse(){
        Page<User> users = Mockito.mock(Page.class);
        //Page<User> users = Mockito.mock(Page.class);

        
        when(userRepository.findAll(Mockito.any(Pageable.class))).thenReturn(users);
        
        UserResponse saveUser = userService.getAllUsers(1, 10);
        
        Assertions.assertThat(saveUser).isNotNull();
    }
}
