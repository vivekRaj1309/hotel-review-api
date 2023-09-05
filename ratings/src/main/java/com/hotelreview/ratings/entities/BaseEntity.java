package com.hotelreview.ratings.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public class BaseEntity {
    @Id
    @Column(name = "id")
    private String id;
}
