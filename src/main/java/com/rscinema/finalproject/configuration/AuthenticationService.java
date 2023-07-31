package com.rscinema.finalproject.configuration;

import com.rscinema.finalproject.domain.dto.LoginRequestDTO;
import com.rscinema.finalproject.domain.entity.user.User;
import com.rscinema.finalproject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class AuthenticationService {

    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtEncoder jwtEncoder;


    public String generateToken(LoginRequestDTO loginReq){
        //authentication starts
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginReq.getEmail(),loginReq.getPassword()));
        Optional<User> user = userRepository.findByEmail(loginReq.getEmail());
        //the actual time in milis
        Instant current = Instant.now();
        //take the authoriy
        String scope = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(" "));
        //create jwtclaimset
        JwtClaimsSet claimsSet = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(current)
                .expiresAt(current.plus(1, ChronoUnit.HOURS))
                .subject(String.valueOf(user.get().getId()))
                .claim("roles",scope)
                .build();
        //jws is an implementation of jwt
        JwtEncoderParameters encoderParameters = JwtEncoderParameters.from(
                JwsHeader.with(MacAlgorithm.HS512).build(),claimsSet
        );

        return jwtEncoder.encode(encoderParameters).getTokenValue();
    }

    public static String getLoggedUser(){
        return SecurityContextHolder.getContext().getAuthentication()
                .getName();
    }

}
