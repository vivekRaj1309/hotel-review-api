package com.hotelreview.user.entities;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Hotel {
    private String name;
    private String location;
    private String contact;
    private String about;
}
