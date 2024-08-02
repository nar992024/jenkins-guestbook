package com.jadecross.guestbook;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

@SpringBootApplication
@RestController
public class PrometheusDemoApplication {

	@Autowired
	private MeterRegistry meterRegistry;

	private Counter counter;

	@PostConstruct
	public void init() {
		counter = meterRegistry.counter("api.call.count");
	}

	public static void main(String[] args) {
		SpringApplication.run(PrometheusDemoApplication.class, args);
	}

	@GetMapping("/test")
	public String test() {
		counter.increment();
		return "test";
	}
}
