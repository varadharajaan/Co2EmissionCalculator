package io.co2.backend;


import io.co2.backend.model.entity.Co2EmissionRequest;
import io.co2.backend.service.Co2EmissionService;
import io.co2.backend.utils.Co2EmissionsUtils;
import io.co2.backend.validation.Co2EmissionRequestValidator;

import java.util.List;
import java.util.Map;

import static io.co2.backend.constants.Co2EmissionConstants.OUTPUT;

/**
 * @Author Varadharajan on 20/09/20 19:19
 * @Projectname co2-simulator
 */
public class Co2CalculatorApplication {


    public static void main(String[] args) {
        final List<String> input = Co2EmissionsUtils.requestFormatter(args[0]);
        Co2EmissionRequestValidator.requestValidator(input);
        final Map<String, String> requestTransformedMap = Co2EmissionsUtils.requestTransformer(input);
        Co2EmissionRequestValidator.transformedRequestValidator(requestTransformedMap);
        final Co2EmissionService co2EmissionService = Co2EmissionService.getInstance();
        Co2EmissionRequest co2EmissionRequest = co2EmissionService.processRequest(requestTransformedMap);
        final String output = co2EmissionService.ouputConverter(co2EmissionRequest,requestTransformedMap.containsKey(OUTPUT));
        System.out.println(output);
    }
}
