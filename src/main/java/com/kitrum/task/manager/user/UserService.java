package com.kitrum.task.manager.user;

import com.kitrum.task.manager.exception.ResourceNotFoundException;
import com.kitrum.task.manager.unit.Unit;
import com.kitrum.task.manager.unit.UnitRepository;
import com.kitrum.task.manager.user.dto.FullUserDto;
import com.kitrum.task.manager.user.dto.UserDto;
import com.kitrum.task.manager.user.thirdparty.ThirdPartyService;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UnitRepository unitRepository;
    private final ThirdPartyService thirdPartyService;

    public UserService(UserRepository userRepository,
                       UnitRepository unitRepository,
                       ThirdPartyService thirdPartyService) {
        this.userRepository = userRepository;
        this.unitRepository = unitRepository;
        this.thirdPartyService = thirdPartyService;
    }

    @PostConstruct
    public void init() {
        Unit unit1 = unitRepository.save(new Unit("1", "Test Unit1"));
        userRepository.save(new User("1", unit1, "Test User1"));

        Unit unit2 = unitRepository.save(new Unit("2", "Test Unit2"));
        userRepository.save(new User("2", unit2, "Test User2"));
    }

    public List<UserDto> getUsers() {
        List<User> units = userRepository.findAll();

        return units.stream()
                .map(UserDto::of)
                .collect(Collectors.toList());
    }

    public FullUserDto getUser(String id)
            throws ResourceNotFoundException {

        FullUserDto user = userRepository.findById(id)
                .map(FullUserDto::of)
                .orElseThrow(() -> new ResourceNotFoundException("User", id));
        thirdPartyService.getUserRating(id)
                .ifPresentOrElse(user::setRating, () -> user.setRating(0));

        return user;
    }
}
