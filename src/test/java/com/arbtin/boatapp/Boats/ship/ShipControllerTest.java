package com.arbtin.boatapp.Boats.ship;

import com.arbtin.boatapp.Boats.captain.OperatorRepository;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import org.springframework.http.MediaType;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class ShipControllerTest {

    @Autowired
    MockMvc mvc;

    @Autowired
    ShipRepository shipRepository;

    @Autowired
    OperatorRepository operatorRepository;

    ObjectMapper mapper = new ObjectMapper();

    @Test
    @Transactional
    void shouldCreateShip() throws Exception {
        Ship ship = new Ship("Valdes", "Bob", 10, SpecialConfiguration.OIL);
        String shipJson = mapper.writeValueAsString(ship);

        mvc.perform(MockMvcRequestBuilders.post("/api/v1/ship")
                .contentType(MediaType.APPLICATION_JSON)
                .content(shipJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.hull").value("Valdes"))
                .andExpect(jsonPath("$.captain").value("Bob"))
                .andExpect(jsonPath("$.capacity").value(10))
                .andExpect(jsonPath("$.special_configuration").value(SpecialConfiguration.OIL.toString()));
    }

    @Test
    @Transactional
    void shouldReturnAllShip() throws Exception {
        shipRepository.save(new Ship("Valdes", "Bob", 10, SpecialConfiguration.OIL));
        shipRepository.save(new Ship("Lolly-pop", "Fred", 100, SpecialConfiguration.CONTAINER));
        shipRepository.save(new Ship("Enterprise", "Kirk", 1000, SpecialConfiguration.TUG));

        mvc.perform(MockMvcRequestBuilders
                        .get("/api/v1/ship"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.*", hasSize(3)))
                .andExpect(jsonPath("$.[2].hull").value("Enterprise"))
                .andExpect(jsonPath("$.[2].captain").value("Kirk"))
                .andExpect(jsonPath("$.[2].capacity").value(1000))
                .andExpect(jsonPath("$.[2].special_configuration").value(SpecialConfiguration.TUG.toString()));
    }

    @Test
    @Transactional
    void shouldReturnShipById() throws Exception {
        shipRepository.save(new Ship("Enterprise", "Kirk", 1000, SpecialConfiguration.TUG));
        Ship savedShip = shipRepository.save(new Ship("Lexington", "Decker", 1000, SpecialConfiguration.CONTAINER));

        mvc.perform(MockMvcRequestBuilders
                        .get("/api/v1/ship/{id}", savedShip.getId()))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(savedShip)));
    }


}
