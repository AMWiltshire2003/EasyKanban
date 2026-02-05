package com.company.service;

import java.io.*;
import java.util.ArrayList;
import com.company.model.Task;

/**
 * Business logic + persistence
 */
public class TaskService {

    private static final String FILE_NAME = "tasks.dat";

    /**
     * Rule: Task descriptions must be <= 50 characters
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

    /**
     * Saves all tasks to a file
     */
    public static void saveTasks(ArrayList<Task> tasks) {
        try (ObjectOutputStream out =
                     new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {

            out.writeObject(tasks);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads tasks from a file
     */
    @SuppressWarnings("unchecked")
    public static ArrayList<Task> loadTasks() {
        try (ObjectInputStream in =
                     new ObjectInputStream(new FileInputStream(FILE_NAME))) {

            return (ArrayList<Task>) in.readObject();

        } catch (Exception e) {
            // File does not exist or first run
            return new ArrayList<>();
        }
    }

}

