package com.shf.dcs.configuration;

import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JwtTokenProvider {
	
	private static final Logger logger = Logger.getLogger(JwtTokenProvider.class);

	// Đoạn JWT_SECRET này là bí mật, chỉ có phía server biết
	private final String JWT_SECRET = "DcsWeb@shf@1234567890";

	// Thời gian có hiệu lực của chuỗi jwt 8 tiếng
	//private final long JWT_EXPIRATION = 21600000L;
	// Thời gian có hiệu lực của chuỗi jwt 600 phút
	private final long JWT_EXPIRATION = 36000000;

	// Tạo ra jwt từ thông tin user
	public String generateToken(String username) {
		Date now = new Date();
		Date expiryDate = new Date(now.getTime() + JWT_EXPIRATION);
		// Tạo chuỗi json web token từ id của user.
		return Jwts.builder().setSubject(username).setIssuedAt(now)
				.setExpiration(expiryDate).signWith(SignatureAlgorithm.HS256, JWT_SECRET).compact();
	}

	// Lấy thông tin user từ jwt
	public String getUserIdFromJWT(String token) {
		Claims claims = Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(token).getBody();
		return claims.getSubject();
	}

	public boolean validateToken(String authToken) {
		try {
			Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(authToken);
			return true;
		} catch (MalformedJwtException ex) {
			logger.error("Invalid JWT token");
		} catch (ExpiredJwtException ex) {
			logger.error("Expired JWT token");
		} catch (UnsupportedJwtException ex) {
			logger.error("Unsupported JWT token");
		} catch (IllegalArgumentException ex) {
			logger.error("JWT claims string is empty.");
		}
		return false;
	}
}
