package com.mobiauto.resalemanagement.business.services;

import com.mobiauto.resalemanagement.business.dtos.ResaleRequest;
import com.mobiauto.resalemanagement.business.dtos.ResaleResponse;
import com.mobiauto.resalemanagement.entities.Resale;
import com.mobiauto.resalemanagement.repositories.ResaleRepository;
import com.mobiauto.resalemanagement.business.mappers.ResaleMapper;
import com.mobiauto.resalemanagement.utils.Constants;
import com.mobiauto.resalemanagement.utils.exceptions.ResaleNotFoundException;
import com.mobiauto.resalemanagement.utils.hateoas.ResaleResponseHypermediaAssembler;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@AllArgsConstructor
public class ResaleManagementService {

    @Autowired
    private final ResaleMapper resaleMapper;
    @Autowired
    private final ResaleRepository resaleRepository;

    public ResponseEntity<?> registerResale(ResaleRequest resaleRequest) {
        try {
            Resale resale = resaleMapper.map(resaleRequest);
            resaleRepository.saveAndFlush(resale);
            ResaleResponse resaleResponse = resaleMapper.map(resale);
            ResaleResponseHypermediaAssembler.addLinks(resaleResponse);
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(resaleResponse);
        } catch (IllegalArgumentException ex) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(ex.getMessage());
        } catch (DataIntegrityViolationException ex) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(Constants.UNIQUE_CNPJ_ERROR);
        } catch (Exception ex) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .build();
        }
    }

    public ResponseEntity<?> getResaleById(Integer resaleId) {
        try {
            Resale resale = resaleRepository.findById(resaleId)
                    .orElseThrow(() -> new ResaleNotFoundException(resaleId));
            ResaleResponse resaleDTO = resaleMapper.map(resale);
            ResaleResponseHypermediaAssembler.addLinks(resaleDTO);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(resaleDTO);
        } catch(ResaleNotFoundException ex) {
          return ResponseEntity
                  .status(HttpStatus.NOT_FOUND)
                  .body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .build();
        }
    }

    public ResponseEntity<?> getAllResales() {
        try {
            List<ResaleResponse> allResales = resaleRepository.findAll()
                    .stream()
                    .map(resaleMapper::map)
                    .toList();
            ResaleResponseHypermediaAssembler.addLinks(allResales);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(allResales);
        } catch (Exception ex) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .build();
        }
    }

    public ResponseEntity<?> deleteResale(Integer resaleId) {
        try {
            Resale resale = resaleRepository.findById(resaleId)
                    .orElseThrow(() -> new ResaleNotFoundException(resaleId));
            resaleRepository.delete(resale);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(Constants.RESALE_DELETED_SUCCESSFULLY);
        } catch(ResaleNotFoundException ex) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .build();
        }
    }
}
