package streaming.scc.services.kofi;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Restendpoint der (über ein webrelay) aufgerufen wird, wenn in Kofi eine Donation rein kommt
 * 
 * http://localhost:8081/kofiwebhook
 * 
 * @author jonas
 *
 */
@RestController
public class KofiAlertEndpoint {
	
	@GetMapping("/hello")
	public String hello() {
		return "Greetings from Spring Boot!";
	}
	
//	@GetMapping("/kofiwebhook")
//	public void kofiNotification() {
//		
//		System.out.println("Got notified by kofi!");
//	}
	
	@PostMapping("/kofiwebhook")
	public void kofiNotification(@RequestBody String data) {
		
		System.out.println("Got notified by kofi: "+data);
		
		ObjectMapper mapper = new ObjectMapper();
		try {
			String decodedData = java.net.URLDecoder.decode(data, StandardCharsets.UTF_8.name());
			// TODO Es kann ja verschiedene objekte geben (dono, subscription, monthly dono...), das muss heir erkannt werden
			// -> XXX Was passiert denn aktuell bei ner monthly dono?
			KofiDonationDataObject dataobj = mapper.readValue(decodedData.substring(5), KofiDonationDataObject.class);
//			System.out.println("-> "+dataobj);
			
			KofiAlertManager.getManager().logLatestDonation(dataobj);
			
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
//	@PostMapping("/kofiwebhook")
//	public void kofiNotification(@RequestBody KofiDonationDataContainer data) {
//		
//		System.out.println("Got notified by kofi2: "+data);
//		
//	}

}
