/*
 * package com.quest.vms.security;
 * 
 * import io.jsonwebtoken.Jwts; import java.io.Serializable; import
 * java.util.Date; import java.util.UUID; import lombok.extern.slf4j.Slf4j;
 * import org.springframework.beans.factory.annotation.Value; import
 * org.springframework.beans.factory.config.ConfigurableBeanFactory; import
 * org.springframework.context.annotation.Scope; import
 * org.springframework.stereotype.Component;
 * 
 * @Component
 * 
 * @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
 * 
 * @Slf4j public class TokenGenerator implements Serializable {
 * 
 * private static final long serialVersionUID = -2550185165626007488L;
 * 
 * @Value("${app.security.token.accessToken.ttl}") private Integer
 * accessTokenTtl;
 * 
 * @Value("${app.security.token.refreshToken.ttl}") private Integer
 * refreshTokenTtl;
 * 
 * @Value("${app.name}") private String serviceName;
 * 
 * public String generateJwtAccessToken(Consumer consumer) { return
 * Jwts.builder().setSubject(consumer.getConsumerNumber()) .setExpiration(new
 * Date(System.currentTimeMillis() + accessTokenTtl)) .setIssuedAt(new
 * Date(System.currentTimeMillis())).setIssuer(serviceName)
 * .setAudience(consumer.getConsumerRefNumber()).setId(String.valueOf(UUID.
 * randomUUID())).compact(); }
 * 
 * public String generateJwtRefreshToken(Consumer consumer) { return
 * Jwts.builder().setSubject(consumer.getConsumerNumber()) .setExpiration(new
 * Date(System.currentTimeMillis() + refreshTokenTtl)) .setIssuedAt(new
 * Date(System.currentTimeMillis())).setIssuer(serviceName)
 * .setAudience(consumer.getConsumerRefNumber()).setId(String.valueOf(UUID.
 * randomUUID())).compact(); } }
 */