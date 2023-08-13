package com.rscinema.finalproject.repository;

import com.rscinema.finalproject.domain.entity.user.Gender;
import com.rscinema.finalproject.domain.entity.user.Role;
import com.rscinema.finalproject.domain.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {

    Optional<User> findByEmail(String email);

    List<User> findAllByGenderAndRole(Gender gender,Role role);
    List<User> findByEmailContainingIgnoreCaseAndGender(String email,Gender gender);
    List<User> findByEmailContainingIgnoreCaseAndGenderAndRole(String email,Gender gender,Role role);
    List<User> findByEmailContainingIgnoreCaseAndRole(String email,Role role);
    List<User> findByEmailContainingAndDeletedAndRole(String email,Boolean status,Role role);
    List<User> findAllByDeleted(Boolean status);
    List<User> findAllByRoleAndDeletedTrue(Role role);
    List<User> findAllByRoleAndDeletedFalse(Role role);
}
