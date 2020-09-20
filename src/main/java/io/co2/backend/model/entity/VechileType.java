package io.co2.backend.model.entity;

import io.co2.backend.model.HeavyVechilesCo2Emission;
import io.co2.backend.model.LargeVechilesCo2Emission;
import io.co2.backend.model.MediumVechilesCo2Emission;
import io.co2.backend.model.SmallVechilesCo2Emission;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

/**
 * @Author Varadharajan on 20/09/20 22:46
 * @Projectname co2-simulator
 */
@Getter
@SuperBuilder
public  class VechileType {

    HeavyVechilesCo2Emission heavyVechilesCo2Emission;

    LargeVechilesCo2Emission largeVechilesCo2Emission;

    MediumVechilesCo2Emission mediumVechilesCo2Emission;

    SmallVechilesCo2Emission smallVechilesCo2Emission;
}
