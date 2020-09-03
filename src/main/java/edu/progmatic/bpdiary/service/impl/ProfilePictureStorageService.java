package edu.progmatic.bpdiary.service.impl;

import edu.progmatic.bpdiary.web.exceptions.FileNotFoundException;
import edu.progmatic.bpdiary.web.exceptions.FileStorageException;
import edu.progmatic.bpdiary.model.registration.ProfilePicture;
import edu.progmatic.bpdiary.model.registration.User;
import edu.progmatic.bpdiary.service.ProfilePictureRepository;
import edu.progmatic.bpdiary.service.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.Objects;

@Service
public class ProfilePictureStorageService {

    @Autowired
    private ProfilePictureRepository profilePictureRepository;

    @Qualifier("userRepository")
    @Autowired
    private UserRepository userRepository;

    public ProfilePicture storeFile(MultipartFile file) {
        // Normalize file name
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));

        try {
            // Check if the file's name contains invalid characters
            if(fileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }

            ProfilePicture profilePicture = new ProfilePicture(fileName, file.getContentType(), file.getBytes());
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            Object principal = auth.getPrincipal();
            User user = (User) principal;
            user.setPictureId(profilePicture.getId());
            userRepository.save(user);

            return profilePictureRepository.save(profilePicture);
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }

    public ProfilePicture getFile(Long fileId) {
        return profilePictureRepository.findById(fileId)
                .orElseThrow(() -> new FileNotFoundException("File not found with id " + fileId));
    }
}
