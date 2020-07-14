package edu.progmatic.blood_presssure_diary.models;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Entity
public class User implements UserDetails {

    private String firstName;
    private String lastName;
    private String password;
    private LocalDate birthDate;
    private String email;
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Id
    private Long id;
    private boolean isMale;
    private double weight;
    private double height;
    private double BMI;
    @OneToMany
    List<MeasurementDetails> measures;
    @OneToOne
    Role role;

    public User() {
    }

    public User(String firstName, String lastName, String password, LocalDate birthDate, String email, boolean isMale, double weight, double height, double BMI, List<MeasurementDetails> measures) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.birthDate = birthDate;
        this.email = email;
        this.isMale = isMale;
        this.weight = weight;
        this.height = height;
        this.BMI = BMI;
        this.measures = measures;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return firstName +" "+lastName;
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isMale() {
        return isMale;
    }

    public void setMale(boolean male) {
        isMale = male;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getBMI() {
        return BMI;
    }

    public void setBMI(double BMI) {
        this.BMI = BMI;
    }

    public List<MeasurementDetails> getMeasures() {
        return measures;
    }

    public void setMeasures(List<MeasurementDetails> measures) {
        this.measures = measures;
    }
}