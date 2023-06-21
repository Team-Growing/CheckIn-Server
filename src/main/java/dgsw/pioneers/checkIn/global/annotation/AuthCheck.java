package dgsw.pioneers.checkIn.global.annotation;

import java.lang.annotation.*;

@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AuthCheck {
//    Role[] roles() default {Role.STUDENT, Role.ADMIN};
}