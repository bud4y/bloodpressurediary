package edu.progmatic.blood_presssure_diary.repositories;

import edu.progmatic.blood_presssure_diary.models.registration.ProfilePicture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfilePictureRepository extends JpaRepository<ProfilePicture,String> {
}
