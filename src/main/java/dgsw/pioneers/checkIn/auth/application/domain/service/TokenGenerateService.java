package dgsw.pioneers.checkIn.auth.application.domain.service;

import dgsw.pioneers.checkIn.member.application.domain.model.MemberRole;
import dgsw.pioneers.checkIn.member.global.lib.jwt.JwtType;
import dgsw.pioneers.checkIn.member.global.lib.jwt.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;

@Service
@RequiredArgsConstructor
public class TokenGenerateService {

    private final JwtUtil jwtUtil;

    private static final Long ACCESS_TOKEN_EXPIRE_TIME = 1000L * 3600 * 24 * 3; // 24시간
    private static final Long REFRESH_TOKEN_EXPIRE_TIME = 1000L * 3600 * 24 * 3; // 3일

    public String generateAccessToken(String email, @NotNull MemberRole role) {
        return jwtUtil.generateToken(email, ACCESS_TOKEN_EXPIRE_TIME, JwtType.ACCESS, role);
    };

    public String generateRefreshToken(String email, @NotNull MemberRole role) {
        return jwtUtil.generateToken(email, REFRESH_TOKEN_EXPIRE_TIME, JwtType.REFRESH, role);
    };
}
