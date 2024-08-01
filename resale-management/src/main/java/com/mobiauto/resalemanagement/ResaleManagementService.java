package com.mobiauto.resalemanagement;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ResaleManagementService {

    @Autowired
    private final ResaleMapper resaleMapper;
    @Autowired
    private final ResaleRepository resaleRepository;

    public ResponseEntity<?> registerResale(ResaleRegistrationRequest resaleRegistrationRequest) {
        Resale resale = resaleMapper.map(resaleRegistrationRequest);
        resaleRepository.save(resale);
        return ResponseEntity.ok().build();
    }
}
