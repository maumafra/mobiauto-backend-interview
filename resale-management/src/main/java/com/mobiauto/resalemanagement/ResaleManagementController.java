package com.mobiauto.resalemanagement;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/resale-management")
@AllArgsConstructor
public class ResaleManagementController {

    @Autowired
    private final ResaleManagementService resaleManagementService;

    @PostMapping
    public ResponseEntity<?> registerResale(final @RequestBody ResaleRegistrationRequest resaleRegistrationRequest) {
        return resaleManagementService.registerResale(resaleRegistrationRequest);
    }
}
