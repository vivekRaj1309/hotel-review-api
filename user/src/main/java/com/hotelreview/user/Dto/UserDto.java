package com.hotelreview.user.Dto;

import com.hotelreview.user.entities.Rating;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {
    private String id;
    @NotEmpty(message = "First name should be 4 to 10 characters long")
    @Size(min = 4, max = 10)
    private String firstName;
    @NotEmpty(message = "Last name should be 4 to 10 characters long")
    @Size(min = 4, max = 10)
    private String lastName;
    @NotEmpty(message = "User name should be 4 to 10 characters long")
    @Size(min = 4, max = 10)
    private String userName;
    @Email
    @NotEmpty(message = "Email not valid")
    private String email;
    @NotEmpty(message = "Contact not valid")
    @Size(min = 10, max = 10)
    private String contact;
    private List<Rating> ratingList;

    public UserDto(String dummy, String dummy1, String dummy2, String mail, String dummy12345) {
    }
}
