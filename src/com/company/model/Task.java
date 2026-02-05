package com.company.model;

import java.io.Serializable;

/**
 * Represents ONE task card on a Kanban board
 */
public class Task implements Serializable {

    // --- Fields (data the task holds) ---
    private String name;          // Task title
    private String description;   // Short explanation of the task
    private String developer;     // Person responsible
    private int duration;         // Estimated hours
    private String status;        // To Do / Doing / Done
    private String taskId;        // Auto-generated ID

    /**
     * Constructor runs when a Task is created
     */
    public Task(String name, String description, String developer, int duration, String status) {
        this.name = name;
        this.description = description;
        this.developer = developer;
        this.duration = duration;
        this.status = status;
        this.taskId = generateTaskId(); // auto-generate ID
    }

    /**
     * Creates a simple task ID
     * Example: "LO:ADRIAN"
     */
    private String generateTaskId() {
        return name.substring(0, 2).toUpperCase()
                + ":" +
                developer.split(" ")[0].toUpperCase();
    }

    // --- Getters (controlled access to data) ---

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getDeveloper() {
        return developer;
    }

    public int getDuration() {
        return duration;
    }

    public String getStatus() {
        return status;
    }

    // Add these methods inside Task.java

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDeveloper(String developer) {
        this.developer = developer;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Used when displaying task details in the UI
     */
    @Override
    public String toString() {
        return "Task ID: " + taskId +
                "\nName: " + name +
                "\nDescription: " + description +
                "\nDeveloper: " + developer +
                "\nDuration: " + duration + " hrs" +
                "\nStatus: " + status;
    }

}
