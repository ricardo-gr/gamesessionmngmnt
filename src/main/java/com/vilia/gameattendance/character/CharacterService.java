package com.vilia.gameattendance.character;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.vilia.gameattendance.character.exception.CharacterServiceException;
import com.vilia.gameattendance.users.User;
import com.vilia.gameattendance.users.UserRepository;

@Service
public class CharacterService {

	@Autowired
	private CharacterRepository charRepo;
	@Autowired
	private UserRepository userRepo;

	public Character getCharacterById(Long id) {
		return charRepo.findById(id).orElse(null);
	}

	public CharacterDto createCharacter(CharacterDto character) throws CharacterServiceException {
		User owningUser = userRepo.findByUsername(character.getCharacterOwner()).orElse(null);

		if (owningUser == null) {
			throw new CharacterServiceException(HttpStatus.BAD_REQUEST, "Incorrect Owning User for Character");
		}

		Character newCharacter = new Character(character, owningUser);

		newCharacter.setId(null);

		Character createdCharacter = charRepo.save(newCharacter);

		return new CharacterDto(createdCharacter);
	}

	public CharacterDto updateCharacter(Long id, CharacterDto character) throws CharacterServiceException {
		Character existingCharacter = charRepo.findById(id).orElse(null);

		if (existingCharacter == null) {
			throw new CharacterServiceException(HttpStatus.BAD_REQUEST, "Character does not exist : " + id);
		}

		User newOwner = userRepo.findByUsername(character.getCharacterOwner()).orElse(null);

		if (newOwner == null) {
			throw new CharacterServiceException(HttpStatus.BAD_REQUEST, "Incorrect Owning User for Character");
		}

		existingCharacter.update(character, newOwner);

		existingCharacter = charRepo.save(existingCharacter);

		return new CharacterDto(existingCharacter);
	}

	public void deleteCharacterById(Long id) {
		Character existingCharacter = charRepo.findById(id).orElse(null);

		if (existingCharacter == null) {
			throw new CharacterServiceException(HttpStatus.BAD_REQUEST, "Character does not exist : " + id);
		}
		
		charRepo.delete(existingCharacter);

	}

	public List<Character> getCharactersByUser(User principal) {
		return charRepo.findByUserId(principal.getId());
	}

	public List<Character> getAllCharacters() {
		return charRepo.findAll();
	}
	
	

}
