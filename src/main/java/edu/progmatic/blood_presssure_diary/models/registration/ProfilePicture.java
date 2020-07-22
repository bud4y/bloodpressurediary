package edu.progmatic.blood_presssure_diary.models.registration;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Arrays;


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

    @Override
    public String toString() {
        return "ProfilePicture{" +
                "id=" + id +
                ", fileName='" + fileName + '\'' +
                ", fileType='" + fileType + '\'' +
                '}';
    }
}
