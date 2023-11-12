package com.example.findit.config;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.cglib.core.Local;
import org.springframework.context.annotation.Configuration;


import com.example.findit.models.Agencia;
import com.example.findit.models.Caracteristicas;
import com.example.findit.models.Destino;
import com.example.findit.models.Pacotes;
import com.example.findit.models.Passeio;   
import com.example.findit.models.Preferencias;
import com.example.findit.models.Usuario;
import com.example.findit.models.Viagem;
import com.example.findit.repository.AgenciaRepository;
import com.example.findit.repository.CaracteristicaRepository;
import com.example.findit.repository.DestinoRepository;
import com.example.findit.repository.PacoteRepository;
import com.example.findit.repository.PasseioRepository;
import com.example.findit.repository.PreferenciasRepository;
import com.example.findit.repository.UsuarioRepository;
import com.example.findit.repository.ViagemRepository;

@Configuration
public class DatabaseSeeder implements CommandLineRunner {
    
    @Autowired
    AgenciaRepository agenciaRepository;

    @Autowired 
    CaracteristicaRepository caracteristicaRepository;

    @Autowired
    DestinoRepository destinoRepository;

    @Autowired
    PacoteRepository pacoteRepository;
    
    @Autowired
    PasseioRepository passeioRepository;

    @Autowired
    PreferenciasRepository preferenciasRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    ViagemRepository viagemRepository;

    @Override
    public void run(String... args) throws Exception {
        Agencia a1 = new Agencia(1L, "CVC", "CVC");
        Agencia a2 = new Agencia(2L, "123milhas", "123");
        agenciaRepository.saveAll(List.of(a1, a2));

        Destino d1 = new Destino(1L, "Rio de Janeiro", "Cristo redentor que fica localizado no Rio de Janeiro", "BR");
        Destino d2 = new Destino(2L, "Arizona", "Grand Cannyon onde é localizado no arizona", "EUA");
        destinoRepository.saveAll(List.of(d1, d2));

        Usuario u1 = new Usuario(1L, "Fernando", "Fernando@gmail.com", "rM74%7^Ocnv%");
        Usuario u2 = new Usuario(2L, "Cesar", "Cesar@gmail.com", "w4B0!u4bA%&^");
        usuarioRepository.saveAll(List.of(u1, u2));

        pacoteRepository.saveAll(List.of(
            Pacotes.builder().nome("Rio de janeiro").tipo("Viagem de avião").descricao("Viagem de avião com tudo pago para o rio de janeiro").agencia(a2).destino(d2).build(),
            Pacotes.builder().nome("São Paulo").tipo("Viagem de avião").descricao("Viagem de avião com tudo pago").agencia(a2).destino(d2).build()
            ));

        viagemRepository.saveAll(List.of(
            Viagem.builder().descricao("Viagem para o Rio de Janeiro").dataPartida(LocalDate.now()).dataFim(LocalDate.now().plusDays(5)).destino(d1).usuario(u1).build(),
            Viagem.builder().descricao("Viagem para São Paulo").dataPartida(LocalDate.now()).dataFim(LocalDate.now().plusDays(5)).destino(d1).usuario(u1).build()

        ));

        preferenciasRepository.saveAll(List.of(
            Preferencias.builder().nome("Clima").tipo("Calor").usuario(u2).build(),
            Preferencias.builder().nome("Clima").tipo("Frio").usuario(u1).build()

        ));

        caracteristicaRepository.saveAll(List.of(
            Caracteristicas.builder().nome("Calor").tipo("temperatura").destino(d2).build(),
            Caracteristicas.builder().nome("Frio").tipo("temperatura").destino(d1).build()  
        ));
        
        passeioRepository.saveAll(List.of(
            Passeio.builder().nome("Cristo redentor").tipo("Pontos turisticos").descricao("Passeio para o cristo redentor").destino(d2).build(),
            Passeio.builder().nome("MASP").tipo("Pontos turisticos").descricao("Passeio para o MASP").destino(d1).build()
        ));





    }
}
