package com.jiuv.cornerstone.oauth.jwt;

import cn.hutool.json.JSON;
import com.jiuv.cornerstone.oauth.entity.UserInfo;
import io.jsonwebtoken.*;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @className: JwtUtil
 * @description:
 * @author: jianghs430
 * @createDate: 2021/5/6 16:23
 * @version: 1.0
 */
public class JwtUtil {
    // token秘钥
    public static String SECRET = "qwerasdfdxzvdfajjlkjeiojznvxndjkfaowijeiodl";

    /**
     * 生成Jwt的方法
     *
     * @param id  用户ID
     * @param subject 用户昵称
     * @param userInfo 自定义信息
     * @param ttlMillis 过期时间
     * @return Token String 凭证
     */
    public static String createJWT(String id, String subject, UserInfo userInfo, long ttlMillis) {
        // 签名方法 HS256
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        // 生成Jwt的时间
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        // 生成秘钥
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(SECRET);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        // 设置JWT所存储的信息
        JwtBuilder builder = Jwts.builder().setId(id).setIssuedAt(now).setSubject(subject).signWith(signatureAlgorithm, signingKey);

        //存储自定义信息 权限
        builder.claim("userInfo", userInfo);

        // 设置过期时间
        if (ttlMillis >= 0) {
            long expMillis = nowMillis + ttlMillis;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp);
        }

        // 构建JWT并将其序列化为紧凑的URL安全字符串
        return builder.compact();
    }

    /**
     * 解析Jwt字符串
     *
     * @param jwt
     *            Jwt字符串
     * @return Claims 解析后的对象
     */
    public static Claims parseJWT(String jwt) {
        return Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(SECRET)).parseClaimsJws(jwt).getBody();
    }

    /**
     * 验证JWT
     *
     * @param jwtStr jwt字符串
     * @return JOSNObject 解析结果<br/>
     *         &emsp;&emsp;Success 成功标识<br/>
     *         &emsp;&emsp;&emsp;&emsp;true：成功<br/>
     *         &emsp;&emsp;&emsp;&emsp;false：失败<br/>
     *         &emsp;&emsp;Claim 声明对象<br/>
     *         &emsp;&emsp;ErrCode 错误码<br/>
     *         &emsp;&emsp;&emsp;&emsp;1005：过期<br/>
     *         &emsp;&emsp;&emsp;&emsp;1004：未登录
     */
    public static Map<String, Object> validateJWT(String jwtStr) {
        Map<String, Object> pojo = new HashMap<>();
        Claims claims;
        try {
            claims = parseJWT(jwtStr);
            pojo.put("status", true);
            pojo.put("code", 1000);
            pojo.put("Claims", claims);
        } catch (ExpiredJwtException e) {
            pojo.put("status", false);
            pojo.put("code", 1005);
            e.printStackTrace();
        } catch (Exception e) {
            pojo.put("status", false);
            pojo.put("code", 1004);
            e.printStackTrace();
        }
        return pojo;
    }
}
