package com.hse.profile.util;

import com.hse.profile.exception.NoAccessRightsExceptions;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.ws.rs.NotAuthorizedException;
import java.security.Key;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Component;

@Component
public class JwtUtil {
  public static final String SECRET = "5367566B59703373367639792F423F4528482B4D6251655468576D5A71347437";

  public Map<String, Object> validateTokenAndExtractData(final String token) {
    Claims claims = Jwts.parserBuilder().setSigningKey(getSignKey()).build().parseClaimsJws(token)
        .getBody();

    HashMap<String, Object> userInfo = new HashMap<>();
    userInfo.put("id", claims.get("id"));
    userInfo.put("name", claims.get("name"));
    userInfo.put("roles", claims.get("roles"));

    return userInfo;
  }

  private Key getSignKey() {
    byte[] keyBytes = Decoders.BASE64.decode(SECRET);
    return Keys.hmacShaKeyFor(keyBytes);
  }

  public void validateTokenAndCheckAccessRights(String token, String... roles) {
    Map<String, Object> userInfo = validateTokenAndExtractData(token);
    String rolesStr = userInfo.get("roles").toString();
    if (Arrays.stream(roles).noneMatch(rolesStr::contains)) {
      throw new NoAccessRightsExceptions();
    }
  }
}
