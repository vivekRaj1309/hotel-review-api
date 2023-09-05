package com.hotelreview.user.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class User extends BaseEntity{
    @Column(name = "first_name", length = 20, nullable = false)
    private String firstName;
    @Column(name = "last_name", length = 20, nullable = false)
    private String lastName;
    @Column(name = "user_name", length = 20, nullable = false)
    private String userName;
    @Column(name = "email", length = 100, nullable = false)
    private String email;
    @Column(name = "contact", length = 10, nullable = false)
    private String contact;
    @Transient
    private List<Rating> ratingList;
}
