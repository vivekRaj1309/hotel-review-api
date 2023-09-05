package com.hotelreview.hotels.repositories;

import com.hotelreview.hotels.entities.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelRepository extends JpaRepository<Hotel, String> {
}
