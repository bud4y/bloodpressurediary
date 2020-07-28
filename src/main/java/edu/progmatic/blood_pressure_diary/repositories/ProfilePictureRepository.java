package edu.progmatic.blood_pressure_diary.repositories;

import edu.progmatic.blood_pressure_diary.models.registration.ProfilePicture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfilePictureRepository extends JpaRepository<ProfilePicture,Long> {
}
