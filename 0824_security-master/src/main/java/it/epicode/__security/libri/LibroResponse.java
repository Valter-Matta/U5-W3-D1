package it.epicode.__security.libri;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LibroResponse {
    private Long id;
    private String titolo;
    private String casaEditricee;
    private String autore;
}
