package io.co2.backend.model;

import lombok.Getter;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;

import static io.co2.backend.constants.Co2EmissionConstants.*;
import static java.util.stream.Collectors.toMap;

/**
 * @Author Varadharajan on 20/09/20 20:07
 * @Projectname co2-simulator
 */
@Getter
public enum LargeVechilesCo2Emission implements VechileTypeValidator {

    LARGE_DIESEL_CAR(LARGE.concat(HYPHEN).concat(DESIEL_CAR),209),
    LARGE_PETROL_CAR(LARGE.concat(HYPHEN).concat(PETROL_CAR),282),
    LARGE_PLUGIN_HYBRID_CAR(LARGE.concat(HYPHEN).concat(PLUGIN_HYDRID_CAR),126),
    LARGE_ELECTRIC_CAR(LARGE.concat(HYPHEN).concat(ELECTRIC_CAR),73);

    private final String type;

    private final double emission;

    public static final Map<String,LargeVechilesCo2Emission> TYPE_MAP = Arrays.stream(values())
            .collect(toMap(LargeVechilesCo2Emission::getType, Function.identity()));

    LargeVechilesCo2Emission(final String type, final double emission) {
        this.type = type;
        this.emission=emission;
    }

    @Override
    public  boolean isExists(final String emissionType) {
        return TYPE_MAP.containsKey(emissionType);
    }
}
