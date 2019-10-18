package com.ferg.accelademo.usermanagement.repository;

import com.ferg.accelademo.usermanagement.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<UserEntity, Long> {

    Optional<UserEntity> findByUserId(long userId);

    void deleteByUserId(long userId);

}
