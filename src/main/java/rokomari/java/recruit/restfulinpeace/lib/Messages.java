package rokomari.java.recruit.restfulinpeace.lib;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Component;

@Component
public class Messages {

	@Autowired
    private MessageSource messageSource;
	
	private MessageSourceAccessor accessor;
	
	@PostConstruct
    private void init() {
        accessor = new MessageSourceAccessor(messageSource);
    }
	
	public String get(String code) {
        return accessor.getMessage(code);
    }
	
	public String get(String code, Object[] data) {
        return accessor.getMessage(code, data);
    }
	
	public String json(String code, String msg) {
		return "{ \"status\": \""+ code +"\", \"message\": \""+msg+"\" }";
	}
}
