package com.vanko.rentyservice.web.controllers;

import com.vanko.rentyservice.business.interfaces.ApartmentService;
import com.vanko.rentyservice.business.implementations.exceptions.InvalidApartmentException;
import com.vanko.rentyservice.business.implementations.exceptions.LandlordNotFoundException;
import com.vanko.rentyservice.viewmodels.ApartmentViewModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/api")
public class ApartmentController {
    private final ApartmentService aprtmentService;

    public ApartmentController(ApartmentService landLordService) {
        this.aprtmentService = landLordService;
    }

    @GetMapping("/apartments/{id}")
    public ResponseEntity<Object> getApartment(@PathVariable long id) {
        var apartment = this.aprtmentService.getApartmentView(id);
        if (apartment != null) {
            return new ResponseEntity<>(apartment, HttpStatus.OK);
        } else {
            String errorMessage = "Apartment with ID: " + id + " not found!";
            return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/landlords/{landlordId}/apartments")
    public ResponseEntity<Void> addApartment(@PathVariable long landlordId,
                                             @Valid @RequestBody ApartmentViewModel apartment,
                                             UriComponentsBuilder uriComponentsBuilder) {
        try {
            long id = this.aprtmentService.addApartment(apartment, landlordId);

            UriComponents uriComponents =
                    uriComponentsBuilder.path("/api/apartments/{id}").buildAndExpand(id);

            return ResponseEntity.created(uriComponents.toUri()).build();
        } catch (LandlordNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (InvalidApartmentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
