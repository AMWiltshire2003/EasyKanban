# EasyKanban

**EasyKanban** is a Java Swing desktop application that allows users to manage tasks using a Kanban workflow. It provides a visual board where tasks can be created, edited, deleted, and moved between columns (To Do, Doing, Done). The application also supports task persistence so that the board state is preserved between sessions.

---

## 🧩 Features

### ✅ Completed Features
- **Create tasks** with:
    - Title
    - Description
    - Developer name
    - Estimated hours
    - Status
    - Auto-generated Task ID
- **Edit tasks** – modify existing task details
- **Delete tasks** – remove tasks from the board
- **Task status updates** – update status by moving tasks to different columns
- **Persistence** – save and load tasks locally using `tasks.dat` (object serialization)
- **Partial Drag-and-Drop** – move tasks visually between columns (partially functional)
- **Basic UX improvements** – clear task cards, dropdowns for status selection

### ⚡ Planned / Optional Enhancements
- Fully functional **drag-and-drop between columns**
- **Dark mode / theming**
- Advanced persistence using **JSON or a database**
- Enhanced UX and animations

---

## 🏗️ Architecture Overview

- **UI Layer (Java Swing)**
    - `KanbanFrame` – main application window
    - `TaskCardPanel` – visual representation of individual tasks
- **Business Logic / Model**
    - `Task` class – stores task data such as title, description, hours, status, and Task ID
- **Persistence Layer**
    - Saves and loads task objects to a local binary file (`tasks.dat`) using Java object serialization

**Separation of concerns:** UI handles display, Task class handles logic/data, and persistence layer handles saving/loading.

---

## 💻 How to Run

Open the project in IntelliJ IDEA or any Java IDE.

Run the Main class.

The Kanban board window will open.

Create, edit, or delete tasks, and move them across columns.

Tasks are automatically saved to tasks.dat and will load the next time the app runs.

## 🧪 Unit Testing
Uses JUnit 5 for testing core functionality.

Tests cover:

Task creation and validation

Task ID generation

Total hours calculation

Task details and getters

📖 Real-World Analogy
Tasks → sticky notes on a board

Columns (To Do, Doing, Done) → sections on a physical Kanban board

Persistence (tasks.dat) → saving the board state like taking a photo of your sticky notes before leaving

## 💡 Interview Notes
When explaining this project in interviews, you can say:

“EasyKanban is a Java Swing application that manages tasks visually using a Kanban workflow.
I separated the UI from the model for maintainability, implemented persistence so tasks survive between sessions, and included features like task creation, editing, deletion, and partial drag-and-drop. Future improvements include full drag-and-drop, dark mode, and advanced persistence.”

## 📂 File Structure (Overview)

```
EasyKanban/
├─ src/
│  ├─ com/company/Main.java
│  ├─ com/company/ui/KanbanFrame.java
│  ├─ com/company/ui/TaskCardPanel.java
│  └─ com/company/model/Task.java
├─ tasks.dat          # Auto-generated persistence file
└─ README.md
```
⚠️ Notes
tasks.dat is a binary file and is automatically created at runtime after adding tasks.

Drag-and-drop functionality is partially implemented and may have edge cases.

Dark mode and advanced persistence are planned enhancements.

## Author: 
Adrian Wiltshire

## Technologies: 
Java, Java Swing, JUnit