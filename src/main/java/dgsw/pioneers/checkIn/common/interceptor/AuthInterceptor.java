package dgsw.pioneers.checkIn.common.interceptor;

import dgsw.pioneers.checkIn.common.annotation.AuthCheck;
import dgsw.pioneers.checkIn.common.exception.ExceptionCode;
import dgsw.pioneers.checkIn.common.lib.jwt.JwtUtil;
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

        User user = getUser(request);

        List<Role> roleList = Arrays.stream(annotation.roles()).collect(Collectors.toList());
        checkRole(roleList, user.getRole());

        request.setAttribute("user", user);

        return true;
    }

    private void checkRole(List<Role> roleList, Role userRole) {
        boolean haveAllowedRole = false;

        for (Role role : roleList) {
            if(userRole.equals(role)) {
                haveAllowedRole = true;
                break;
            }
        }

        if (!haveAllowedRole) {
            throw CustomError.of(ExceptionCode.INVALID_PERMISSION);
        }
    }

    private User getUser(HttpServletRequest request) {
        return jwtUtil.getUserByToken(getTokenOfRequest(request).split(" ")[1]);
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