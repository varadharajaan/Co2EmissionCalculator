package io.co2.backend.validation;

import io.co2.backend.exception.InputCountMismatchException;
import io.co2.backend.exception.RequiredInputParamsNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static io.co2.backend.constants.Co2EmissionConstants.DISTANCE;
import static io.co2.backend.constants.Co2EmissionConstants.TRANSPORTATION_METHOD;
import static java.lang.String.format;

/**
 * @Author Varadharajan on 20/09/20 21:09
 * @Projectname co2-simulator
 */
@UtilityClass
public class Co2EmissionRequestValidator {

    public  void requestValidator(final List<String> request) {
        Optional.ofNullable(request)
                .filter(it -> !it.isEmpty() && it.size()%2==0)
                .orElseThrow(() -> new InputCountMismatchException("Malformed input.Request seems to be Insufficient"));
    }

    public  void transformedRequestValidator(final Map<String, String> requestTransformedMap) {
        validateRequiredFieldsFound(TRANSPORTATION_METHOD,requestTransformedMap);
        validateRequiredFieldsFound(DISTANCE,requestTransformedMap);
    }


    private  void validateRequiredFieldsFound(String key, Map<String, String> requestTransformedMap) {
        Optional.ofNullable(requestTransformedMap.get(key))
                .stream()
                .findAny()
                .orElseThrow(() -> new RequiredInputParamsNotFoundException(format("%s not found",key)));
    }
}
