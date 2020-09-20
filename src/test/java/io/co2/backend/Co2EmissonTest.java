package io.co2.backend;

import io.co2.backend.exception.InputCountMismatchException;
import io.co2.backend.exception.RequiredInputParamsNotFoundException;
import io.co2.backend.model.entity.Co2EmissionRequest;
import io.co2.backend.model.entity.VechileType;
import io.co2.backend.service.Co2EmissionService;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.co2.backend.model.MediumVechilesCo2Emission.MEDIUM_DIESEL_CAR;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;


/**
 * @Author Varadharajan on 21/09/20 01:00
 * @Projectname co2-simulator
 */
public class Co2EmissonTest {


    Co2EmissionRequest co2EmissionRequest;

    Co2EmissionService co2EmissionService;

    Co2CalculatorApplication co2CalculatorApplication;

    @Before
    public void init() {

        co2EmissionRequest = Co2EmissionRequest.builder().distance(15.0).unitOfDistance("km")
                .vechileType(VechileType.builder()
                        .heavyVechilesCo2Emission(null)
                        .largeVechilesCo2Emission(null)
                        .smallVechilesCo2Emission(null)
                        .mediumVechilesCo2Emission(MEDIUM_DIESEL_CAR).build()).build();


        co2EmissionService = Co2EmissionService.getInstance();

        co2CalculatorApplication = new Co2CalculatorApplication();

    }


    @Test
    public void testValidInput() {
        Map<String,String> map = new HashMap<>();
        map.put("unit-of-distanc","km");
        map.put("distance","15");
        map.put("transportation-method","medium-diesel-car");
        final Co2EmissionRequest actual = co2EmissionService.processRequest(map);
        assertThat(actual.getDistance()).isEqualTo(co2EmissionRequest.getDistance());
        assertThat(actual.getUnitOfDistance()).isEqualTo(co2EmissionRequest.getUnitOfDistance());
    }

    @Test
    public void testOutput() {
        String[] st = new String[] {"--unit-of-distance=km --distance 15 --transportation-method=medium-diesel-car"};
        Co2CalculatorApplication.main(st);
    }

    @Test
    public void InValidInputExpcetion() {
        String[] st = new String[] {"--unit-of-distance=km --distance 15 --transportation-method"};
        assertThatExceptionOfType(InputCountMismatchException.class).isThrownBy(() -> Co2CalculatorApplication.main(st))
                .withMessageContaining("Malformed input.Request seems to be Insufficient");
    }

    @Test
    public void InValidInputExceptionWithTransPortNotFound() {
        final String[] st = new String[] {"--unit-of-distance=km  --distance 15 --transportation-method"};
        assertThatExceptionOfType(RequiredInputParamsNotFoundException.class).isThrownBy(() -> Co2CalculatorApplication.main(st))
                .withMessageContaining("transportation-method not found");
    }

}
