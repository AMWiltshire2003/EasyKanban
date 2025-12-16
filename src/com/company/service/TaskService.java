package com.company.service;

/**
 * Contains business rules for tasks
 */
public class TaskService {

    /**
     * Rule Task descriptions must be <=50 characters
     */
    public static boolean isDescriptionValid(String description) {
        return description.length() <= 50;
    }

    /**
     * Calculates total hours across tasks
     */
    public static int addHours(int currentTotal, int newTaskHours) {
        return currentTotal + newTaskHours;
    }
}
