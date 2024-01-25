package dte.tzevaadomtracker.services;

import dte.tzevaadomtracker.dto.requests.UserCreationRequest;
import dte.tzevaadomtracker.repositories.UserRepository;
import dte.tzevaadomtracker.user.User;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService
{
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository)
    {
        this.userRepository = userRepository;
    }

    public UUID createUser(UserCreationRequest request)
    {
        String name = request.name();

        if(this.userRepository.existsByName(name))
            throw new IllegalArgumentException(String.format("The username '%s' is taken!", name));

        UUID personalToken = UUID.randomUUID();

        this.userRepository.save(new User(name, personalToken));

        return personalToken;
    }

    public long getUsersAmount()
    {
        return this.userRepository.count();
    }
}
