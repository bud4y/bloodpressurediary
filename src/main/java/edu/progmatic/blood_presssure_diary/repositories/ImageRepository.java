package edu.progmatic.blood_presssure_diary.repositories;

import java.util.Optional;

import edu.progmatic.blood_presssure_diary.models.registration.Image;
import org.springframework.data.jpa.repository.JpaRepository;
public interface ImageRepository extends JpaRepository<Image, Long> {
    Optional<Image> findByName(String name);
}
