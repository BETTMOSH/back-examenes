package es.roberto.gestionexamenesbackend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;
/**
 * @author Roberto Ledezma
 */

@RequiredArgsConstructor
@Service
public class TokenBlackListService {

    /*private final RedisTemplate<String, String> redisTemplate;

    *//**
     * Metodo para añadir un token a la lista negra
     *//*
    public void addTokenToBlackList(String token, long expirationTime) {
        redisTemplate.opsForValue().set(token, "blacklisted", expirationTime, TimeUnit.MILLISECONDS);
    }

    *//**
     * Metodo para comprobar si un token esta en la lista negra
     *//*
    public boolean isTokenBlackListed(String token) {
        return Boolean.TRUE.equals(redisTemplate.hasKey(token));
    }*/
}
