package com.backend.PowerUp.repositories;

import com.backend.PowerUp.entities.UserImage;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserImageRepository extends CrudRepository<UserImage, Integer> {
    Optional<UserImage> findByName(String name);
}
