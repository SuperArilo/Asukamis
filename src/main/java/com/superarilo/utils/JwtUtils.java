package com.superarilo.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.util.DigestUtils;

import java.util.Date;

public class JwtUtils {

    public static boolean verify(String token, String secret) {
        try {
            JWT.require(Algorithm.HMAC384(secret)).build().verify(token);
            return true;
        } catch (Exception exception) {
            return false;
        }
    }

    public static String createJWT(String email, Long uid, String password,String secret, long expire_time) {
        return JWT.create().withAudience(email)
                .withExpiresAt(new Date(System.currentTimeMillis() + expire_time * 1000 * 60))
                .withClaim("email", email)
                .withClaim("uid", uid)
                .withClaim("password", DigestUtils.md5DigestAsHex(password.getBytes()))
                .sign(Algorithm.HMAC384(secret));
    }
}
