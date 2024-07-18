package com.blog.common.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 描述:      token配置类
 */
@Component
public class TokenUtil {

    private static long expire = 1800;         //   设置token过期时间 单位：秒
    private static String secret = "abcdefghijk1234567890abcdefjhijk";    //  32位密钥


    /**
     * 生成token
     *
     * @param username
     * @return
     */
    public static String generateToken(String username) {
        Date now = new Date();          //token生效时间
        Date expiration = new Date(now.getTime() + 1000 * expire);     //  token过期时间 :现在的时间 + 设置的过期时间 单位：毫秒
        return Jwts.builder()
                .setHeaderParam("type", "JWT")             //  头部信息
                .setSubject(username)                           //  载荷(携带的参数)
                .setIssuedAt(now)                               //  生效时间
                .setExpiration(expiration)                      //  过期时间
                .signWith(SignatureAlgorithm.HS512, secret)      //  加密签名密钥
                .compact();                                     //  合成
    }

    /**
     * 解析token
     *
     * @param token
     * @return
     */
    public static boolean validateToken(String token) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();

            //判断token是否到期，如果到期返回false，否则返回true
            Date expiration = claims.getExpiration();
            if (expiration.before(new Date())) {
                return false;
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 通过token获取具体值
     *
     * @param token
     * @return
     */
    public static String getClaimsByToken(String token) {
        boolean b = validateToken(token);
        if (b) {
            return Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody().getSubject();
        }
        return "";
    }
}
