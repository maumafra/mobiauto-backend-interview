package com.mobiauto.opportunitymanagement.business.mappers;

import com.mobiauto.opportunitymanagement.business.domains.OpportunityClient;
import com.mobiauto.opportunitymanagement.business.domains.OpportunityStatus;
import com.mobiauto.opportunitymanagement.business.domains.OpportunityVehicle;
import com.mobiauto.opportunitymanagement.business.dtos.ClientResponse;
import com.mobiauto.opportunitymanagement.business.dtos.OpportunityRegistration;
import com.mobiauto.opportunitymanagement.business.dtos.OpportunityResponse;
import com.mobiauto.opportunitymanagement.business.dtos.VehicleResponse;
import com.mobiauto.opportunitymanagement.entities.Opportunity;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class OpportunityMapper {

    public Opportunity map(OpportunityRegistration opportunityRegistration){
        return Opportunity.builder()
                .status(OpportunityStatus.NEW)
                .client(OpportunityClient.builder()
                        .name(opportunityRegistration.clientName())
                        .email(opportunityRegistration.clientEmail())
                        .phone(opportunityRegistration.clientPhone())
                        .build()
                )
                .vehicle(OpportunityVehicle.builder()
                        .company(opportunityRegistration.vehicleCompany())
                        .model(opportunityRegistration.vehicleModel())
                        .year(opportunityRegistration.vehicleYear())
                        .version(opportunityRegistration.vehicleVersion())
                        .build()
                )
                .resaleId(opportunityRegistration.resaleId())
                .build();
    }

    public OpportunityResponse map(Opportunity opportunity) {
        return OpportunityResponse.builder()
                .opportunityId(opportunity.getId())
                .status(opportunity.getStatus().name())
                .client(ClientResponse.builder()
                        .clientName(opportunity.getClient().getName())
                        .clientEmail(opportunity.getClient().getEmail())
                        .clientPhone(opportunity.getClient().getPhone())
                        .build()
                )
                .vehicle(VehicleResponse.builder()
                        .vehicleCompany(opportunity.getVehicle().getCompany())
                        .vehicleModel(opportunity.getVehicle().getModel())
                        .vehicleYear(opportunity.getVehicle().getYear())
                        .vehicleVersion(opportunity.getVehicle().getVersion())
                        .build()
                )
                .resaleId(opportunity.getResaleId())
                .build();
    }
}
