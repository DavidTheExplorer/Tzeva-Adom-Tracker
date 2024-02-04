package dte.tzevaadomtracker.user;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.util.UUID;

@Entity
public class User
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private UUID personalToken;

    public User(String firstName, UUID personalToken)
    {
        this.firstName = firstName;
        this.personalToken = personalToken;
    }

    protected User(){}

    public String getFirstName()
    {
        return this.firstName;
    }

    public UUID getPersonalToken()
    {
        return this.personalToken;
    }
}