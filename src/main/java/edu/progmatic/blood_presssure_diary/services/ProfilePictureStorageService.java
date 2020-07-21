package edu.progmatic.blood_presssure_diary.services;

import edu.progmatic.blood_presssure_diary.exceptions.FileNotFoundException;
import edu.progmatic.blood_presssure_diary.exceptions.FileStorageException;
import edu.progmatic.blood_presssure_diary.models.registration.ProfilePicture;
import edu.progmatic.blood_presssure_diary.models.registration.User;
import edu.progmatic.blood_presssure_diary.repositories.ProfilePictureRepository;
import edu.progmatic.blood_presssure_diary.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

@Service
public class ProfilePictureStorageService {

    @Autowired
    private ProfilePictureRepository profilePictureRepository;
    @Autowired
    private UserRepository userRepository;

    public ProfilePicture storeFile(MultipartFile file) {
        // Normalize file name
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

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

    public ProfilePicture getFile(String fileId) {
        return profilePictureRepository.findById(fileId)
                .orElseThrow(() -> new FileNotFoundException("File not found with id " + fileId));
    }
}
