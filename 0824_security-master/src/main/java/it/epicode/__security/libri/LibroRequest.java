package it.epicode.__security.libri;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LibroRequest {
    @NotBlank(message = "Il titolo è obbligatorio")
    private String titolo;
    @NotBlank(message = "La casa editrice è obbligatoria")
    private String casaEditricee;
    @NotBlank(message = "L'autore è obbligatorio")
    private String autore;
}
