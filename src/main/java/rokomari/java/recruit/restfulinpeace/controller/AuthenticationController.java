package rokomari.java.recruit.restfulinpeace.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import rokomari.java.recruit.restfulinpeace.model.ApiKey;
import rokomari.java.recruit.restfulinpeace.model.Login;
import rokomari.java.recruit.restfulinpeace.model.User;
import rokomari.java.recruit.restfulinpeace.security.config.ApiKeyProvider;
import rokomari.java.recruit.restfulinpeace.security.config.TokenProvider;
import rokomari.java.recruit.restfulinpeace.service.UserService;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenProvider jwtTokenUtil;
    
    @Autowired
	private UserService userService;
    

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

        User user = userService.findbyEmail(login.getEmail());

        ObjectMapper mapper = new ObjectMapper();
        ObjectNode companyNode = mapper.createObjectNode();
        
        //remove id from role array
        //https://stackoverflow.com/a/15604304
        ArrayNode array =  mapper.valueToTree(user.getRoles());
        for (JsonNode personNode : array) {
        	ObjectNode object = (ObjectNode) personNode;
        	object.remove("id");
        }

		companyNode.put("status", "logged_in");
		companyNode.put("first_names", user.getFirst_name());
		companyNode.put("email", user.getEmail());
		companyNode.putArray("roles").addAll(array);
		
		HttpHeaders headers = new HttpHeaders();
		headers.add(HttpHeaders.CONTENT_TYPE, "application/json; charset=UTF-8");
		headers.add("jwt_token", token);

        return ResponseEntity.ok().headers(headers).body(companyNode);
    }
    
    
    
    /**
     * test generate api key, must uncomment in production
     * @param login
     * @return
     * @throws AuthenticationException
     */
    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/apikey", method = RequestMethod.GET)
    public ResponseEntity<?> getApiKey() {
    	
    	String genkey = new ApiKeyProvider().generateKey();
    	
    	System.out.println(genkey);
    	
        return ResponseEntity.ok(new ApiKey(genkey));
    }
    

}