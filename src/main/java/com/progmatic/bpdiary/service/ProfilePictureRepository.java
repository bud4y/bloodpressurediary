package com.progmatic.bpdiary.service;

import com.progmatic.bpdiary.model.registration.ProfilePicture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfilePictureRepository extends JpaRepository<ProfilePicture,Long> {
}
