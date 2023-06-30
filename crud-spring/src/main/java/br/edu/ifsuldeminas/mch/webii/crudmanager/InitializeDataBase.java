package br.edu.ifsuldeminas.mch.webii.crudmanager;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import br.edu.ifsuldeminas.mch.webii.crudmanager.dao.RestauranteRepository;
import br.edu.ifsuldeminas.mch.webii.crudmanager.dao.EntregadorRepository;
import br.edu.ifsuldeminas.mch.webii.crudmanager.model.*;

@Component
@Transactional
public class InitializeDataBase implements CommandLineRunner {

    @Autowired
    private EntregadorRepository entregadorRepository;

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Override
    public void run(String... args) throws Exception {
        // Simulação de um banco de dados.

        Entregador alberto = new Entregador();
        alberto.setName("Alberto Moreira");
        alberto.setVehicleType("Moto");
        alberto.setGender("M");

        Entregador barbara = new Entregador();
        barbara.setName("Barbara Dias");
        barbara.setVehicleType("Carro");
        barbara.setGender("F");

        Entregador cristiano = new Entregador();
        cristiano.setName("Cristiano Silva");
        cristiano.setVehicleType("Van");
        cristiano.setGender("M");

        Entregador diana = new Entregador();
        diana.setName("Diana Ferreira");
        diana.setVehicleType("Caminhão");
        diana.setGender("F");

        entregadorRepository.save(alberto);
        entregadorRepository.save(barbara);
        entregadorRepository.save(cristiano);
        entregadorRepository.save(diana);

        Restaurante restaurante = new Restaurante();
        restaurante.setName("Donna Nana");
        restaurante.setDeliveryType("Mercadoria");
        restaurante.setAddress("Av. São Carlos, 159");

        restaurante.getEntregadores().add(alberto);
        restaurante.getEntregadores().add(barbara);
        restaurante.getEntregadores().add(cristiano);
        restaurante.getEntregadores().add(diana);

        restauranteRepository.save(restaurante);
    }
}
