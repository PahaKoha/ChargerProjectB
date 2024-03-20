package com.backend.PowerUp.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users_images")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "type")
    private String type;
    @Lob
    @Column(name = "imageData")
    private byte[] imageData;
}
