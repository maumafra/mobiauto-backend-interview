package com.mobiauto.resalemanagement.controllers;

import com.mobiauto.resalemanagement.services.ResaleManagementService;
import com.mobiauto.resalemanagement.dtos.ResaleRequestDTO;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("api/v1/resale-management")
@AllArgsConstructor
public class ResaleManagementController {

    @Autowired
    private final ResaleManagementService resaleManagementService;

    @GetMapping(
            path = "/{resaleId}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> getResale(final @PathVariable("resaleId") Integer resaleId) {
        return resaleManagementService.getResaleById(resaleId);
    }

    @GetMapping(
            path = "/resales",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> getAllResales() {
        return resaleManagementService.getAllResales();
    }

    @PostMapping(
            path = "/register-resale",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> registerResale(final @RequestBody @Valid ResaleRequestDTO resaleRequestDTO) {
        return resaleManagementService.registerResale(resaleRequestDTO);
    }

    @DeleteMapping(path = "/delete-resale/{resaleId}")
    public ResponseEntity<?> deleteResale(final @PathVariable("resaleId") Integer resaleId) {
        return resaleManagementService.deleteResale(resaleId);
    }
}
