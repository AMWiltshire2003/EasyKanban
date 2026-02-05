package com.company.ui;

import com.company.model.Task;
import com.company.service.TaskService;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Kanban board UI inspired by Trello / Jira
 */
public class KanbanFrame extends JFrame {

    private ArrayList<Task> tasks;

    // Panels for each column
    private JPanel todoPanel = createColumn("TO DO", new Color(230, 240, 255));
    private JPanel doingPanel = createColumn("DOING", new Color(255, 245, 230));
    private JPanel donePanel = createColumn("DONE", new Color(230, 255, 230));

    public KanbanFrame() {
        setTitle("EasyKanban");
        setSize(900, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Main board layout (3 columns)
        JPanel board = new JPanel(new GridLayout(1, 3, 10, 0));
        board.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        board.add(wrapInScroll(todoPanel));
        board.add(wrapInScroll(doingPanel));
        board.add(wrapInScroll(donePanel));

        // Top bar with button (like Jira/Trello)
        JButton addTaskBtn = new JButton("+ Add Task");
        addTaskBtn.addActionListener(e -> addTask());

        JPanel topBar = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topBar.add(addTaskBtn);

        add(topBar, BorderLayout.NORTH);
        add(board, BorderLayout.CENTER);

        tasks = TaskService.loadTasks();
        loadTasksIntoBoard();

        enableDrop(todoPanel, "To Do");
        enableDrop(doingPanel, "Doing");
        enableDrop(donePanel, "Done");
    }

    /**
     * Creates a column panel
     */
    private JPanel createColumn(String title, Color color) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(color);

        JLabel header = new JLabel(title);
        header.setFont(new Font("Arial", Font.BOLD, 16));
        header.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(header);
        panel.add(Box.createVerticalStrut(10));

        return panel;
    }

    /**
     * Wraps a panel in a scroll pane
     */
    private JScrollPane wrapInScroll(JPanel panel) {
        JScrollPane scroll = new JScrollPane(panel);
        scroll.setBorder(null);
        return scroll;
    }

    /**
     * Handles adding a task
     */
    private void addTask() {

        String name = JOptionPane.showInputDialog("Task Name:");
        String description = JOptionPane.showInputDialog("Task Description:");

        if (!TaskService.isDescriptionValid(description)) {
            JOptionPane.showMessageDialog(this, "Description too long!");
            return;
        }

        String developer = JOptionPane.showInputDialog("Developer:");
        int duration = Integer.parseInt(
                JOptionPane.showInputDialog("Duration (hours):")
        );

        String[] statuses = {"To Do", "Doing", "Done"};
        JComboBox<String> statusDropdown = new JComboBox<>(statuses);

        int result = JOptionPane.showConfirmDialog(
                this,
                statusDropdown,
                "Select Task Status",
                JOptionPane.OK_CANCEL_OPTION
        );

        if (result != JOptionPane.OK_OPTION) return;

        String status = (String) statusDropdown.getSelectedItem();


        Task task = new Task(name, description, developer, duration, status);
        tasks.add(task);

        // Create a visual card
        TaskCardPanel taskCard = new TaskCardPanel(task);

        // Add card to correct column
        switch (status.toLowerCase()) {
            case "to do":
                todoPanel.add(taskCard);
                break;
            case "doing":
                doingPanel.add(taskCard);
                break;
            case "done":
                donePanel.add(taskCard);
                break;
        }

        // Refresh UI
        revalidate();
        repaint();

        TaskService.saveTasks(tasks);
    }

    /**
     * Creates a task "card" like Trello
     */
    private JPanel createTaskCard(Task task) {
        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.GRAY),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        card.setBackground(Color.WHITE);

        card.add(new JLabel("📌 " + task.toString().split("\n")[1]));
        card.add(new JLabel("👤 " + task.toString().split("\n")[3]));
        card.add(new JLabel("⏱ " + task.getDuration() + " hrs"));

        card.setMaximumSize(new Dimension(Integer.MAX_VALUE, 80));

        return card;
    }

    /**
     * Restores saved tasks into the correct columns
     */
    private void loadTasksIntoBoard() {

        for (Task task : tasks) {

            TaskCardPanel card = new TaskCardPanel(task);

            switch (task.getStatus().toLowerCase()) {
                case "to do":
                    todoPanel.add(card);
                    break;
                case "doing":
                    doingPanel.add(card);
                    break;
                case "done":
                    donePanel.add(card);
                    break;
            }
        }

        revalidate();
        repaint();
    }

    /**
     * Enables drag-and-drop for a column
     */
    private void enableDrop(JPanel column, String newStatus) {

        column.setTransferHandler(new TransferHandler() {

            @Override
            public boolean canImport(TransferSupport support) {
                return support.isDataFlavorSupported(
                        TaskCardPanel.TaskTransferable.TASK_FLAVOR);
            }

            @Override
            public boolean importData(TransferSupport support) {

                try {
                    Task task = (Task) support.getTransferable()
                            .getTransferData(
                                    TaskCardPanel.TaskTransferable.TASK_FLAVOR);

                    TaskCardPanel card = new TaskCardPanel(task);

                    // Remove from old parent
                    Container oldParent = card.getParent();
                    if (oldParent != null) {
                        oldParent.remove(card);
                    }

                    // Update task status
                    task.setStatus(newStatus);

                    // Add to new column
                    column.add(card);

                    // Save change
                    TaskService.saveTasks(tasks);

                    revalidate();
                    repaint();

                    return true;

                } catch (Exception e) {
                    e.printStackTrace();
                    return false;
                }
            }
        });
    }

    public Object getTasks() {
        return getTasks();
    }
}
