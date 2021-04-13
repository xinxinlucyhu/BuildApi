package com.tts.usersapi;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long>
{
 public List<User> findByState(String state);
 
 @Override
 public List<User> findAll();
}
