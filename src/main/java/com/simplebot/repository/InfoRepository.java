package com.simplebot.repository;

import com.simplebot.entity.Info;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by Vladislav on 11/4/2016.
 */
public interface InfoRepository extends JpaRepository<Info, String> {

    @Query("SELECT * FROM public.info WHERE userId=:id AND id=(SELECT max(id) FROM public.info)")
    Info getLastSavedUserInfo(long id);
}
