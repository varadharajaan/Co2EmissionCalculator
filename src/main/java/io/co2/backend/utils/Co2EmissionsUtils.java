package io.co2.backend.utils;

import io.co2.backend.exception.InputCountMismatchException;
import lombok.experimental.UtilityClass;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

import static io.co2.backend.constants.Co2EmissionConstants.ROUNDOFF_DECIMAL_DIGITS;
import static java.math.RoundingMode.HALF_UP;
import static java.util.Collections.unmodifiableMap;
import static java.util.stream.Collectors.toList;

/**
 * @Author Varadharajan on 20/09/20 19:49
 * @Projectname co2-simulator
 */

@UtilityClass
public class Co2EmissionsUtils {

    public List<String> requestFormatter(@NotNull  final String input) {
        final String[] tokens = input.split(" |\\=");
        return Arrays.stream(tokens).map(it -> it.replace("--", ""))
                .collect(toList());
    }


    public static Map<String, String> requestTransformer(final List<String> input) {
        final Map<String,String> map = new LinkedHashMap<>();
        int i = 0;
        while(i<input.size()) {
            map.put(ignoreSpecialCharactersIfFound(input.get(i)),ignoreSpecialCharactersIfFound(input.get(++i)));
            i++;
        }
        return unmodifiableMap(map);
    }


    private String ignoreSpecialCharactersIfFound(@NotNull final String str) {
        return str.replaceAll("[^a-zA-Z0-9-]","");
    }

    public String  roundOffDecimalDigit(final double d) {
        BigDecimal bd = new BigDecimal(d);
        bd = bd.setScale(1, HALF_UP);
        return bd.toPlainString();
    }
}
