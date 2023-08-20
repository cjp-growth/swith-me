package project.swithme.order.core.presentation.query.validator;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;
import project.study.support.exception.InvalidParameterException;
import project.study.support.log.Field;

@Component
public class OrderPagingValidator {

    public void validate(
        LocalDate startDate,
        LocalDate endDate
    ) {
        if (isNotNull(startDate) && isNotNull(endDate) && isInvalidStartDate(startDate, endDate)) {
            throw new InvalidParameterException(getFields(startDate, endDate));
        }
    }

    private boolean isInvalidStartDate(
        LocalDate startDate,
        LocalDate endDate
    ) {
        return startDate.isAfter(endDate);
    }

    public List<Field> getFields(
        LocalDate startDate,
        LocalDate endDate
    ) {
        List<Field> fields = new ArrayList<>();
        fields.add(Field.of("startDate", startDate));
        fields.add(Field.of("endDate", endDate));
        return fields;
    }

    private boolean isNotNull(LocalDate date) {
        return date != null;
    }
}
