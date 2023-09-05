package com.hotelreview.hotels.services;

import com.hotelreview.hotels.dto.HotelDto;
import com.hotelreview.hotels.entities.Hotel;
import com.hotelreview.hotels.exceptions.ResourceNotFoundException;
import com.hotelreview.hotels.repositories.HotelRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class HotelServiceImplementation implements HotelService{
    @Autowired
    private HotelRepository hotelRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public HotelDto createHotel(HotelDto hotelDto) {
        String hotelId = UUID.randomUUID().toString();
        hotelDto.setId(hotelId);
        Hotel hotel = modelMapper.map(hotelDto, Hotel.class);
        return modelMapper.map(hotelRepository.save(hotel), HotelDto.class);
    }

    @Override
    public HotelDto updateHotel(HotelDto hotelDto, String hotelId) {
        Hotel hotel = modelMapper.map(getHotel(hotelId), Hotel.class);
        hotel.setName(hotelDto.getName());
        hotel.setContact(hotelDto.getContact());
        hotel.setLocation(hotelDto.getLocation());
        hotel.setAbout(hotelDto.getAbout());
        return modelMapper.map(hotel, HotelDto.class);
    }

    @Override
    public HotelDto getHotel(String hotelId) {
        Hotel hotel = hotelRepository
                .findById(hotelId).orElseThrow(()-> new ResourceNotFoundException("Hotel", "id", hotelId));
        return modelMapper.map(hotel, HotelDto.class);
    }

    @Override
    public List<HotelDto> getAllHotels() {
        List<Hotel> hotelList = hotelRepository.findAll();
        return hotelList.stream().map(hotel -> modelMapper.map(hotel, HotelDto.class)).collect(Collectors.toList());
    }

    @Override
    public void deleteHotel(String hotelId) {
        Hotel hotel = modelMapper.map(getHotel(hotelId), Hotel.class);
        hotelRepository.delete(hotel);
    }
}
