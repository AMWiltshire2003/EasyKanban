package com.company.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TaskTest {

 @Test
 void testTaskCreation() {
  Task task = new Task(
          "Login",
          "Create login screen",
          "Adrian Wiltshire",
          5,
          "To Do"
  );

  assertEquals(5, task.getDuration());
  assertEquals("To Do", task.getStatus());
 }

 @Test
 void testTaskToStringContainsDetails() {
  Task task = new Task(
          "Register",
          "Create register screen",
          "Adrian Wiltshire",
          3,
          "Doing"
  );

  String output = task.toString();

  assertTrue(output.contains("Task ID"));
  assertTrue(output.contains("Register"));
  assertTrue(output.contains("Doing"));
 }
}
