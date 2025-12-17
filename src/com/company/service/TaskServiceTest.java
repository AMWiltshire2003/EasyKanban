package com.company.service;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TaskServiceTest {

    @Test
    void testValidTaskDescription() {
        String description = "Create login screen";
        assertTrue(TaskService.isDescriptionValid(description));
    }

    @Test
    void testInvalidTaskDescription() {
        String description = "This description is intentionally made very long to exceed fifty characters";
        assertFalse(TaskService.isDescriptionValid(description));
    }

    @Test
    void testAddHours() {
        int total = TaskService.addHours(5, 3);
        assertEquals(8, total);
    }
}

