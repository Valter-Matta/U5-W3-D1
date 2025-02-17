package it.epicode.__security.libri;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "libri")

public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    // titolo, casaeditrice, autore
    private String titolo;
    private String casaEditricee;
    private String autore;

}
