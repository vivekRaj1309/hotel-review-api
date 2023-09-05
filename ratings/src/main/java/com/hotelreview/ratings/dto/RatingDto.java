package com.hotelreview.ratings.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RatingDto {
    private String id;
    @NotNull(message = "User Id cannot be empty")
    private String userId;
    @NotNull(message = "Hotel Id cannot be empty")
    private String hotelId;
    @NotNull(message = "Rating not valid")
    private int rating;
    @NotEmpty(message = "Feedback required")
    private String feedback;
}
