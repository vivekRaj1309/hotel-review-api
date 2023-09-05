package com.hotelreview.ratings.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "rating")
public class Rating extends BaseEntity{
    @Column(name = "user_id")
    private String userId;
    @Column(name = "hotel_id")
    private String hotelId;
    @Column(name = "rating", nullable = false)
    private int rating;
    @Column(name = "feedback", nullable = false, length = 500)
    private String feedback;
}
