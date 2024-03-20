package com.backend.PowerUp.dto;

import com.backend.PowerUp.entities.UserImage;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class MainUserData {
    private String username;
    private String email;
    private String firstname;
    private String lastname;
    private byte[] image;


    public MainUserData() {
    }

    public MainUserData(String username, String email, String firstname, String lastname) {
        this.username = username;
        this.email = email;
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public MainUserData(String username, String email, String firstname, String lastname, byte[] image) {
        this.username = username;
        this.email = email;
        this.firstname = firstname;
        this.lastname = lastname;
        this.image = image;
    }
}
