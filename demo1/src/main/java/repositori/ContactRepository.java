package repositori;

import model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Integer> {
    List<Contact> findByClientId(Integer clientId);
    List<Contact> findByClientIdAndType(Integer clientId, String contactType);
}