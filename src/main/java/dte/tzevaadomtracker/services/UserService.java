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

    public User findByPersonalToken(UUID personalToken)
    {
        return this.userRepository.findByPersonalToken(personalToken)
                .orElseThrow(() -> new IllegalArgumentException(String.format("Couldn't find a user identified by the token '%s'", personalToken)));
    }

    public UUID createUser(UserCreationRequest request)
    {
        UUID personalToken = UUID.randomUUID();

        this.userRepository.save(new User(request.firstName(), personalToken));

        return personalToken;
    }

    public long getUsersAmount()
    {
        return this.userRepository.count();
    }
}
