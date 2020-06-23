package core.repository;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import core.ITConfig;
import core.domain.Client;
import core.domain.Movie;
import core.repository.dbRepository.ClientRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ITConfig.class})
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class, DbUnitTestExecutionListener.class})
@DatabaseSetup("/META-INF/dbtest/repoClient.xml")
public class ClientRepositoryTest {

    @Autowired
    private ClientRepository clientRepository;

    @Test
    public void findAllWithRentals(){
        List<Client> clients = this.clientRepository.findAllWithRentals();
        assertEquals("there should be 2 clients", 2, clients.size());
    }

    @Test
    public void findByName(){
        Client client = this.clientRepository.findByName("client1");
        assertNotNull(client);
        assertEquals(client.getName(),"client1");
    }

    @Test
    public void findAllSimple(){
        List<Client> clients = this.clientRepository.findAllWithRentals();
        assertEquals("there should be 2 clients", 2, clients.size());
    }

    @Test
    public void findAllWithRentsAndMovies(){
        List<Client> clients = this.clientRepository.findAllWithRentals();
        assertEquals("there should be 2 clients", 2, clients.size());
    }

    @Test
    public void updateClient(){
        Client c = this.clientRepository.findByName("client1");
        c.setName("new-client1");
        this.clientRepository.updateClient(c);
        Client new_client = this.clientRepository.findByName("new-client1");
        assertNotNull(new_client);
        assertEquals(new_client.getName(),"new-client1");
        assertEquals(new_client.getAge(),c.getAge());
        assertEquals(new_client.getId(),c.getId());
    }

    @Test
    public void addClient(){
        List<Client> old_clients = clientRepository.findAllSimple();
        assertEquals("there should be 2 clients", 2, old_clients.size());
        Client c = new Client(90,"client3",20,null);
        Client savedClient = clientRepository.save(c);
        assertNotNull(savedClient);
        List<Client> clients = clientRepository.findAllSimple();
        assertEquals("there should be 3 clients", 3, clients.size());
    }

    @Test
    public void deleteClient(){
        List<Client> old_clients = clientRepository.findAllSimple();
        assertEquals("there should be 2 clients", 2, old_clients.size());
        Client c = clientRepository.findByName("client1");
        assertNotNull(c);
        clientRepository.delete(c);
        List<Client> clients = clientRepository.findAllSimple();
        assertEquals("there should be 1 client", 1, clients.size());
    }



}