package com.hotelreview.ratings.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApiResponseDto {
    private String message;
    private boolean status;
}
