package edu.progmatic.blood_presssure_diary.controllers;

import edu.progmatic.blood_presssure_diary.models.registration.Response;
import edu.progmatic.blood_presssure_diary.services.ProfilePictureStorageService;

import edu.progmatic.blood_presssure_diary.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;



@RestController
@RequestMapping("/user")
public class ProfilePictureController {
    private Logger logger = LoggerFactory.getLogger(ProfilePictureController.class);
    @Autowired
    private ProfilePictureStorageService profilePictureStorageService;
    @Autowired
    private UserService userService;

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

}
