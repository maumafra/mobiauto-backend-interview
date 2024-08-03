package com.mobiauto.resalemanagement;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mobiauto.resalemanagement.dto.ResaleDTO;
import com.mobiauto.resalemanagement.entities.Resale;
import com.mobiauto.resalemanagement.mappers.ResaleMapper;
import jakarta.persistence.EntityNotFoundException;
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
    @Autowired
    private final ObjectMapper objectMapper;

    public ResponseEntity<?> registerResale(ResaleDTO resaleDTO) {
        try {
            Resale resale = resaleMapper.map(resaleDTO);
            resaleRepository.save(resale);
            //TODO: Fazer custom exceptions
            //TODO: Fazer a exeption de CNPJ duplicado
        } catch (IllegalArgumentException ex) {
            return ResponseEntity
                    .badRequest()
                    .body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity
                    .internalServerError()
                    .build();
        }
        return ResponseEntity
                .ok()
                .build();
    }

    public ResponseEntity<?> getResaleById(Integer resaleId) {
        try {
            Resale resale = resaleRepository.getReferenceById(resaleId);
            ResaleDTO resaleDTO = resaleMapper.map(resale);
            return ResponseEntity
                    .ok(objectMapper.writeValueAsString(resaleDTO));
        } catch (EntityNotFoundException ex) {
            return ResponseEntity
                    .notFound()
                    .build();
        } catch (Exception ex) {
            return ResponseEntity
                    .internalServerError()
                    .build();
        }
    }
}
