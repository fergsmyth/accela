package com.ferg.accelademo.usermanagement.service;

import com.ferg.accelademo.usermanagement.entity.UserEntity;
import com.ferg.accelademo.usermanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Scanner;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void createUser(long userId, String firstName, String surname){
        UserEntity entity = new UserEntity(userId, firstName, surname);
        userRepository.save(entity);
    }

    public void updateUser(Long userId, String firstName, String surname){
        Optional<UserEntity> userOptional = userRepository.findByUserId(userId);
        if (userOptional.isPresent()){
            UserEntity user = userOptional.get();
            user.setFirstName(firstName);
            user.setSurname(surname);
            userRepository.save(user);
        }
    }

    public void deleteUser(Long userId){
        userRepository.deleteByUserId(userId);
    }

    public Long countUsers(){
        return userRepository.count();
    }


    public boolean userExists(Long id){
        return userRepository.findByUserId(id).isPresent();
    }

    public void getUsersList(String[] args) {
        if(args.length == 1){
            Iterable<UserEntity> allUsers = userRepository.findAll();
            System.out.println("List users");
            for (UserEntity user : allUsers){
                System.out.println(String.format("User ID: %d, First name: %s, Surname: %s", user.getUserId(), user.getFirstName(), user.getSurname()));
            }
        }
    }

    public void getUserCount(String[] args) {
        if(args.length == 1){
            System.out.println(String.format("Total number of users in the system: %d", countUsers()));
        } else {
            System.out.println("No additional arguments should be passed for counting users");
        }
    }

    public void deleteUser(String[] args) {
        if(args.length == 4) {
            Scanner scanner = new Scanner(args[1].trim());
            if (scanner.hasNextLong()) {
                long userId = scanner.nextLong();
                if (userExists(userId)) {
                    deleteUser(userId);
                } else {
                    System.out.println(String.format("Unable to find user matching id %d", userId));
                }
            }
        }
    }

    public void updateUser(String[] args) {
        if(args.length == 4){
            Scanner scanner = new Scanner(args[1].trim());
            if(scanner.hasNextLong()){
                long userId = scanner.nextLong();
                if(userExists(userId)){
                    String firstname = args[1];
                    String surname = args[2];
                    updateUser(userId, firstname, surname);
                } else {
                    System.out.println(String.format("Unable to find user matching id %d", userId));
                }
            } else {
                System.out.println("Please provide the user id as the second parameter to update a user.");
            }
        }
    }

    public void createUser(String[] args) {
        if(args.length == 4) {
            Scanner scanner = new Scanner(args[1].trim());
            long userId = scanner.nextLong();
            if(scanner.hasNextLong() && !userExists(userId)){
                String firstname = args[1];
                String surname = args[2];
                createUser(userId, firstname, surname);
            } else {
                System.out.println(String.format("Another user already exists with the id", userId));
            }
        } else {
            System.out.println("You must provide a user id, first name and surname to add a user");
        }
    }
}
