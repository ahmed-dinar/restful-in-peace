package rokomari.java.recruit.restfulinpeace.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import rokomari.java.recruit.restfulinpeace.model.ApiKey;
import rokomari.java.recruit.restfulinpeace.model.AuthToken;
import rokomari.java.recruit.restfulinpeace.model.Login;
import rokomari.java.recruit.restfulinpeace.security.config.ApiKeyProvider;
import rokomari.java.recruit.restfulinpeace.security.config.TokenProvider;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenProvider jwtTokenUtil;
    
    @Autowired
    private ApiKeyProvider keyProvider;


    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> register(@RequestBody Login login) throws AuthenticationException {

        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                		login.getEmail(),
                		login.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        final String token = jwtTokenUtil.generateToken(authentication);
        return ResponseEntity.ok(new AuthToken(token));
    }
    
    
    
    /**
     * test generate api key, must uncomment in production
     * @param login
     * @return
     * @throws AuthenticationException
     */
    @RequestMapping(value = "/apikey", method = RequestMethod.GET)
    public ResponseEntity<?> getApiKey() {
    	
    	String genkey = keyProvider.generateKey();
    	
    	System.out.println(genkey);
    	
        return ResponseEntity.ok(new ApiKey(genkey));
    }
    

}