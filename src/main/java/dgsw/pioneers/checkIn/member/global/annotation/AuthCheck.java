package dgsw.pioneers.checkIn.member.global.annotation;

import dgsw.pioneers.checkIn.member.application.domain.model.MemberRole;

import java.lang.annotation.*;

@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AuthCheck {
    MemberRole[] roles() default {MemberRole.STUDENT, MemberRole.ADMIN};
}