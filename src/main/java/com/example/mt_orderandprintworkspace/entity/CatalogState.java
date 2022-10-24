package com.example.mt_orderandprintworkspace.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Entity
@Table(name = "catalog_state")
public class CatalogState {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "actualize_moment")
    @Temporal(TemporalType.TIMESTAMP)
    private Date actualizeDateTime;

    public CatalogState() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getActualizeDateTime() {
        return actualizeDateTime.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    public void setActualizeDateTime(LocalDateTime actualizeDateTime) {
        this.actualizeDateTime = Date.from(actualizeDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }
}
