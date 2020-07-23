package edu.progmatic.blood_presssure_diary.controllers;

import edu.progmatic.blood_presssure_diary.models.registration.ProfilePicture;
import edu.progmatic.blood_presssure_diary.models.registration.Response;
import edu.progmatic.blood_presssure_diary.repositories.ProfilePictureRepository;
import edu.progmatic.blood_presssure_diary.services.ProfilePictureStorageService;
import edu.progmatic.blood_presssure_diary.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Optional;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;


@RestController
@RequestMapping("/user")
public class ProfilePictureController {
    private Logger logger = LoggerFactory.getLogger(ProfilePictureController.class);
    private ProfilePictureStorageService profilePictureStorageService;
    private ProfilePictureRepository profilePictureRepository;
    private UserService userService;


    @Autowired
    public ProfilePictureController(ProfilePictureStorageService profilePictureStorageService,
                                    ProfilePictureRepository profilePictureRepository, UserService userService) {
        this.profilePictureStorageService = profilePictureStorageService;
        this.profilePictureRepository = profilePictureRepository;
        this.userService = userService;
    }

    @PostMapping("/uploadFile")
    public Response uploadFile(@RequestParam("file") MultipartFile file) {
        String fileName = profilePictureStorageService.storeFile(file).getFileName();


        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile/")
                .path(fileName)
                .toUriString();

        return new Response(fileName, fileDownloadUri,
                file.getContentType(), file.getSize());
    }

    @GetMapping(path = {"/get/{imageName}"})
    public ProfilePicture getImage(@PathVariable("imageName") String imageName) throws IOException {
        final Optional<ProfilePicture> retrievedImage = profilePictureRepository.findByFileName(imageName);
        ProfilePicture img = new ProfilePicture(retrievedImage.get().getFileName(), retrievedImage.get().getFileType(),
                decompressBytes(retrievedImage.get().getData()));
        img.setId(retrievedImage.get().getId());
        return img;
    }


    public static byte[] decompressBytes(byte[] data) {
        Inflater inflater = new Inflater();
        inflater.setInput(data);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        try {
            while (!inflater.finished()) {
                int count = inflater.inflate(buffer);
                outputStream.write(buffer, 0, count);
            }
            outputStream.close();
        } catch (IOException ioe) {
        } catch (DataFormatException e) {
        }
        return outputStream.toByteArray();
    }
}
