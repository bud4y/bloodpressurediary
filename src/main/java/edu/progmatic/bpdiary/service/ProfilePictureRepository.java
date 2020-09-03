package edu.progmatic.bpdiary.service;

import edu.progmatic.bpdiary.model.registration.ProfilePicture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfilePictureRepository extends JpaRepository<ProfilePicture,Long> {
}
