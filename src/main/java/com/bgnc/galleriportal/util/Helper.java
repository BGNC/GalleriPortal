package com.bgnc.galleriportal.util;

import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.experimental.UtilityClass;

import java.security.Key;

@UtilityClass
public class Helper {

    public  final String SECRET_KEY="0cEovTmVR+8fM+DNDIsRVTV8mgUVLcl7CteepmLqxho=";

    public Key getKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
