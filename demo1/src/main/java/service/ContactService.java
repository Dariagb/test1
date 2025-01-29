package service;

import lombok.RequiredArgsConstructor;
import model.Contact;
import org.springframework.stereotype.Service;
import repositori.ContactRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ContactService {
    private final ContactRepository contactRepository;

    public Contact createContact(Contact contact) {
        return contactRepository.save(contact);
    }

    public List<Contact> getContactsByClientId(Integer clientId) {
        return contactRepository.findByClientId(clientId);
    }

    public List<Contact> getContactsByClientIdAndType(Integer clientId, String contactType) {
        return contactRepository.findByClientIdAndType(clientId, contactType);
    }
}

