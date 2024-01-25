package dte.tzevaadomtracker.alertendpoint;

import dte.tzevaadomtracker.user.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "user_endpoints")
public class AlertEndpoint
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "url", nullable = false)
    private String url;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User owner;

    public AlertEndpoint(String url, User owner)
    {
        this.url = url;
        this.owner = owner;
    }
    protected AlertEndpoint(){}

    public String getURL()
    {
        return this.url;
    }

    public User getOwner()
    {
        return this.owner;
    }
}
