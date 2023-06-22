package dgsw.pioneers.checkIn.global.lib.jwt;

import dgsw.pioneers.checkIn.global.exception.GlobalExceptionCode;
import dgsw.pioneers.checkIn.global.exception.custom.CustomException;
import dgsw.pioneers.checkIn.global.properties.JwtProperties;
import dgsw.pioneers.checkIn.member.application.domain.model.Member;
import dgsw.pioneers.checkIn.member.application.domain.model.Member.MemberId;
import dgsw.pioneers.checkIn.member.application.domain.model.MemberRole;
import dgsw.pioneers.checkIn.member.application.port.out.LoadMemberPort;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.w3c.dom.events.EventException;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtUtil {

    private final JwtProperties jwtProperties;
    private final LoadMemberPort loadMemberPort;

    private Key getSignKey(String secretKey) {
        byte[] keyBytes = secretKey.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String generateToken(String memberId, Long time, JwtType type, MemberRole role) {
        Claims claims = Jwts.claims();
        claims.put("id", memberId);
        claims.put("type", type);
        claims.put("role", role);

        Date now = new Date();

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + time))
                .signWith(getSignKey(jwtProperties.getKey()), SignatureAlgorithm.HS256)
                .compact();
    }

    public Claims extractAllClaims(String token) throws ExpiredJwtException, IllegalArgumentException, UnsupportedJwtException, MalformedJwtException {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(getSignKey(jwtProperties.getKey()))
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (ExpiredJwtException e) {
            throw new CustomException(GlobalExceptionCode.TOKEN_EXPIRED);
        } catch (IllegalArgumentException e) {
            throw new CustomException(GlobalExceptionCode.TOKEN_NOT_PROVIDED);
        } catch (EventException e) {
            throw e;
        }
    }

    public JwtType checkTokenType(String token) {
        if ("REFRESH".equals(extractAllClaims(token).get("type"))) {
            return JwtType.REFRESH;
        } else {
            return JwtType.ACCESS;
        }
    }

    public Member getMemberByToken(String token) {
        return loadMemberPort.loadMember(new MemberId(extractAllClaims(token).get("id").toString()));
    }
}