package io.co2.backend.model;

import lombok.Getter;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;


import static io.co2.backend.constants.Co2EmissionConstants.*;
import static java.util.stream.Collectors.toMap;

/**
 * @Author Varadharajan on 20/09/20 19:59
 * @Projectname co2-simulator
 */
@Getter
public enum SmallVechilesCo2Emission implements VechileTypeValidator {

    SMALL_DIESEL_CAR(SMALL.concat(HYPHEN).concat(DESIEL_CAR),142),
    SMALL_PETROL_CAR(SMALL.concat(HYPHEN).concat(PETROL_CAR),154),
    SMALL_PLUGIN_HYBRID_CAR(SMALL.concat(HYPHEN).concat(PLUGIN_HYDRID_CAR),73),
    SMALL_ELECTRIC_CAR(SMALL.concat(HYPHEN).concat(ELECTRIC_CAR),50);

    private final String type;

    private final double emission;

    public static final Map<String,SmallVechilesCo2Emission> TYPE_MAP = Arrays.stream(values())
            .collect(toMap(SmallVechilesCo2Emission::getType, Function.identity()));

    SmallVechilesCo2Emission(final String type, final double emission) {
        this.type = type;
        this.emission=emission;
    }

    @Override
    public  boolean isExists(final String emissionType) {
        return TYPE_MAP.containsKey(emissionType);
    }
}
