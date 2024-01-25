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
    private String name;

    @Column(nullable = false)
    private UUID personalToken;

    public User(String name, UUID personalToken)
    {
        this.name = name;
        this.personalToken = personalToken;
    }

    protected User(){}

    public String getName()
    {
        return this.name;
    }

    public UUID getPersonalToken()
    {
        return this.personalToken;
    }
}