<!-- Use this file to provide workspace-specific custom instructions to Copilot. -->

## Project: RubricaX - Java Swing Contact Book Application

This is a complete Java Swing GUI application for managing a contact book (rubrica). The project demonstrates modern Java development practices with object-oriented design patterns.

### Architecture
- **Model**: `Contatto.java` - Data model for individual contacts
- **Business Logic**: `Rubrica.java` - Contact management operations  
- **View**: `RubricaGUI.java` - Main Swing interface
- **Dialog**: `ContattoDialog.java` - Input dialogs for contacts
- **Entry Point**: `Main.java` - Application launcher

### Features
- Add, edit, delete contacts with validation
- Search by name/surname and phone number
- Modern Swing GUI with colored buttons
- Input validation and error handling
- MVC architecture pattern

### Build & Run
```bash
# Compile
javac -d bin src/*.java

# Run  
java -cp bin Main
```