package com.mobiauto.resalemanagement.utils.hateoas;

import com.mobiauto.resalemanagement.business.dtos.ResaleResponseDTO;
import com.mobiauto.resalemanagement.controllers.ResaleManagementController;
import java.util.List;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

public class ResaleResponseHypermediaAssembler {

    public static void addLinks(ResaleResponseDTO resaleResponseDTO) {
        resaleResponseDTO.add(
                linkTo(
                        methodOn(ResaleManagementController.class)
                                .getResale(resaleResponseDTO.getResaleId())
                ).withSelfRel()
        );
    }

    public static void addLinks(List<ResaleResponseDTO> responseDTOList) {
        responseDTOList.stream()
                .forEach(
                        resaleResponseDTO -> resaleResponseDTO.add(
                                linkTo(
                                        methodOn(ResaleManagementController.class)
                                                .getResale(resaleResponseDTO.getResaleId())
                                ).withSelfRel()
                        )
                );
    }
}
