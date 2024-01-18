package com.vilia.gameattendance.character;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;

import com.vilia.gameattendance.character.exception.CharacterServiceException;
import com.vilia.gameattendance.users.User;
import com.vilia.gameattendance.users.UserRepository;

@Component
public class Character2CharacterDtoMapper {
	
	@Autowired
	private UserRepository userRepository;

	public Character2CharacterDtoMapper() {}
	
	public CharacterDto newCharacterDto(Character character) {
		CharacterDto newDto = new CharacterDto();
		
		newDto.setActive(character.isActive());
		newDto.setCharacterId(character.getId());
		newDto.setCharacterName(character.getName());
		newDto.setCharacterOwner(character.getUser().getUsername());
		newDto.setPortrait(character.getPortrait());
		
		return newDto;
	}
	
	public Character newCharacter(CharacterDto dto) {
		Character character = new Character();
		
		User ownerUser = findCharacterOwnerUser(dto.getCharacterOwner());
		
		character.setActive(dto.isActive());
		character.setId(dto.getCharacterId());
		character.setName(dto.getCharacterName());
		character.setOwner(ownerUser);
		character.setPortrait(dto.getPortrait());
		
		return character;
	}

	private User findCharacterOwnerUser(String characterOwner) throws CharacterServiceException {
		User owner;
		Optional<User> ownerOpt = userRepository.findByUsername(characterOwner);
		
		if(ownerOpt.isPresent())
			owner = ownerOpt.get();
		else
			throw new CharacterServiceException(HttpStatusCode.valueOf(404), String.format("User %s does not exit", characterOwner));
		
		return owner;
	}
	
	public Character updateCharacter(Character existingCharacter, CharacterDto characterChanges) {
		//TODO: Elaborate method
		
		return null;
	}
}
