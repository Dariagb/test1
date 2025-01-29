package model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Contact")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column
    String phone;

    @Column
    String email;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;
}
