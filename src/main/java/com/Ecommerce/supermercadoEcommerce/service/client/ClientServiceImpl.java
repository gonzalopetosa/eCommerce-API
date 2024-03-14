package com.Ecommerce.supermercadoEcommerce.service.client;

import com.Ecommerce.supermercadoEcommerce.entity.Client;
import com.Ecommerce.supermercadoEcommerce.entity.Order;
import com.Ecommerce.supermercadoEcommerce.repository.ClientRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRepository repository;

    @Override
    public Client add(Client client) throws Exception {
        try {
            return repository.save(client);
        }
        catch (Exception e){
            throw new Exception(e.getCause());
        }
    }

    @Override
    public List<Client> findAll() throws Exception {
        try {
            return repository.findAll();
        }
        catch (Exception e){
            throw new Exception(e.getCause());
        }
    }

    @Override
    public Client findById(Integer id) throws Exception {
        try {
            Optional<Client> clientOptionals = repository.findById(id);
            if (clientOptionals.isPresent()) {
                return clientOptionals.get();
            }
            else
                throw new Exception("Client not present");
        }
        catch (Exception e){
            e.printStackTrace();
            throw new Exception("Error al buscar la orden por ID: " + e.getMessage());
            //throw new Exception(e.getCause());
        }
    }

    @Override
    public Client modify(Integer id, Client newClient) throws Exception {
        try {
            Client client = repository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("CLient no encontrado con ID: " + id));
            client.setName(newClient.getName());
            client.setLast_name(newClient.getLast_name());
            client.setAddress(newClient.getAddress());
            client.setCity(newClient.getCity());
            client.setEmail(newClient.getEmail());
            client.setPostal_code(newClient.getPostal_code());
            client.setCountry(newClient.getCountry());
            client.setOrder_history(newClient.getOrder_history());

            return repository.save(client);
        }
        catch (Exception e){
            throw new Exception(e.getCause());
        }
    }

    @Override
    public void deleteById(Integer Id) throws Exception {
        try {
            repository.deleteById(Id);
        }
        catch (Exception e){
            throw new Exception(e.getCause());
        }
    }

    @Override
    public List<Order> findOrderHistoryById(Integer id) throws Exception {
        try {
            Optional<Client> clientOptionals = repository.findById(id);
            if (clientOptionals.isPresent())
                return clientOptionals.get().getOrder_history();
            else
                throw new Exception("Client not present");
        }
        catch (Exception e){
            throw new Exception(e.getCause());
        }
    }

    @Override
    public boolean exist(Integer id) {
        return repository.existsById(id);
    }

    @Override
    public Client findByEmail(String email) {
        return repository.findByEmail(email);
    }

}
