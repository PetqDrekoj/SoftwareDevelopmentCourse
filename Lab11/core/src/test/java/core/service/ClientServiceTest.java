package core.service;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import core.ITConfig;
import core.domain.Client;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ITConfig.class})
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class, DbUnitTestExecutionListener.class})
@DatabaseSetup("/META-INF/dbtest/db-data.xml")
@Transactional
public class ClientServiceTest {

    @Autowired
    private ServiceClient serviceClient;

    @Test
    public void findAll() {
        List<Client> clients = serviceClient.getAllClients();
        assertEquals("there should be four students", 2, clients.size());
    }

    @Test
    public void getClientByName() {
        Client c = serviceClient.getClientByName("client1");
        assertNotNull(c);
        assertEquals("client1", c.getName());
    }


    @Test
    public void updateClient() {
        Client c = new Client("client3", 20, null);
        serviceClient.updateClient("client1", c);
        Client new_client = serviceClient.getClientByName("client3");
        assertNotNull(new_client);
        assertEquals(c.getAge(), new_client.getAge());
        assertEquals(c.getName(), new_client.getName());
        List<Client> clients = serviceClient.getAllClients();
        assertEquals("there should be four students", 2, clients.size());
    }

    @Test
    public void deleteClient() {
        List<Client> clients = serviceClient.getAllClients();
        assertEquals("there should be 2 students", 2, clients.size());

        serviceClient.deleteClient("client1");

        List<Client> clients1 = serviceClient.getAllClients();
        assertEquals("there should be 1 student", 1, clients1.size());
    }

    @Test
    public void addClient(){
        List<Client> old_clients = serviceClient.getAllClients();
        assertEquals("there should be 2 clients", 2, old_clients.size());
        Client c = new Client("client3",20,null);
        serviceClient.addClient(c);
        Client new_client = serviceClient.getClientByName("client3");
        assertNotNull(new_client);
        List<Client> clients = serviceClient.getAllClients();
        assertEquals("there should be 3 clients", 3, clients.size());
    }


}