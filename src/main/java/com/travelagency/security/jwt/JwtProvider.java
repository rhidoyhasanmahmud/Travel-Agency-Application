package com.travelagency.security.jwt;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.servlet.http.HttpServletRequest;

import com.travelagency.model.User;
import com.travelagency.security.services.UserPrinciple;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class JwtProvider {

    private final String jwtSecret = "brain-station-unlocking";

	public String generateJwtToken(User user) {
		Map<String, Object> claims = new HashMap<String, Object>();
		claims.put("id", user.getId());
		claims.put("userid", user.getUserId());
		claims.put("name", user.getName());
		claims.put("email", user.getEmail());
		int jwtExpiration = 86400;
		return Jwts.builder().setClaims(claims).setSubject((user.getUserId()))
				.setIssuedAt(new Date())
				.setExpiration(new Date((new Date()).getTime() + jwtExpiration * 1000))
				.signWith(SignatureAlgorithm.HS512, jwtSecret)
				.compact();

	}

	public Claims extractAllClaims(HttpServletRequest request) {
		Claims claims;
		String jwt = getJwt(request);
		try {
			claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(jwt).getBody();
		} catch (Exception e) {
			log.error("Could not get all claims from passed token");
			claims = null;
		}
		return claims;
	}

	public boolean validateJwtToken(String authToken) {
		try {
			Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
			return true;
		} catch (SignatureException e) {
			log.error("Invalid JWT signature -> Message: {} ", e);
		} catch (MalformedJwtException e) {
			log.error("Invalid JWT token -> Message: {}", e);
		} catch (ExpiredJwtException e) {
			log.error("Expired JWT token -> Message: {}", e);
		} catch (UnsupportedJwtException e) {
			log.error("Unsupported JWT token -> Message: {}", e);
		} catch (IllegalArgumentException e) {
			log.error("JWT claims string is empty -> Message: {}", e);
		}

		return false;
	}

	public String getUserNameFromJwtToken(String token) {
		return Jwts.parser()
				.setSigningKey(jwtSecret)
				.parseClaimsJws(token)
				.getBody().getSubject();
	}

	private String getJwt(HttpServletRequest request) {
		String authHeader = request.getHeader("Authorization");

		if (authHeader != null && authHeader.startsWith("Bearer ")) {
			return authHeader.replace("Bearer ","");
		}

		return null;
	}
}
