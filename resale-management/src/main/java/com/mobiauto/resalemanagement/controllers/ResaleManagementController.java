package com.mobiauto.resalemanagement.controllers;

import com.mobiauto.resalemanagement.ResaleManagementService;
import com.mobiauto.resalemanagement.dto.ResaleDTO;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/resale-management")
@AllArgsConstructor
public class ResaleManagementController {

    @Autowired
    private final ResaleManagementService resaleManagementService;

    @PostMapping
    public ResponseEntity<?> registerResale(final @RequestBody ResaleDTO resaleDTO) {
        return resaleManagementService.registerResale(resaleDTO);
    }

    @GetMapping(path = "/{resaleId}")
    public ResponseEntity<?> getResale(final @PathVariable("resaleId") Integer resaleId) {
        return resaleManagementService.getResaleById(resaleId);
    }
}
