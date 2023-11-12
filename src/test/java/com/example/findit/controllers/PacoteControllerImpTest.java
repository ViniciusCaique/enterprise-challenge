package com.example.findit.controllers;

import com.example.findit.models.Pacotes;
import com.example.findit.repository.PacoteRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

//@WebMvcTest(controllers = PacoteController.class)
@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class PacoteControllerImpTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PacoteRepository pacoteRepository;

    @Autowired
    private ObjectMapper objectMapper;

    private Pacotes pacotes;

    @BeforeEach
    public void init() {
       pacotes = Pacotes.builder().nome("São Paulo").tipo("Viagem de avião").descricao("Viagem de avião com tudo pago").build();
    }


    @Test
    public void findAllPack() throws Exception {
        List<Pacotes> pacotesList = new ArrayList<>();  
        when(pacoteRepository.findAll()).thenReturn(pacotesList);

        ResultActions resultActions = mockMvc.perform(get("/api/pacotes")
                .contentType(MediaType.APPLICATION_JSON));

        resultActions.andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void createPack() throws Exception {
        given(pacoteRepository.save(ArgumentMatchers.any())).willAnswer((invocation -> invocation.getArgument(0)));

        ResultActions resultActions = mockMvc.perform(post("/api/pacotes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(pacotes)));

        resultActions.andExpect(MockMvcResultMatchers.status().isCreated());
    }

}
