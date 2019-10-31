package com.ferg.accelademo.usermanagement.service;

import com.ferg.accelademo.usermanagement.dto.UserXMLDTO;
import com.ferg.accelademo.usermanagement.entity.UserEntity;
import com.ferg.accelademo.usermanagement.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.Optional;

@Service
public class UserService {

    Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    public void createUser(long userId, String firstName, String surname){
        UserEntity entity = new UserEntity(userId, firstName, surname);
        userRepository.save(entity);
    }

    public void createUserFromXML(String filePath){
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(UserXMLDTO.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            UserXMLDTO userXMLDTO = (UserXMLDTO) jaxbUnmarshaller.unmarshal(new File(filePath));
            createUser(userXMLDTO.getUserId(), userXMLDTO.getFirstName(), userXMLDTO.getSurname());
        } catch (JAXBException e) {
            logger.error("Failed to create user from xml", e);
        }
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

    @Transactional
    public void deleteUser(Long userId){
        userRepository.deleteByUserId(userId);
    }

    public Long countUsers(){
        return userRepository.count();
    }

    public boolean userExists(Long id){
        return userRepository.findByUserId(id).isPresent();
    }

    public void getUsersList() {
        Iterable<UserEntity> allUsers = userRepository.findAllByOrderByUserIdAsc();
        for (UserEntity user : allUsers){
            System.out.println(String.format("User Id: %d, First name: %s, Surname: %s", user.getUserId(), user.getFirstName(), user.getSurname()));
        }
    }

    public void getUserCount() {
            System.out.println(countUsers());
    }

}
