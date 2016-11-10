package com.simplebot.repository;

import com.simplebot.entity.Info;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by Vladislav on 11/4/2016.
 */
public interface InfoRepository extends JpaRepository<Info, String> {
    Info findTop1ByUserIdOrderByIdDesc(Long userId);
    List<Info> findTop5ByUserIdOrderByIdDesc(Long usedId);
}
