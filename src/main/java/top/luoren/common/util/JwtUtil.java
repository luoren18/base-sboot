package top.luoren.common.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author luoren
 * @date 2019/9/4 08:02
 */
@Data
@Component
@ConfigurationProperties(prefix = "jwt")
public class JwtUtil {
    private static final String SECRET = "sboot:F\"0?O_Rri98VhHp63M<l)qdL]./KwtB*";
    private static final long EXPIRATION_TIME = 2 * 60 * 60 * 1000;


    /**
     * 从数据声明生成令牌
     *
     * @param claims 数据声明
     * @return 令牌
     */
    private static String generateToken(Map<String, Object> claims) {
        Date expirationDate = new Date(System.currentTimeMillis() + EXPIRATION_TIME);
        return Jwts.builder().setClaims(claims).setExpiration(expirationDate).signWith(SignatureAlgorithm.HS512, SECRET).compact();
    }

    /**
     * 从令牌中获取数据声明
     *
     * @param token 令牌
     * @return 数据声明
     */
    private static Claims getClaimsFromToken(String token) {
        Claims claims;
        try {
            claims = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
        } catch (Exception e) {
            claims = null;
        }
        return claims;
    }

    /**
     * 生成令牌
     *
     * @param username 用户
     * @return 令牌
     */
    public static String generateToken(String username, Set<String> roles, Set<String> permissions) {
        Map<String, Object> claims = new HashMap<>(4);
        claims.put(Claims.SUBJECT, username);
        claims.put(Claims.ISSUED_AT, new Date());
        claims.put("roles", roles);
        claims.put("permission", permissions);
        return generateToken(claims);
    }

    /**
     * 生成令牌
     *
     * @param username 用户
     * @return 令牌
     */
    public static String generateToken(String username) {
        Map<String, Object> claims = new HashMap<>(4);
        claims.put(Claims.SUBJECT, username);
        claims.put(Claims.ISSUED_AT, new Date());
        claims.put("roles", null);
        claims.put("permission", null);
        return generateToken(claims);
    }

    /**
     * 从令牌中获取用户名
     *
     * @param token 令牌
     * @return 用户名
     */
    public static String getUsernameFromToken(String token) {
        String username;
        try {
            Claims claims = getClaimsFromToken(token);
            username = claims.getSubject();
        } catch (Exception e) {
            username = null;
        }
        return username;
    }


    /**
     * 判断令牌是否过期
     *
     * @param token 令牌
     * @return 是否过期
     */
    public static Boolean isTokenExpired(String token) {
        try {
            Claims claims = getClaimsFromToken(token);
            Date expiration = claims.getExpiration();
            return expiration.before(new Date());
        } catch (Exception e) {
            return true;
        }
    }

    /**
     * 刷新令牌
     *
     * @param token 原令牌
     * @return 新令牌
     */
    public static String refreshToken(String token) {
        String refreshedToken;
        try {
            Claims claims = getClaimsFromToken(token);
            claims.put(Claims.ISSUED_AT, new Date());
            refreshedToken = generateToken(claims);
        } catch (Exception e) {
            refreshedToken = null;
        }
        return refreshedToken;
    }

    /**
     * 验证令牌
     *
     * @param token    令牌
     * @param username 用户名
     * @return 是否有效
     */
    public static boolean validateToken(String token, String username) {
        return (getUsernameFromToken(token).equals(username) && !isTokenExpired(token));
    }

    public static void main(String[] args) throws InterruptedException {

        String token = JwtUtil.generateToken("luoren");
        System.out.println(token);
        boolean isExpired = JwtUtil.isTokenExpired(token);
        System.out.println(isExpired);
    }
}
