package io.co2.backend.model;

import lombok.Getter;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;

import static io.co2.backend.constants.Co2EmissionConstants.*;
import static java.util.stream.Collectors.toMap;



@Getter
public enum MediumVechilesCo2Emission implements VechileTypeValidator {

    MEDIUM_DIESEL_CAR(MEDIUM.concat(HYPHEN).concat(DESIEL_CAR),171),
    MEDIUM_PETROL_CAR(MEDIUM.concat(HYPHEN).concat(PETROL_CAR),192),
    MEDIUM_PLUGIN_HYBRID_CAR(MEDIUM.concat(HYPHEN).concat(PLUGIN_HYDRID_CAR),110),
    MEDIUM_ELECTRIC_CAR(MEDIUM.concat(HYPHEN).concat(ELECTRIC_CAR),58);

    private final String type;

    private final double emission;

    public static final Map<String,MediumVechilesCo2Emission> TYPE_MAP = Arrays.stream(values())
            .collect(toMap(MediumVechilesCo2Emission::getType, Function.identity()));

    MediumVechilesCo2Emission(final String type, final double emission) {
        this.type = type;
        this.emission=emission;
    }

    @Override
    public  boolean isExists(final String emissionType) {
        return TYPE_MAP.containsKey(emissionType);
    }
}
