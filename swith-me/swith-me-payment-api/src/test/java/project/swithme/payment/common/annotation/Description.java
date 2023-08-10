package project.swithme.payment.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
public @interface Description {

    String content() default "";
}
