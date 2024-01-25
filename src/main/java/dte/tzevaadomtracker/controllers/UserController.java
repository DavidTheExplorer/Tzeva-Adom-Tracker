package dte.tzevaadomtracker.controllers;

import dte.tzevaadomtracker.dto.requests.UserCreationRequest;
import dte.tzevaadomtracker.dto.responses.UserCreationResponse;
import dte.tzevaadomtracker.services.UserService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("v1/users")
public class UserController
{
    private final UserService userService;

    public UserController(UserService userService)
    {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserCreationResponse> createUser(@RequestBody UserCreationRequest request)
    {
        UUID personalToken = this.userService.createUser(request);

        return ResponseEntity.status(CREATED).body(new UserCreationResponse(personalToken));
    }
}
