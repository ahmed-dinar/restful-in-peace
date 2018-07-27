package rokomari.java.recruit.restfulinpeace.lib;

import java.util.List;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Component;

@Component
public class Helper {

	/**
	 * Check id in header, doctor_id, patient_id etc
	 * 
	 * @param id
	 * @return
	 */
	public static Long hasValidId(List<String> id) {

		if (id == null || id.size() == 0) {
			return (long) -1;
		}

		try {
			return Long.parseLong(id.get(0));
		} catch (NumberFormatException e) {
			throw new BadCredentialsException("Invalid id");
		}
	}

}
