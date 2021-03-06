package web.converter;

import core.domain.Client;
import org.springframework.stereotype.Component;
import web.dto.ClientDto;

@Component
public class ClientConverter extends BaseConverter<Client, ClientDto> {

    @Override
    public Client convertDtoToModel(ClientDto dto) {
        return new Client(dto.getId(), dto.getName(), dto.getAge());
    }

    @Override
    public ClientDto convertModelToDto(Client client) {
        return new ClientDto(client.getId(), client.getName(), client.getAge());
    }
}
