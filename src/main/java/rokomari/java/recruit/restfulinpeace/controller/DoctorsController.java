package rokomari.java.recruit.restfulinpeace.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class DoctorsController {

	@RequestMapping(value="/test", method = RequestMethod.GET)
	public ResponseEntity<String> justTest() {
		
		
		System.out.println("hey doctor!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");

		return ResponseEntity.ok().body("Hello");
	}
	
}
