package edu.progmatic.bpdiary.web.controller;

import edu.progmatic.bpdiary.model.registration.Response;
import edu.progmatic.bpdiary.service.impl.ProfilePictureStorageService;

import edu.progmatic.bpdiary.service.impl.UserServiceImpl;
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
