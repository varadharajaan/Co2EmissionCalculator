package io.co2.backend.model.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotNull;


/**
 * @Author Varadharajan on 20/09/20 22:10
 * @Projectname co2-simulator
 */
@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode

public class Co2EmissionRequest {

    private String unitOfDistance ;

    @NotNull
    private double distance;

    private String output ;

    private VechileType vechileType;
}
