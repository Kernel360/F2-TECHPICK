package kernel360.techpick.feature;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
public class test {

	@GetMapping
	public String connectionTest() {
		return "Hello, Tech-pick test server!";
	}
}
