package dgsw.pioneers.checkIn.global.interceptor;

import dgsw.pioneers.checkIn.global.exception.GlobalExceptionCode;
import dgsw.pioneers.checkIn.global.exception.custom.CustomException;
import dgsw.pioneers.checkIn.member.application.domain.model.Member;
import dgsw.pioneers.checkIn.member.application.domain.model.enums.MemberRole;
import dgsw.pioneers.checkIn.global.annotation.AuthCheck;
import dgsw.pioneers.checkIn.global.lib.jwt.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class AuthInterceptor implements HandlerInterceptor {

    private final JwtUtil jwtUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if(!(handler instanceof HandlerMethod)) {
            return true;
        }

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        AuthCheck annotation = handlerMethod.getMethodAnnotation(AuthCheck.class);

        if (!(handlerMethod.getMethod().isAnnotationPresent(AuthCheck.class))) {
            return true;
        }

        Member member = getMember(request);

        List<MemberRole> roleList = Arrays.stream(annotation.roles()).collect(Collectors.toList());
        checkRole(roleList, member.getMemberRole());

        request.setAttribute("member", member);

        return true;
    }

    private void checkRole(List<MemberRole> roleList, MemberRole userRole) {
        boolean haveAllowedRole = false;

        for (MemberRole role : roleList) {
            if(userRole.equals(role)) {
                haveAllowedRole = true;
                break;
            }
        }

        if (!haveAllowedRole) {
            throw new CustomException(GlobalExceptionCode.INVALID_PERMISSION);
        }
    }

    private Member getMember(HttpServletRequest request) {
        return jwtUtil.getMemberByToken(getTokenOfRequest(request).split(" ")[1]);
    }

    private String getTokenOfRequest(HttpServletRequest request) {
        Enumeration<String> headers = request.getHeaders("Authorization");

        while (headers.hasMoreElements()) {
            String value = headers.nextElement();
            if (value != null) {
                return value;
            }
        }

        return Strings.EMPTY;
    }
}