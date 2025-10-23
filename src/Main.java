import javax.swing.*;
import java.awt.GraphicsEnvironment;

/**
 * Classe principale per avviare l'applicazione Rubrica con interfaccia grafica
 * Supporta modalità headless per Docker
 */
public class Main {
    
    public static void main(String[] args) {
        // Controlla se siamo in ambiente headless (Docker senza GUI)
        if (GraphicsEnvironment.isHeadless() || System.getProperty("java.awt.headless", "false").equals("true")) {
            System.out.println("=== RUBRICA HEADLESS MODE ===");
            System.out.println("Modalità senza interfaccia grafica attivata");
            System.out.println("L'applicazione è stata compilata e containerizzata con successo!");
            System.out.println("Per usare la GUI, esegui il JAR direttamente: java -jar RubricaX.jar");
            System.out.println("==============================");
            return;
        }
        
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
                    
                    // Solo se non siamo in modalità headless
                    if (!GraphicsEnvironment.isHeadless()) {
                        JOptionPane.showMessageDialog(null, 
                            "Errore durante l'avvio dell'applicazione:\n" + e.getMessage(),
                            "Errore di Avvio", 
                            JOptionPane.ERROR_MESSAGE);
                    }
                    
                    System.exit(1);
                }
            }
        });
    }
}