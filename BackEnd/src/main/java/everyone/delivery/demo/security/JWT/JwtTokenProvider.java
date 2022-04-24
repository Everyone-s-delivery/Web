package everyone.delivery.demo.security.JWT;

import everyone.delivery.demo.common.exception.LogicalRuntimeException;
import everyone.delivery.demo.security.user.UserRole;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * 참고 링크: https://daddyprogrammer.org/post/636/springboot2-springsecurity-authentication-authorization/
 * JWT 토큰을 생성 및 검증 모듈
 * **/
@RequiredArgsConstructor
@Component
public class JwtTokenProvider {

    @Value("spring.jwt.secret")
    private String secretKey;

    private long tokenValidMilisecond = 1000L * 60 * 60; // 1시간만 토큰 유효

    private final UserDetailsService userDetailsService;

    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    // Jwt 토큰 생성
    public String createToken(String userPk, Set<UserRole> roles) {
        Claims claims = Jwts.claims().setSubject(userPk);
        claims.put("roles", new ArrayList<>(roles));
        Date now = new Date();
        return Jwts.builder()
                .setClaims(claims) // 데이터
                .setIssuedAt(now) // 토큰 발행일자
                .setExpiration(new Date(now.getTime() + tokenValidMilisecond)) // set Expire Time
                .signWith(SignatureAlgorithm.HS256, secretKey) // 암호화 알고리즘, secret값 세팅
                .compact();
    }

    // Jwt 토큰으로 인증 정보를 조회
    @Transactional
    public Authentication getAuthentication(String token) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(this.getUserPk(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    // Jwt 토큰에서 회원 구별 정보 추출
    public String getUserPk(String token) {
        Claims obj = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();

        return obj.getSubject();
    }

    // Request의 Header에서 token 파싱 : "Authorization: jwt토큰"
    public String resolveToken(HttpServletRequest req) {
        String authorization = req.getHeader("Authorization");
        if(authorization == null)
            return null;
        String bearerToken[] = authorization.split(" ");
        return (bearerToken.length == 2 && bearerToken[0].equals("Bearer") ) ? bearerToken[1] : null;
    }

    // Jwt 토큰의 유효성 + 만료일자 확인
    public boolean validateToken(String jwtToken) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwtToken);
            return !claims.getBody().getExpiration().before(new Date());
        } catch (Exception e) {
            return false;
        }
    }
}