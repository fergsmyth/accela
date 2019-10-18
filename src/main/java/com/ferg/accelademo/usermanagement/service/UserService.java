package com.ferg.accelademo.usermanagement.service;

import com.ferg.accelademo.usermanagement.entity.UserEntity;
import com.ferg.accelademo.usermanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void createUser(String firstName, String surname){
        UserEntity entity = new UserEntity(firstName, surname);
        userRepository.save(entity);
    }

    public void updateUser(Long userId, String firstName, String surname){
        Optional<UserEntity> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()){
            UserEntity user = userOptional.get();
            user.setFirstName(firstName);
            user.setSurname(surname);
            userRepository.save(user);
        }
    }

    public void deleteUser(Long userId){
        userRepository.deleteById(userId);
    }

    public Long countUsers(){
        return userRepository.count();
    }

    public Iterable<UserEntity> getAllUsers(){
        return userRepository.findAll();
    }

}
