package com.hotelreview.hotels.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Hotel extends BaseEntity{
    @Column(name = "name", length = 20, nullable = false)
    private String name;
    @Column(name = "location", length = 100, nullable = false)
    private String location;
    @Column(name = "contact", length = 10, nullable = false)
    private String contact;
    @Column(name = "about", length = 500, nullable = false)
    private String about;
}
