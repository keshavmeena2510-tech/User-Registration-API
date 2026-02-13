package com.example.UserRegistrationApi.service;

import com.example.UserRegistrationApi.entity.User;
import com.example.UserRegistrationApi.exception.UserNotFoundException;
import com.example.UserRegistrationApi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class UserServiceImpl implements UserService{


    private final UserRepository userRepository;

    public UserServiceImpl (UserRepository userRepository)
    {
        this.userRepository=userRepository;
    }

    @Override
    public User registerUser(User user) {
        Optional<User> existingUser=userRepository.findByEmail(user.getEmail());
        if(existingUser.isPresent()) {
            throw new RuntimeException("Email already exist");
        }
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Long id) {
       return userRepository.findById(id).orElseThrow(()-> new UserNotFoundException("user not found with this id:"+id));
    }

    @Override
    public User updateUser(Long id, User user) {
        User existingUser=userRepository.findById(id).orElseThrow(()-> new UserNotFoundException("user not found with this id:"+id));
        existingUser.setName(user.getName());
        existingUser.setEmail(user.getEmail());
        existingUser.setPassword(user.getPassword());
        existingUser.setPhone(user.getPhone());

        return userRepository.save(existingUser);
    }

    @Override
    public void deleteUser(Long id) {
        User existingUser=userRepository.findById(id).orElseThrow(()->new RuntimeException("user not found"));
        userRepository.delete(existingUser);

    }
}
