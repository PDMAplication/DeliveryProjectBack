package br.com.projeto.controller;

import br.com.projeto.dto.UsuarioDTO;
import br.com.projeto.model.Usuario;
import br.com.projeto.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/{usuarioId}")
    public ResponseEntity<UsuarioDTO> getUsuario(@PathVariable Long usuarioId) {
        UsuarioDTO usuarioToGet = usuarioService.findUsuarioById(usuarioId);
        return ResponseEntity.ok(usuarioToGet);
    }

    @PostMapping
    public ResponseEntity<UsuarioDTO> saveUsuario(@RequestBody @Valid UsuarioDTO usuarioDTO) {
        return new ResponseEntity<>(this.usuarioService.saveUsuario(usuarioDTO), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<UsuarioDTO> updateUsuario(@RequestBody UsuarioDTO usuarioDTO) {
        UsuarioDTO usuarioToUpdate = usuarioService.updateUsuario(usuarioDTO);
        return ResponseEntity.ok(usuarioToUpdate);
    }

    @DeleteMapping("/{usuarioId}")
    public ResponseEntity<Usuario> deleteById(@PathVariable Long usuarioId) {
        this.usuarioService.deleteUsuario(usuarioId);
        return ResponseEntity.noContent().build();
    }
}
