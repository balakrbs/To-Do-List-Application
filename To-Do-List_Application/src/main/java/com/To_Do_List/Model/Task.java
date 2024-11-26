package com.To_Do_List.Model;

import java.time.LocalDateTime;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;
import jakarta.persistence.Id;

@Entity
public class Task {

    @Id
    private Integer id;

    @NotNull(message = "Give the Title")
    private String title;
    private String description;

    @NotNull(message = "Give Status of this Record")
    private String status;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Task() {
        this.title = "";
        this.status = "";
    }

    // Getters and setters...

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
