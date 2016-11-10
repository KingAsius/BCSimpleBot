package com.simplebot.repository;

import com.simplebot.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Vladislav on 11/10/2016.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
