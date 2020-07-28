package edu.progmatic.blood_pressure_diary.models.registration;

import com.fasterxml.jackson.annotation.JsonIgnore;
import edu.progmatic.blood_pressure_diary.models.measurement.MeasurementDetails;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class User implements UserDetails {

    private String username;
    private String firstName;
    private String lastName;
    private String password;
    private LocalDate birthDate;
    private String email;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;
    private boolean isMale;
    private Double weight;
    private Double height;
    private Double BMI;
    @OneToMany(mappedBy = "user")
    @JsonIgnore
    List<MeasurementDetails> measurements;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(
                    name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "id"))
    private Set<Role> roles;
    private Long pictureId;
    private String activation;
    private Boolean enabled;

    public User(String firstName, String lastName, String password, LocalDate birthDate, String email, boolean isMale, double weight, double height, double BMI, List<MeasurementDetails> measurements, String username) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.birthDate = birthDate;
        this.email = email;
        this.isMale = isMale;
        this.weight = weight;
        this.height = height;
        this.BMI = BMI;
        this.measurements = measurements;
        this.username = username;

    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return firstName + " " + lastName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }


    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void addRoles(String roleName) {
        if (this.roles == null || this.roles.isEmpty())
            this.roles = new HashSet<>();
        this.roles.add(new Role(roleName));
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", password='" + password + '\'' +
                ", birthDate=" + birthDate +
                ", email='" + email + '\'' +
                ", id=" + id +
                ", isMale=" + isMale +
                ", weight=" + weight +
                ", height=" + height +
                ", BMI=" + BMI +
                ", measurements=" + measurements +
                ", roles=" + roles +
                '}';
    }
}
