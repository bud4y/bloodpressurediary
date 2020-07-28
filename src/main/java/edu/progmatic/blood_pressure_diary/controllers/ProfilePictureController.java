package edu.progmatic.blood_pressure_diary.controllers;

import edu.progmatic.blood_pressure_diary.models.registration.Response;
import edu.progmatic.blood_pressure_diary.services.ProfilePictureStorageService;

import edu.progmatic.blood_pressure_diary.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@CrossOrigin(origins = "*",allowedHeaders = "*")
@RestController
@RequestMapping("/user")
public class ProfilePictureController {
   // private Logger logger = LoggerFactory.getLogger(ProfilePictureController.class);
    @Autowired
    private ProfilePictureStorageService profilePictureStorageService;
    @Autowired
    private UserServiceImpl userServiceImpl;

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
