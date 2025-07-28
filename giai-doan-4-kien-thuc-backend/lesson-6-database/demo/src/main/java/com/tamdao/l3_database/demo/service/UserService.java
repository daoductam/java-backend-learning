package com.tamdao.l3_database.demo.service;


import com.tamdao.l3_database.demo.dto.UserDto;
import com.tamdao.l3_database.demo.entity.User;
import com.tamdao.l3_database.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User createUser(UserDto userDto) {
        User user = new User();
        user.setName(userDto.getName());
        user.setDob(userDto.getDob());
        user.setPhone(userDto.getPhone());
        user.setEmail(userDto.getEmail());
        return userRepository.save(user);
    }
}
