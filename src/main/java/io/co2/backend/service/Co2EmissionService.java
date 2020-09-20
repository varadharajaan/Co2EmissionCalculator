package io.co2.backend.service;

import io.co2.backend.exception.RequiredInputParamsNotFoundException;
import io.co2.backend.model.HeavyVechilesCo2Emission;
import io.co2.backend.model.LargeVechilesCo2Emission;
import io.co2.backend.model.MediumVechilesCo2Emission;
import io.co2.backend.model.SmallVechilesCo2Emission;
import io.co2.backend.model.entity.Co2EmissionRequest;
import io.co2.backend.model.entity.VechileType;
import io.co2.backend.utils.Co2EmissionsUtils;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;
import java.util.Optional;

import static io.co2.backend.constants.Co2EmissionConstants.*;
import static java.lang.Double.parseDouble;
import static java.lang.String.format;
import static java.lang.String.valueOf;

/**
 * @Author Varadharajan on 20/09/20 22:06
 * @Projectname co2-simulator
 */
public class Co2EmissionService {

    private static final String ERROR_MESSAGE = "%s vechile type is not valid";
    private static final String CO2_EMISSION_OUTPUT = "Your trip caused %s of CO2-equivalent.";

    private static  Co2EmissionService co2EmissionService ;
    private Co2EmissionService () {

    }

    public static Co2EmissionService getInstance() {
        if(null == co2EmissionService) {
            synchronized (Co2EmissionService.class) {
                if( null == co2EmissionService) {
                    co2EmissionService = new Co2EmissionService();
                }
            }
        }
        return  co2EmissionService;
    }

    public Co2EmissionRequest processRequest(final Map<String,String> map) {
        String transportation = map.getOrDefault(TRANSPORTATION_METHOD,null);
        String distance = map.getOrDefault(DISTANCE,null);
        if(null == transportation || null == distance) {
            throw new RequiredInputParamsNotFoundException("transportation vehicle type or distance not found");
        }
        final Co2EmissionRequest co2EmissionRequest = requestForwarderAndMapper(transportation,distance);
        setDefaultValuesIfFound(map,co2EmissionRequest);
        return  co2EmissionRequest;
    }

    private void setDefaultValuesIfFound(final Map<String, String> map, final Co2EmissionRequest request) {
        final String unitOfDistance = map.getOrDefault(UNIT_OF_DISTANCE, DEFAULT_UNIT_OF_DISTANCE);
        request.setUnitOfDistance(unitOfDistance);
        if(unitOfDistance.equals("m")) {
            final double distance = request.getDistance();
            request.setDistance(distance/1000);
        }
        final String output = map.getOrDefault(OUTPUT,DEFAULT_OUTPUT);
        request.setOutput(output);
    }

    private Co2EmissionRequest requestForwarderAndMapper(final String transportation, final String distance) {
        if (transportation.contains(SMALL)  ) {
            final SmallVechilesCo2Emission smallVechilesCo2Emission = SmallVechilesCo2Emission.TYPE_MAP.get(transportation);
            if(null==smallVechilesCo2Emission) {
                throw new RequiredInputParamsNotFoundException(format(ERROR_MESSAGE,SMALL));
            }
            return  Co2EmissionRequest.builder()
                    .distance(parseDouble(distance))
                    .vechileType(VechileType.builder().smallVechilesCo2Emission(smallVechilesCo2Emission).build())
                    .build();
        }
        else if(transportation.contains(MEDIUM)) {
            final MediumVechilesCo2Emission mediumVechilesCo2Emission = MediumVechilesCo2Emission.TYPE_MAP.get(transportation);
            if(null==mediumVechilesCo2Emission) {
                throw new RequiredInputParamsNotFoundException(format(ERROR_MESSAGE,MEDIUM));
            }
            return Co2EmissionRequest.builder()
                    .distance(parseDouble(distance))
                    .vechileType(VechileType.builder().mediumVechilesCo2Emission(mediumVechilesCo2Emission).build())
                    .build();
        }

        else if(transportation.contains(LARGE)) {
            final LargeVechilesCo2Emission largeVechilesCo2Emission = LargeVechilesCo2Emission.TYPE_MAP.get(transportation);
            if(null==largeVechilesCo2Emission) {
                throw new RequiredInputParamsNotFoundException(format(ERROR_MESSAGE,LARGE));
            }
            return Co2EmissionRequest.builder()
                    .distance(parseDouble(distance))
                    .vechileType(VechileType.builder().largeVechilesCo2Emission(largeVechilesCo2Emission).build())
                    .build();
        }

        else {
            final HeavyVechilesCo2Emission heavyVechilesCo2Emission = HeavyVechilesCo2Emission.TYPE_MAP.get(transportation);
            if(null==heavyVechilesCo2Emission) {
                throw new RequiredInputParamsNotFoundException(format(ERROR_MESSAGE,"heavy"));
            }
            return Co2EmissionRequest.builder()
                    .distance(parseDouble(distance))
                    .vechileType(VechileType.builder().heavyVechilesCo2Emission(heavyVechilesCo2Emission).build())
                    .build();
        }
    }

