package com.mobiauto.resalemanagement.utils.hateoas;

import com.mobiauto.resalemanagement.business.dtos.ResaleResponse;
import com.mobiauto.resalemanagement.controllers.ResaleManagementController;
import java.util.List;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

public class ResaleResponseHypermediaAssembler {

    public static void addLinks(ResaleResponse resaleResponse) {
        resaleResponse.add(
                linkTo(
                        methodOn(ResaleManagementController.class)
                                .getResale(resaleResponse.getResaleId())
                ).withSelfRel()
        );
    }

    public static void addLinks(List<ResaleResponse> responseDTOList) {
        responseDTOList.stream()
                .forEach(
                        resaleResponse -> resaleResponse.add(
                                linkTo(
                                        methodOn(ResaleManagementController.class)
                                                .getResale(resaleResponse.getResaleId())
                                ).withSelfRel()
                        )
                );
    }
}
