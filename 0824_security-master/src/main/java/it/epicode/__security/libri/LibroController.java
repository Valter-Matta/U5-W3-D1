package it.epicode.__security.libri;

import it.epicode.__security.common.CommonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/libri")
@RequiredArgsConstructor
// eventuale autorizzazione di default su tutti i metody di tipo endpoint
// del controller
@PreAuthorize("isAuthenticated()")
public class LibroController {
    private final LibroService libroService;

    @GetMapping
    @PreAuthorize("hasRole('USER')")
    // solo un utente con ruolo USER può usare questo metodo
    public List<LibroResponse>  findAll() {
        return libroService.findAll();
    }

    @GetMapping("/{id}")
    // tutti gli utenti autenticati possono usare questo metodo
    @PreAuthorize("isAuthenticated()")
    public LibroResponse findById(Long id) {
        return libroService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ADMIN')")
    // preautorize mette in sicurezza l'endpoint in modo che solo chi
    // ha ruolo ADMIN possa utilizzarlo
    public CommonResponse save(LibroRequest libroRequest) {
        return libroService.save(libroRequest);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    // solo alcuni ruoli possono usar questo metodo
    @PreAuthorize("hasAnyRole('ADMIN', 'SELLER')")
    public void delete(Long id) {
        libroService.delete(id);
    }
}
