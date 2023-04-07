package com.vilia.gameattendance.character;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vilia.gameattendance.login.authorization.AuthorizationUtils;
import com.vilia.gameattendance.users.User;

import jakarta.annotation.security.RolesAllowed;

@RestController
@RequestMapping("/characters")
public class CharacterController {

    private final CharacterService characterService;
	private AuthorizationUtils authUtils;

    @Autowired
    public CharacterController(CharacterService characterService, AuthorizationUtils authUtils) {
        this.characterService = characterService;
        this.authUtils = authUtils;
    }

    @GetMapping("/{id}")
    @RolesAllowed({"ROLE_USER", "ROLE_ADMIN"})
    public ResponseEntity<CharacterDto> getCharacter(@PathVariable("id") Long id, Authentication authentication) {
        Character character = characterService.getCharacterById(id);
        if (character == null) {
            return ResponseEntity.notFound().build();
        }
        if (!authUtils.authorizeAdminUserOrUserOverResourceUserId(authentication, character.getUser().getId())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        
        return ResponseEntity.ok(new CharacterDto(character));
    }

    @PostMapping
    @RolesAllowed({"ROLE_USER", "ROLE_ADMIN"})
    public ResponseEntity<CharacterDto> createCharacter(@RequestBody CharacterDto character, Authentication authentication) {
        if (!authUtils.authorizeAdminUserOrUserOverResourceOwnerUsername(authentication, character.getCharacterOwner())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        
        character = characterService.createCharacter(character);
        
        return ResponseEntity.created(URI.create("/characters/" + character.getCharacterId())).body(character);
    }

    @PutMapping("/{id}")
    @RolesAllowed({"ROLE_USER", "ROLE_ADMIN"})
    public ResponseEntity<CharacterDto> updateCharacter(@PathVariable("id") Long id, @RequestBody CharacterDto character,
    		Authentication authentication) {
        Character existingCharacter = characterService.getCharacterById(id);
        if (existingCharacter == null) {
            return ResponseEntity.notFound().build();
        }
        if (!authUtils.authorizeAdminUserOrUserOverResourceUserId(authentication, existingCharacter.getUser().getId())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        
        character.setCharacterId(id);
        character = characterService.updateCharacter(id, character);
        
        return ResponseEntity.ok(character);
    }

    @DeleteMapping("/{id}")
    @RolesAllowed({"ROLE_USER", "ROLE_ADMIN"})
    public ResponseEntity<Void> deleteCharacter(@PathVariable("id") Long id, Authentication authentication) {
        Character character = characterService.getCharacterById(id);
        if (character == null) {
            return ResponseEntity.notFound().build();
        }
        if (!authUtils.authorizeAdminUserOrUserOverResourceUserId(authentication, character.getUser().getId())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        
        characterService.deleteCharacterById(id);
        
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    @RolesAllowed({"ROLE_USER", "ROLE_ADMIN"})
    public ResponseEntity<List<Character>> getCharactersByUser(Authentication authentication) {
        List<Character> characters = characterService.getCharactersByUser((User) authentication.getPrincipal());
        return ResponseEntity.ok(characters);
    }
    
    @GetMapping("/all")
    @RolesAllowed("ROLE_ADMIN")
    public ResponseEntity<List<Character>> getAllCharacters() {
    	List<Character> characters = characterService.getAllCharacters();
        return ResponseEntity.ok(characters);
    }
}

