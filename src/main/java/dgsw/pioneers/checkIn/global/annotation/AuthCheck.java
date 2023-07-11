package dgsw.pioneers.checkIn.global.annotation;

import dgsw.pioneers.checkIn.member.application.domain.model.enums.MemberRole;

import java.lang.annotation.*;

@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AuthCheck {
    MemberRole[] roles() default {MemberRole.STUDENT, MemberRole.ADMIN};
}