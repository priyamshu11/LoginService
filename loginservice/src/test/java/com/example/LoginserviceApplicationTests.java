package com.example;

import com.example.controller.MainController;
import com.example.entity.UserEntity;
import com.example.repository.LoginRepository;
import com.example.service.LoginService;
import com.netflix.discovery.converters.Auto;
import io.micrometer.core.ipc.http.HttpSender;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ActiveProfiles("test")
class LoginserviceApplicationTests {

	@Autowired
	LoginRepository loginRepository;
	@Autowired
	LoginService loginService;
	@Autowired
	MainController mainController;

	@Test
	@DisplayName("Check test two")
	public void t2(){
		System.out.println("test two");
	}

	@Test
	@DisplayName("Check test one")
	public void t1(){
		loginRepository.save(new UserEntity(null,"Deepthi","200@ascA308"));
		assertEquals (1,loginRepository.findAll().size());
	}

	@Test
	@DisplayName(" Check Pass Login Status")
	public void t3(){
		loginRepository.save(new UserEntity(null, "Deepthi", "200@ascA308"));
		ResponseEntity<Boolean> response = mainController.f4(new UserEntity(null,"Deepthi", "200@ascA308"));
		assertEquals (true, response.getBody());
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}

	@Test
	@DisplayName(" Check Fail Login Status")
	public void t4(){
		loginRepository.save(new UserEntity(null, "Deepthi", "200@ascA308"));
		ResponseEntity<Boolean> response = mainController.f4(new UserEntity(null, "Deepthi", "200@ascA30"));
		assertEquals (false, response.getBody());
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}

	@BeforeEach
	void setup(){
		loginRepository.deleteAll();
		loginRepository.save(new UserEntity(null, "Deepthi", "200@ascA308"));
		loginRepository.save(new UserEntity(null, "Priyamsh", "200@ascA30"));
	}

	@DisplayName("check login with parametized data")
	@ParameterizedTest
	@CsvSource({"Deepthi,200@ascA308", "Priyamsh,200@ascA30"})
	public void t5(String name, String password){
		ResponseEntity<Boolean> response = mainController.f4(new UserEntity(null, name, password));
		assertTrue(response.getBody());
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}





}
