import javax.swing.*;

/**
 * Classe principale per avviare l'applicazione Rubrica con interfaccia grafica
 */
public class Main {
    
    public static void main(String[] args) {
        // Esegue l'applicazione GUI nel thread di Event Dispatch
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    // Crea e mostra la finestra principale
                    RubricaGUI rubricaGUI = new RubricaGUI();
                    rubricaGUI.setVisible(true);
                    
                    System.out.println("=== RUBRICA GUI AVVIATA ===");
                    System.out.println("Interfaccia grafica caricata con successo!");
                    
                } catch (Exception e) {
                    // Gestione errori di avvio
                    System.err.println("Errore durante l'avvio dell'applicazione: " + e.getMessage());
                    e.printStackTrace();
                    
                    JOptionPane.showMessageDialog(null, 
                        "Errore durante l'avvio dell'applicazione:\n" + e.getMessage(),
                        "Errore di Avvio", 
                        JOptionPane.ERROR_MESSAGE);
                    
                    System.exit(1);
                }
            }
        });
    }
}