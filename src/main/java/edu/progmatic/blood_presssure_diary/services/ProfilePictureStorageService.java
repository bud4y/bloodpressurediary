package edu.progmatic.blood_presssure_diary.services;

import edu.progmatic.blood_presssure_diary.exceptions.FileNotFoundException;
import edu.progmatic.blood_presssure_diary.exceptions.FileStorageException;
import edu.progmatic.blood_presssure_diary.models.registration.ProfilePicture;
import edu.progmatic.blood_presssure_diary.models.registration.User;
import edu.progmatic.blood_presssure_diary.repositories.ProfilePictureRepository;
import edu.progmatic.blood_presssure_diary.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

@Service
public class ProfilePictureStorageService {
    Logger logger = LoggerFactory.getLogger(ProfilePictureStorageService.class);

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

            ProfilePicture profilePicture = new ProfilePicture(fileName, file.getContentType(), compressBytes(file.getBytes()));
            profilePictureRepository.save(profilePicture);
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            Object principal = auth.getPrincipal();
            User user = (User) principal;
            user.setPictureId(profilePicture.getId());
            userRepository.save(user);

            return profilePicture;
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }

    public ProfilePicture getFile(Integer fileId) {
        return profilePictureRepository.findById(fileId)
                .orElseThrow(() -> new FileNotFoundException("File not found with id " + fileId));
    }

    public static byte[] compressBytes(byte[] data) {
        Deflater deflater = new Deflater();
        deflater.setInput(data);
        deflater.finish();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        while (!deflater.finished()) {
            int count = deflater.deflate(buffer);
            outputStream.write(buffer, 0, count);
        }
        try {
            outputStream.close();
        } catch (IOException e) {
        }
        System.out.println("Compressed Image Byte Size - " + outputStream.toByteArray().length);
        return outputStream.toByteArray();
    }


}
