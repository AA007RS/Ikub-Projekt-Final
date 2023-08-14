package com.rscinema.finalproject.repository;

import com.rscinema.finalproject.domain.entity.user.Gender;
import com.rscinema.finalproject.domain.entity.user.Role;
import com.rscinema.finalproject.domain.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer>, JpaSpecificationExecutor<User> {

    @Query("SELECT u from User u WHERE " +
            "(u.email ILIKE concat(:email,'%') OR :email is null) AND " +
            "(u.deleted = :deleted OR :deleted is null) AND " +
            "(u.gender = :gender OR :gender is null) AND " +
            "(u.role = :role or :role is null) ORDER BY u.id asc")
    List<User> searchUsers(@Param("email")String email,@Param("deleted")Boolean deleted,@Param("gender")Gender gender,@Param("role")Role role);
    Optional<User> findByEmail(String email);
    Optional<User> findByIdAndDeletedFalseAndRole(Integer id,Role role);
    List<User> findAllByRoleAndDeletedTrue(Role role);
    List<User> findAllByRoleAndDeletedFalse(Role role);

}
