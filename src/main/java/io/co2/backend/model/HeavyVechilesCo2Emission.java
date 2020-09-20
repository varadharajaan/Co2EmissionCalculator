package io.co2.backend.model;

import lombok.Getter;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;

import static io.co2.backend.constants.Co2EmissionConstants.HEAVY_BUS;
import static io.co2.backend.constants.Co2EmissionConstants.HEAVY_TRAIN;
import static java.util.stream.Collectors.toMap;

/**
 * @Author Varadharajan on 20/09/20 20:08
 * @Projectname co2-simulator
 */

@Getter
public enum HeavyVechilesCo2Emission implements VechileTypeValidator  {

    BUS(HEAVY_BUS,27),
    TRAIN(HEAVY_TRAIN,6);

    private final String type;

    private final double emission;

    public static final Map<String,HeavyVechilesCo2Emission> TYPE_MAP = Arrays.stream(values())
            .collect(toMap(HeavyVechilesCo2Emission::getType, Function.identity()));

    HeavyVechilesCo2Emission(final String type, final double emission) {
        this.type = type;
        this.emission=emission;
    }

    @Override
    public  boolean isExists(final String emissionType) {
        return TYPE_MAP.containsKey(emissionType);
    }
}
