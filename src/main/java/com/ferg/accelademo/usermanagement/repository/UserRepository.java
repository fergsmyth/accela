package com.ferg.accelademo.usermanagement.repository;

import com.ferg.accelademo.usermanagement.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserEntity, Long> {

}
