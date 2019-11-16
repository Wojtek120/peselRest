package pl.wojtek120.pesel.model.services;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import pl.wojtek120.pesel.model.dto.UserDto;
import pl.wojtek120.pesel.model.entities.User;
import pl.wojtek120.pesel.model.repositories.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final ModelMapper modelMapper;
    private final UserRepository userRepository;

    public UserService(ModelMapper modelMapper, UserRepository userRepository) {
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
    }


    /**
     * Get all user list
     *
     * @return list with all user dto
     */
    public List<UserDto> getAll() {
        return userRepository.findAll().stream().map(this::entityToDto).collect(Collectors.toList());
    }



    public void add(UserDto user) {
        userRepository.save(dtoToEntity(user));
    }

    /**
     * Convert User entity to data transfer object
     *
     * @param entity entity of User
     * @return dto mapped @param
     */
    private UserDto entityToDto(User entity) {
        return modelMapper.map(entity, UserDto.class);
    }


    /**
     * Convert User data transfer object to entity
     *
     * @param dto user data transfer object
     * @return entity mapped from @param
     */
    private User dtoToEntity(UserDto dto){
        return modelMapper.map(dto, User.class);
    }
}
