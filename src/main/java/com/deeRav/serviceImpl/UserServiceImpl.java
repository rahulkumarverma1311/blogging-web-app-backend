package com.deeRav.serviceImpl;

import com.deeRav.entity.User;
import com.deeRav.payloads.UserDto;
import com.deeRav.repository.UserRepo;
import com.deeRav.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.deeRav.exception.ResourceNotFound;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ModelMapper modelMapper;
    @Override
    public UserDto addUser(UserDto userDto) {

        User user = this.dotToUser(userDto);
        User user1 = this.userRepo.save(user);
        UserDto savedUser = this.userToDto(user1);
        return savedUser;
    }

    @Override
    public UserDto updateUser(UserDto userDto, Integer userId) {
        User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFound("User","userid",userId));
        user.setName(userDto.getName());
        user.setAbout(userDto.getAbout());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        User updatedUser = this.userRepo.save(user);
        UserDto userDto1 = this.userToDto(updatedUser);
        return userDto1;
    }

    @Override
    public UserDto getUserBYId(Integer userId) {

        User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFound("User","userid",userId));

        return this.userToDto(user);
    }

    @Override
    public List<UserDto> getAllUser() {

        List<User> users = this.userRepo.findAll();
        List<UserDto>userDtos= users.stream().map(user -> this.userToDto(user)).collect(Collectors.toList());
        return userDtos;
    }

    @Override
    public void deleteUser(Integer userId) {
        User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFound("User","userid",userId));
        this.userRepo.delete(user);
    }

    public User dotToUser(UserDto userDto){
        User user = this.modelMapper.map(userDto,User.class);
        return user;
    }

    public UserDto userToDto(User user){
        UserDto userDto = this.modelMapper.map(user,UserDto.class);
        return userDto;
    }
}
