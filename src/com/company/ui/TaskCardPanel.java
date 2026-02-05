package com.company.ui;

import com.company.model.Task;
import com.company.service.TaskService;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.awt.datatransfer.*;
import java.io.IOException;

/**
 * A visual Kanban task card (like Trello/Jira)
 */
public class TaskCardPanel extends JPanel {

    private Task task;

    // Labels we will update when editing
    private JLabel nameLabel;
    private JLabel developerLabel;
    private JLabel durationLabel;

    public TaskCardPanel(Task task) {
        this.task = task;

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.GRAY),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        setBackground(Color.WHITE);
        setMaximumSize(new Dimension(Integer.MAX_VALUE, 80));

        // Create labels
        nameLabel = new JLabel("📌 " + task.getName());
        developerLabel = new JLabel("👤 " + task.getDeveloper());
        durationLabel = new JLabel("⏱ " + task.getDuration() + " hrs");

        add(nameLabel);
        add(developerLabel);
        add(durationLabel);

        setupContextMenu();

        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                getTransferHandler().exportAsDrag(
                        TaskCardPanel.this, e, TransferHandler.MOVE);
            }
        });

        setTransferHandler(new TransferHandler("task"));

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                JComponent comp = (JComponent) e.getSource();
                TransferHandler handler = comp.getTransferHandler();
                handler.exportAsDrag(comp, e, TransferHandler.MOVE);
            }
        });
    }

    public Task getTask() {
        return task;
    }
    /**
     * Right-click menu (Edit / Delete)
     */
    private void setupContextMenu() {
        JPopupMenu menu = new JPopupMenu();

        JMenuItem editItem = new JMenuItem("Edit");
        JMenuItem deleteItem = new JMenuItem("Delete");

        editItem.addActionListener(e -> editTask());
        deleteItem.addActionListener(e -> deleteTask());

        menu.add(editItem);
        menu.add(deleteItem);

        setComponentPopupMenu(menu);
    }

    /**
     * Edit task details
     */
    private void editTask() {

        String newName = JOptionPane.showInputDialog("Edit Task Name:", task.getName());
        String newDesc = JOptionPane.showInputDialog("Edit Description:", task.getDescription());
        String newDev = JOptionPane.showInputDialog("Edit Developer:", task.getDeveloper());
        int newDuration = Integer.parseInt(
                JOptionPane.showInputDialog("Edit Duration:", task.getDuration())
        );

        // Update task object
        task.setName(newName);
        task.setDescription(newDesc);
        task.setDeveloper(newDev);
        task.setDuration(newDuration);

        // Update UI labels
        nameLabel.setText("📌 " + task.getName());
        developerLabel.setText("👤 " + task.getDeveloper());
        durationLabel.setText("⏱ " + task.getDuration() + " hrs");

        TaskService.saveTasks(
                (ArrayList<Task>) ((KanbanFrame) SwingUtilities.getWindowAncestor(this)).getTasks()
        );
    }

    /**
     * Delete task from board
     */
    private void deleteTask() {
        int confirm = JOptionPane.showConfirmDialog(
                this,
                "Delete this task?",
                "Confirm Delete",
                JOptionPane.YES_NO_OPTION
        );

        if (confirm == JOptionPane.YES_OPTION) {
            Container parent = getParent();
            parent.remove(this);
            parent.revalidate();
            parent.repaint();
        }

        TaskService.saveTasks(
                (ArrayList<Task>) ((KanbanFrame) SwingUtilities.getWindowAncestor(this)).getTasks()
        );
    }

    /**
     * Handles dragging the Task object
     */
    private static class TaskTransferHandler extends TransferHandler {

        private Task task;

        public TaskTransferHandler(Task task) {
            this.task = task;
        }

        @Override
        protected Transferable createTransferable(JComponent c) {
            return new TaskTransferable(task);
        }

        @Override
        public int getSourceActions(JComponent c) {
            return MOVE;
        }
    }

    /**
     * Wraps a Task so it can be transferred during drag
     */
    public static class TaskTransferable implements Transferable {

        public static final DataFlavor TASK_FLAVOR =
                new DataFlavor(Task.class, "Task");

        private Task task;

        public TaskTransferable(Task task) {
            this.task = task;
        }

        @Override
        public DataFlavor[] getTransferDataFlavors() {
            return new DataFlavor[]{TASK_FLAVOR};
        }

        @Override
        public boolean isDataFlavorSupported(DataFlavor flavor) {
            return TASK_FLAVOR.equals(flavor);
        }

        @Override
        public Object getTransferData(DataFlavor flavor)
                throws UnsupportedFlavorException, IOException {

            if (!isDataFlavorSupported(flavor)) {
                throw new UnsupportedFlavorException(flavor);
            }
            return task;
        }
    }

}
