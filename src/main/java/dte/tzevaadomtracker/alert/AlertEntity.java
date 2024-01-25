package dte.tzevaadomtracker.alert;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Entity
@Table(name = "alert_history")
public class AlertEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String region;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private Integer category;

    @Column(nullable = false)
    private LocalDateTime date;

    public AlertEntity(String region, String title, Integer category, LocalDateTime date)
    {
        this.region = region;
        this.title = title;
        this.category = category;
        this.date = date;
    }
    protected AlertEntity(){}

    public String getRegion()
    {
        return this.region;
    }

    public String getTitle()
    {
        return this.title;
    }

    public Integer getCategory()
    {
        return this.category;
    }

    public LocalDateTime getDate()
    {
        return this.date;
    }

    @Override
    public String toString()
    {
        return String.format("AlertEntity [id=%s, region=%s, title=%s, category=%s, date=%s]", this.id, this.region, this.title, this.category, this.date);
    }
}