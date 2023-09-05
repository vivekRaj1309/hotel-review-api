package com.hotelreview.hotels.services;

import com.hotelreview.hotels.dto.HotelDto;

import java.util.List;

public interface HotelService {
    HotelDto createHotel(HotelDto hotelDto);
    HotelDto updateHotel(HotelDto hotelDto, String hotelId);
    HotelDto getHotel(String hotelId);
    List<HotelDto> getAllHotels();
    void deleteHotel(String hotelId);
}
