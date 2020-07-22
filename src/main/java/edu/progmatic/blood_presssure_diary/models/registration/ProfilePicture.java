package edu.progmatic.blood_presssure_diary.models.registration;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;


@NoArgsConstructor
@Getter
@Setter
@Entity
public class ProfilePicture {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;
    private String fileName;
    private String fileType;
    @Lob
    private byte[] data;


    public ProfilePicture(String fileName, String fileType, byte[] data) {
        this.fileName = fileName;
        this.fileType = fileType;
        this.data = data;
    }
}
