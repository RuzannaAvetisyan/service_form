package com.example.validatingforminput;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;


@Controller
public class WebController implements WebMvcConfigurer {
	@Autowired
	MailSender mailSender;
	@Autowired
	RequestRepo requestRepo;
	@Autowired
	ServiceRequestRepo serviceRequestRepo;

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/results").setViewName("results");
	}

	@GetMapping("/")
	public String showForm() {
		return "form";
	}

	@PostMapping("/")
	public String checkPersonInfo(@RequestParam String dateTime, @RequestParam String name, @RequestParam String email,
								  @RequestParam List<String> service) {
		Request request = requestRepo.save(new Request(name, email, LocalDateTime.parse(dateTime)));
		if(service != null && !service.isEmpty()){
			for (String s: service){
				serviceRequestRepo.save(new ServiceRequest(Services.valueOf(s), request));
			}
		}
		new Thread(() -> {
			try {
				mailSender.send(email, "Confirmation.",
						"Date confirmation:" + LocalDateTime.parse(dateTime).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))
				);
			}catch (Exception e){
				System.out.println("Something went wrong. Maybe you forgot your password?");
			}
		}).start();
		return "redirect:/results";
	}

	//sorry, without authorization
	@GetMapping("/database")
	public String database(Model model){
		model.addAttribute("data", requestRepo.findAll());
		return "database";
	}

}