    public String ouputConverter(@NotNull final Co2EmissionRequest co2EmissionRequest, final boolean outputFlagFound) {
        final Optional<VechileType> vechileType = Optional.of(co2EmissionRequest)
                .map(Co2EmissionRequest::getVechileType);

        if(vechileType.map(VechileType::getHeavyVechilesCo2Emission).isPresent()) {
            final double emission = vechileType.get().getHeavyVechilesCo2Emission().getEmission();
            final double value = co2EmissionRequest.getDistance()*emission;
            final  String result = isRoundOffRequired(value,co2EmissionRequest.getOutput(),outputFlagFound);
            return format(CO2_EMISSION_OUTPUT,result);
        }

        else if(vechileType.map(VechileType::getLargeVechilesCo2Emission).isPresent()) {
            final double emission = vechileType.get().getLargeVechilesCo2Emission().getEmission();
            final double value = co2EmissionRequest.getDistance()*emission;
            final  String result = isRoundOffRequired(value,co2EmissionRequest.getOutput(),outputFlagFound);
            return format(CO2_EMISSION_OUTPUT,result);
        }

        else if(vechileType.map(VechileType::getMediumVechilesCo2Emission).isPresent()) {
            final double emission = vechileType.get().getMediumVechilesCo2Emission().getEmission();
            final double value = co2EmissionRequest.getDistance()*emission;
            final  String result = isRoundOffRequired(value,co2EmissionRequest.getOutput(),outputFlagFound);
            return format(CO2_EMISSION_OUTPUT,result);
        }

        else if(vechileType.map(VechileType::getSmallVechilesCo2Emission).isPresent()) {
            final double emission = vechileType.get().getSmallVechilesCo2Emission().getEmission();
            final double value = co2EmissionRequest.getDistance()*emission;
            final  String result = isRoundOffRequired(value,co2EmissionRequest.getOutput(),outputFlagFound);
            return format(CO2_EMISSION_OUTPUT,result);
        }
        throw new RequiredInputParamsNotFoundException("Cannot be processed for this Given Input");
    }

    public String isRoundOffRequired (double result, final String output, final boolean isOutPutFlagFound) {
        StringBuilder sb = new StringBuilder();
        if(isOutPutFlagFound && (output.equalsIgnoreCase("kg"))) {
                sb.append(Co2EmissionsUtils.roundOffDecimalDigit(result/1000)).append("kg");
        }

        else if(isOutPutFlagFound && output.equalsIgnoreCase("g")) {
            sb.append(result).append("g");
        }

        else if(!isOutPutFlagFound) {
            double s = result/1000;
            if(s < 1) {
                sb.append(result).append("g");
            }
            else {
                sb.append(Co2EmissionsUtils.roundOffDecimalDigit(s)).append("kg");
            }
        }
        return  sb.toString();
    }
}
