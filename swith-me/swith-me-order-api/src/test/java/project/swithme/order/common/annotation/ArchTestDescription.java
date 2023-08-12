package project.swithme.order.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.TYPE})
public @interface ArchTestDescription {

    String[] target() default "";

    String[] contents() default "";
}
