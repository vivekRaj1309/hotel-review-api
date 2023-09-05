package com.hotelreview.hotels.controller;

import com.hotelreview.hotels.dto.ApiResponseDto;
import com.hotelreview.hotels.dto.HotelDto;
import com.hotelreview.hotels.services.HotelService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotels")
public class HotelController {
    @Autowired
    private HotelService hotelService;

    @PostMapping
    @PreAuthorize("hasAuthority('Admin')")
    public ResponseEntity<HotelDto> createHotel(@Valid @RequestBody HotelDto hotelDto){
        return new ResponseEntity<>(hotelService.createHotel(hotelDto), HttpStatus.CREATED);
    }
    @PutMapping("/{hotelId}")
    @PreAuthorize("hasAuthority('Admin')")
    public ResponseEntity<HotelDto> updateHotel(@Valid @RequestBody HotelDto hotelDto,@PathVariable String hotelId){
        return new ResponseEntity<>(hotelService.updateHotel(hotelDto, hotelId), HttpStatus.CREATED);
    }
    @GetMapping("/{hotelId}")
    @PreAuthorize("hasAuthority('SCOPE_internal')")
    public ResponseEntity<HotelDto> getHotel(@PathVariable String hotelId){
        return new ResponseEntity<>(hotelService.getHotel(hotelId), HttpStatus.OK);
    }
    @GetMapping
    @PreAuthorize("hasAuthority('Admin') || hasAuthority('SCOPE_internal')")
    public ResponseEntity<List<HotelDto>> getAllHotels(){
        return new ResponseEntity<>(hotelService.getAllHotels(), HttpStatus.OK);
    }
    @DeleteMapping("/{hotelId}")
    @PreAuthorize("hasAuthority('Admin')")
    public ResponseEntity<ApiResponseDto> deleteHotel(@PathVariable String hotelId){
        hotelService.deleteHotel(hotelId);
        return new ResponseEntity<>(new ApiResponseDto("Hotel deleted successfully with id: " + hotelId, true), HttpStatus.OK);
    }
}
