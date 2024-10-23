package com.bgnc.galleriportal.repository;

import com.bgnc.galleriportal.dto.RefreshTokenRequest;
import com.bgnc.galleriportal.model.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByRefreshToken(RefreshTokenRequest refreshTokenRequest);
}
