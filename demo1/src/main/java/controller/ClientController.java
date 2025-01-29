package controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import model.Client;
import model.Contact;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.ClientService;
import service.ContactService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("client")
@RequiredArgsConstructor
public class ClientController {
    private final ClientService clientService;
    private final ContactService contactService;

    @Operation(summary = "Создать клиента")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Успешно создан"),
            @ApiResponse(responseCode = "400", description = "Некорректный запрос")
    })
    @PostMapping
    public ResponseEntity<Client> addClient(@RequestBody Client client) {
        Client createdClient = clientService.createClient(client);
        return new ResponseEntity<>(createdClient, HttpStatus.CREATED);
    }

    @Operation(summary = "Создать контакт")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Успешно создан"),
            @ApiResponse(responseCode = "400", description = "Некорректный запрос")
    })
    @PostMapping("/contact")
    public ResponseEntity<Contact> addContact(@RequestBody Contact contact) {
        Contact createdContact = contactService.createContact(contact);
        return new ResponseEntity<>(createdContact, HttpStatus.CREATED);
    }

    @Operation(summary = "Получить список клиентов")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Успешно получен"),
            @ApiResponse(responseCode = "404", description = "Клиенты не найдены")
    })
    @GetMapping
    public ResponseEntity<List<Client>> getClients() {
        List<Client> clients = clientService.readAllClient();
        if (clients.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(clients, HttpStatus.OK);
    }

    @Operation(summary = "Получить клиента по id", description = "Успешно получен")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Успешно получен"),
            @ApiResponse(responseCode = "404", description = "Клиент не найден")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Client> getClientById(@PathVariable Integer id) {
        Optional<Client> clientOptional = clientService.getClientById(id);
        return clientOptional.map(client -> ResponseEntity.ok(client))
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Получить список контактов заданного клиента")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Успешно получен"),
            @ApiResponse(responseCode = "404", description = "Контакты не найдены")
    })
    @GetMapping("/{clientId}/contacts")
    public ResponseEntity<List<Contact>> getContactsByClient(@PathVariable Integer clientId) {
        List<Contact> contacts = contactService.getContactsByClientId(clientId);
        if (contacts.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(contacts, HttpStatus.OK);
    }

    @Operation(summary = "Получить список контактов заданного типа для заданного клиента")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Успешно получен"),
            @ApiResponse(responseCode = "404", description = "Контакты не найдены")
    })
    @GetMapping("/{clientId}/contacts/{contactType}")
    public ResponseEntity<List<Contact>> getContactsByTypeAndClient(
            @PathVariable Integer clientId,
            @PathVariable String contactType
    ) {
        List<Contact> contacts = contactService.getContactsByClientIdAndType(clientId, contactType);
        if (contacts.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(contacts, HttpStatus.OK);
    }

}
