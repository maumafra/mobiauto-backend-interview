package com.mobiauto.opportunitymanagement.business.mappers;

import com.mobiauto.opportunitymanagement.business.domains.OpportunityClient;
import com.mobiauto.opportunitymanagement.business.domains.OpportunityStatus;
import com.mobiauto.opportunitymanagement.business.domains.OpportunityVehicle;
import com.mobiauto.opportunitymanagement.business.dtos.ClientResponseDTO;
import com.mobiauto.opportunitymanagement.business.dtos.OpportunityRegistrationDTO;
import com.mobiauto.opportunitymanagement.business.dtos.OpportunityResponseDTO;
import com.mobiauto.opportunitymanagement.business.dtos.VehicleResponseDTO;
import com.mobiauto.opportunitymanagement.entities.Opportunity;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class OpportunityMapper {

    public Opportunity map(OpportunityRegistrationDTO opportunityRegistrationDTO){
        return Opportunity.builder()
                .status(OpportunityStatus.NEW)
                .client(OpportunityClient.builder()
                        .name(opportunityRegistrationDTO.clientName())
                        .email(opportunityRegistrationDTO.clientEmail())
                        .phone(opportunityRegistrationDTO.clientPhone())
                        .build()
                )
                .vehicle(OpportunityVehicle.builder()
                        .company(opportunityRegistrationDTO.vehicleCompany())
                        .model(opportunityRegistrationDTO.vehicleModel())
                        .year(opportunityRegistrationDTO.vehicleYear())
                        .version(opportunityRegistrationDTO.vehicleVersion())
                        .build()
                )
                .build();
    }

    public OpportunityResponseDTO map(Opportunity opportunity) {
        return OpportunityResponseDTO.builder()
                .opportunityId(opportunity.getId())
                .status(opportunity.getStatus().name())
                .client(ClientResponseDTO.builder()
                        .clientName(opportunity.getClient().getName())
                        .clientEmail(opportunity.getClient().getEmail())
                        .clientPhone(opportunity.getClient().getPhone())
                        .build()
                )
                .vehicle(VehicleResponseDTO.builder()
                        .vehicleCompany(opportunity.getVehicle().getCompany())
                        .vehicleModel(opportunity.getVehicle().getModel())
                        .vehicleYear(opportunity.getVehicle().getYear())
                        .vehicleVersion(opportunity.getVehicle().getVersion())
                        .build()
                )
                .reasonForConclusion(opportunity.getReasonForConclusion())
                .build();
    }
}
