package com.simplebot.repository;

import com.simplebot.entity.Info;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Vladislav on 11/4/2016.
 */
public interface InfoRepository extends JpaRepository<Info, String> {
}
