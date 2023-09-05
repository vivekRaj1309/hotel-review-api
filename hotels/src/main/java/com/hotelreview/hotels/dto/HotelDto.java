package com.hotelreview.hotels.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HotelDto {
    private String id;
    @NotEmpty(message = "Name should be at least 3 characters long")
    @Size(min = 3, max = 20)
    private String name;
    @NotEmpty(message = "Location not valid")
    @Size(max = 100)
    private String location;
    @NotEmpty(message = "Contact not valid")
    @Size(min = 10, max = 10)
    private String contact;
    @NotEmpty(message = "Hotel Description should be at least 20 characters long")
    @Size(min = 20, max = 500)
    private String about;
}
