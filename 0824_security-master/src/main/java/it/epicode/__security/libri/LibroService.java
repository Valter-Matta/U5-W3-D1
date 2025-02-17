package it.epicode.__security.libri;

import it.epicode.__security.common.CommonResponse;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service
@Validated
@RequiredArgsConstructor
public class LibroService {
    private final LibroRepository libroRepository;

    public List<LibroResponse> findAll() {
        return fromLibri(libroRepository.findAll());
    }


    public LibroResponse findById(Long id) {
        if(!libroRepository.existsById(id)) {
            throw new EntityNotFoundException("Libro con id " + id + " non trovato");
        }

        Libro libro = libroRepository.findById(id).get();
        return fromLibro(libro);
    }

    public CommonResponse save(@Valid LibroRequest libroRequest) {
        Libro libro = fromRequest(libroRequest);

        libroRepository.save(libro);
        CommonResponse response = new CommonResponse();
        response.setId(libro.getId());

        return response;

    }

    public void delete(Long id) {
        if (!libroRepository.existsById(id)) {
            throw new EntityNotFoundException("Libro con id " + id + " non trovato");
        }

        libroRepository.deleteById(id);
    }

    public Libro fromRequest(LibroRequest libroRequest) {
        Libro libro = new Libro();
        BeanUtils.copyProperties(libroRequest, libro);
        return libro;
    }

    public LibroResponse fromLibro(Libro libro) {
        LibroResponse libroResponse = new LibroResponse();
        BeanUtils.copyProperties(libro, libroResponse);
        return libroResponse;
    }

    public List<LibroResponse> fromLibri(List<Libro> libri) {
        return libri
                .stream()
                .map(this::fromLibro)
                .toList();
    }
}
