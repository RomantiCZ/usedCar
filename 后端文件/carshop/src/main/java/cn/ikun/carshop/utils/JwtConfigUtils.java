package cn.ikun.carshop.utils;

import cn.ikun.carshop.pojo.Admin;
import cn.ikun.carshop.pojo.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.*;

/**
 * jwt配置类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
@ConfigurationProperties(prefix = "jwtconfig.jwt")
public class JwtConfigUtils {

    private String secret;
    private long expire;

    private String header;


    public String createAdminToken(String subject, Admin admin) throws Exception {

        Date nowDate = new Date();
        Date expireDate = new Date(System.currentTimeMillis() + expire * 1000);

        var token = Jwts.builder()
                //头信息
                .setHeaderParam("typ", "JWT")
                .setHeaderParam("alg", "HS256")

                //payload
                .claim("adminId", admin.getAdminId())
                .claim("username", admin.getUsername())
                .claim("name", admin.getName())
                .claim("role", admin.getRole())

                .setIssuedAt(nowDate)
                .setExpiration(expireDate)
                .setSubject(subject)
                .setId(UUID.randomUUID().toString())
                //算法信息
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
        return token;
    }

    public Admin parseAdminToken(String token) {
        if (!Optional.ofNullable(token).isPresent()) {
            return null;
        }
        Admin admin = new Admin();
        try {
            var claimsJws = Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            var claims = claimsJws.getBody();

            Date expireTime = claims.getExpiration();// 获取过期时间
            if (expireTime.before(new Date())){
                return null;
            }

            admin.setAdminId((Long) claims.get("adminId"));
            admin.setUsername((String) claims.get("username"));
            admin.setName((String) claims.get("name"));
            admin.setRole((String) claims.get("role"));
            admin.setAdminToken(token);

        } catch (Exception e) {
            return null;
        }
        return admin;
    }

    public String createUserToken(String subject, User user) {
        Date nowDate = new Date();
        Date expireDate = new Date(System.currentTimeMillis() + expire * 1000);


        String token = Jwts.builder()
                //头信息
                .setHeaderParam("typ", "JWT")
                .setHeaderParam("alg", "HS256")
                //payload
                .claim("userId", user.getUserId())
                .claim("userName", user.getUserName())
                .claim("name", user.getName())
                .claim("email",user.getEmail())
                .setIssuedAt(nowDate)
                .setExpiration(expireDate)
                .setSubject(subject)
                .setId(UUID.randomUUID().toString())
                //算法信息
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
        return  token;
    }

    public User parseUserToken(String token){
        if (!Optional.ofNullable(token).isPresent()) {
            return null;
        }
        User user = new User();
        try {
            var claimsJws = Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            var claims = claimsJws.getBody();

            Date expireTime = claims.getExpiration();// 获取过期时间
            if (expireTime.before(new Date())){
                return null;
            }

            user.setUserId((Long) claims.get("userId"));
            user.setUserName((String) claims.get("username"));
            user.setName((String) claims.get("name"));
            user.setEmail((String) claims.get("email"));
            user.setUserToken(token);

        }catch (Exception e){
            return null;
        }
        return user;
    }




}
