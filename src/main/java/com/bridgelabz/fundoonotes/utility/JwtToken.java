package com.bridgelabz.fundoonotes.utility;

import java.io.UnsupportedEncodingException;

import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.Verification;
import com.bridgelabz.fundoonotes.model.Label;

@Component
public class JwtToken {
	
	private static String Token="rohan";
	
	public String generateToken(long id) {
		Algorithm algorithm = null;
		try {
			algorithm = Algorithm.HMAC256(Token);
		} catch (IllegalArgumentException | UnsupportedEncodingException e) {

			e.printStackTrace();
		}
		String token = JWT.create().withClaim("ID", id).sign(algorithm);
		return token;
	}
	
	public long decodeToken(String token){
		long userId = 0L;
		try {
			Verification verification;
			verification = JWT.require(Algorithm.HMAC256(Token));
			JWTVerifier jwtverifier = verification.build();
			DecodedJWT decodedjwt = jwtverifier.verify(token);
			Claim claim = decodedjwt.getClaim("ID");
			userId = claim.asLong();
			return userId;
		} catch (IllegalArgumentException | UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return userId;
	}
}
