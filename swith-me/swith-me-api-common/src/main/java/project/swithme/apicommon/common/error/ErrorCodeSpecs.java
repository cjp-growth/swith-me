package project.swithme.apicommon.common.error;

import java.util.Map;
import lombok.Getter;

@Getter
public class ErrorCodeSpecs {

    private Map<String, ErrorCodeSpec> errorCodes;

    public ErrorCodeSpecs(Map<String, ErrorCodeSpec> errorCodes) {
        this.errorCodes = errorCodes;
    }
}
