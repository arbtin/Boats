package com.arbtin.boatapp.Boats;

import com.arbtin.boatapp.Boats.ship.Ship;
import com.arbtin.boatapp.Boats.ship.ShipRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class BoatsApplicationTests {

	@Autowired
	ShipRepository shipRepository;

	@Test
	void contextLoads() {
	}

	@Test
	void confirmRepositorySetupIsCorrect() {
		List<Ship> all = shipRepository.findAll();
		assertThat(all).hasSize(0);
	}



}
