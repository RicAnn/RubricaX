import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Interfaccia grafica per la gestione della rubrica usando Swing
 */
public class RubricaGUI extends JFrame {
    
    private Rubrica rubrica;
    private DefaultListModel<String> listModel;
    private JList<String> listaContatti;
    private JTextField campoRicercaNome;
    private JTextField campoRicercaTelefono;
    private JTextArea areaDettagli;
    
    private List<Contatto> contattiVisualizati;
    
    // Bottoni
    private JButton btnAggiungi;
    private JButton btnModifica;
    private JButton btnElimina;
    private JButton btnCercaNome;
    private JButton btnCercaTelefono;
    private JButton btnMostraTutti;
    
    /**
     * Costruttore della GUI
     */
    public RubricaGUI() {
        rubrica = new Rubrica();
        contattiVisualizati = new ArrayList<>();
        initializeGUI();
        setupEventListeners();
    }
    
    /**
     * Inizializza l'interfaccia grafica
     */
    private void initializeGUI() {
        setTitle("Gestione Rubrica - GUI");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        
        // Layout principale
        setLayout(new BorderLayout());
        
        // Panel superiore con ricerca
        JPanel panelRicerca = createSearchPanel();
        add(panelRicerca, BorderLayout.NORTH);
        
        // Panel centrale con lista e dettagli
        JPanel panelCentrale = createMainPanel();
        add(panelCentrale, BorderLayout.CENTER);
        
        // Panel inferiore con bottoni
        JPanel panelBottoni = createButtonPanel();
        add(panelBottoni, BorderLayout.SOUTH);
    }
    
    /**
     * Crea il panel di ricerca
     */
    private JPanel createSearchPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        panel.setBackground(new Color(240, 240, 240));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        
        // Ricerca per nome
        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(new JLabel("Cerca per nome:"), gbc);
        
        gbc.gridx = 1; gbc.fill = GridBagConstraints.HORIZONTAL; gbc.weightx = 1.0;
        campoRicercaNome = new JTextField(20);
        panel.add(campoRicercaNome, gbc);
        
        gbc.gridx = 2; gbc.fill = GridBagConstraints.NONE; gbc.weightx = 0;
        btnCercaNome = new JButton("Cerca");
        panel.add(btnCercaNome, gbc);
        
        // Ricerca per telefono
        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(new JLabel("Cerca per telefono:"), gbc);
        
        gbc.gridx = 1; gbc.fill = GridBagConstraints.HORIZONTAL; gbc.weightx = 1.0;
        campoRicercaTelefono = new JTextField(20);
        panel.add(campoRicercaTelefono, gbc);
        
        gbc.gridx = 2; gbc.fill = GridBagConstraints.NONE; gbc.weightx = 0;
        btnCercaTelefono = new JButton("Cerca");
        panel.add(btnCercaTelefono, gbc);
        
        // Bottone mostra tutti
        gbc.gridx = 3; gbc.gridy = 0; gbc.gridheight = 2;
        btnMostraTutti = new JButton("Mostra Tutti");
        panel.add(btnMostraTutti, gbc);
        
        return panel;
    }
    
    /**
     * Crea il panel principale con lista e dettagli
     */
    private JPanel createMainPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        
        // Lista contatti a sinistra
        listModel = new DefaultListModel<>();
        listaContatti = new JList<>(listModel);
        listaContatti.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listaContatti.setFont(new Font("SansSerif", Font.PLAIN, 14));
        
        JScrollPane scrollLista = new JScrollPane(listaContatti);
        scrollLista.setPreferredSize(new Dimension(400, 0));
        scrollLista.setBorder(BorderFactory.createTitledBorder("Contatti"));
        
        // Area dettagli a destra
        areaDettagli = new JTextArea();
        areaDettagli.setEditable(false);
        areaDettagli.setFont(new Font("Monospaced", Font.PLAIN, 12));
        areaDettagli.setBackground(new Color(250, 250, 250));
        
        JScrollPane scrollDettagli = new JScrollPane(areaDettagli);
        scrollDettagli.setPreferredSize(new Dimension(350, 0));
        scrollDettagli.setBorder(BorderFactory.createTitledBorder("Dettagli Contatto"));
        
        // Split pane
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, scrollLista, scrollDettagli);
        splitPane.setDividerLocation(400);
        
        panel.add(splitPane, BorderLayout.CENTER);
        
        return panel;
    }
    
    /**
     * Crea il panel dei bottoni
     */
    private JPanel createButtonPanel() {
        JPanel panel = new JPanel(new FlowLayout());
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        
        btnAggiungi = new JButton("Aggiungi Contatto");
        btnAggiungi.setBackground(new Color(76, 175, 80));
        btnAggiungi.setForeground(Color.WHITE);
        btnAggiungi.setFont(new Font("SansSerif", Font.BOLD, 12));
        
        btnModifica = new JButton("Modifica Contatto");
        btnModifica.setBackground(new Color(255, 152, 0));
        btnModifica.setForeground(Color.WHITE);
        btnModifica.setFont(new Font("SansSerif", Font.BOLD, 12));
        btnModifica.setEnabled(false);
        
        btnElimina = new JButton("Elimina Contatto");
        btnElimina.setBackground(new Color(244, 67, 54));
        btnElimina.setForeground(Color.WHITE);
        btnElimina.setFont(new Font("SansSerif", Font.BOLD, 12));
        btnElimina.setEnabled(false);
        
        panel.add(btnAggiungi);
        panel.add(Box.createHorizontalStrut(10));
        panel.add(btnModifica);
        panel.add(Box.createHorizontalStrut(10));
        panel.add(btnElimina);
        
        return panel;
    }
    
    /**
     * Configura i listener degli eventi
     */
    private void setupEventListeners() {
        // Selezione nella lista
        listaContatti.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int selectedIndex = listaContatti.getSelectedIndex();
                if (selectedIndex >= 0) {
                    mostraDettagliContatto(selectedIndex);
                    btnModifica.setEnabled(true);
                    btnElimina.setEnabled(true);
                } else {
                    areaDettagli.setText("");
                    btnModifica.setEnabled(false);
                    btnElimina.setEnabled(false);
                }
            }
        });
        
        // Bottoni principali
        btnAggiungi.addActionListener(e -> aggiungiContatto());
        btnModifica.addActionListener(e -> modificaContatto());
        btnElimina.addActionListener(e -> eliminaContatto());
        
        // Bottoni di ricerca
        btnCercaNome.addActionListener(e -> cercaPerNome());
        btnCercaTelefono.addActionListener(e -> cercaPerTelefono());
        btnMostraTutti.addActionListener(e -> aggiornaLista());
        
        // Enter nei campi di ricerca
        campoRicercaNome.addActionListener(e -> cercaPerNome());
        campoRicercaTelefono.addActionListener(e -> cercaPerTelefono());
    }
    
    /**
     * Aggiorna la lista dei contatti
     */
    private void aggiornaLista() {
        listModel.clear();
        List<Contatto> contatti = rubrica.getTuttiContatti();
        contattiVisualizati = contatti; // Aggiorna la lista visualizzata
        for (int i = 0; i < contatti.size(); i++) {
            Contatto c = contatti.get(i);
            String item = String.format("%d. %s %s", i + 1, c.getNome(), c.getCognome());
            listModel.addElement(item);
        }
        
        // Reset campi ricerca
        campoRicercaNome.setText("");
        campoRicercaTelefono.setText("");
        
        if (listModel.isEmpty()) {
            areaDettagli.setText("Nessun contatto presente nella rubrica.");
        } else {
            areaDettagli.setText("Seleziona un contatto per visualizzare i dettagli.");
        }
    }
    
    /**
     * Mostra i dettagli del contatto selezionato
     */
    private void mostraDettagliContatto(int index) {
        if (index >= 0 && index < contattiVisualizati.size()) {
            Contatto contatto = contattiVisualizati.get(index);
            if (contatto != null) {
                StringBuilder dettagli = new StringBuilder();
                dettagli.append("DETTAGLI CONTATTO\n");
                dettagli.append("=================\n\n");
                dettagli.append("Nome: ").append(contatto.getNome()).append("\n");
                dettagli.append("Cognome: ").append(contatto.getCognome()).append("\n");
                dettagli.append("Telefono: ").append(contatto.getTelefono()).append("\n");
                dettagli.append("Via: ").append(contatto.getVia()).append("\n");
                
                areaDettagli.setText(dettagli.toString());
            }
        }
    }
    
    /**
     * Aggiunge un nuovo contatto
     */
    private void aggiungiContatto() {
        ContattoDialog dialog = new ContattoDialog(this, "Aggiungi Contatto", null);
        dialog.setVisible(true);
        
        if (dialog.isConfermato()) {
            Contatto nuovoContatto = dialog.getContatto();
            rubrica.aggiungiContatto(nuovoContatto);
            aggiornaLista();
            JOptionPane.showMessageDialog(this, "Contatto aggiunto con successo!", 
                "Successo", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    /**
     * Modifica il contatto selezionato
     */
    private void modificaContatto() {
        int selectedIndex = listaContatti.getSelectedIndex();
        if (selectedIndex >= 0 && selectedIndex < contattiVisualizati.size()) {
            Contatto contattoCorrente = contattiVisualizati.get(selectedIndex);
            ContattoDialog dialog = new ContattoDialog(this, "Modifica Contatto", contattoCorrente);
            dialog.setVisible(true);
            
            if (dialog.isConfermato()) {
                Contatto contattoModificato = dialog.getContatto();
                // Trova l'indice originale nella rubrica
                int indiceOriginale = rubrica.getIndiceContatto(contattoCorrente);
                rubrica.modificaContatto(indiceOriginale, contattoModificato);
                aggiornaLista(); // Torna alla vista completa dopo modifica
                JOptionPane.showMessageDialog(this, "Contatto modificato con successo!", 
                    "Successo", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }
    
    /**
     * Elimina il contatto selezionato
     */
    private void eliminaContatto() {
        int selectedIndex = listaContatti.getSelectedIndex();
        if (selectedIndex >= 0 && selectedIndex < contattiVisualizati.size()) {
            Contatto contatto = contattiVisualizati.get(selectedIndex);
            
            int conferma = JOptionPane.showConfirmDialog(this,
                "Sei sicuro di voler eliminare il contatto:\n" + contatto.getNome() + " " + contatto.getCognome() + "?",
                "Conferma Eliminazione",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);
            
            if (conferma == JOptionPane.YES_OPTION) {
                // Trova l'indice originale nella rubrica e rimuovi
                int indiceOriginale = rubrica.getIndiceContatto(contatto);
                rubrica.rimuoviContatto(indiceOriginale);
                aggiornaLista(); // Torna alla vista completa dopo eliminazione
                JOptionPane.showMessageDialog(this, "Contatto eliminato con successo!", 
                    "Successo", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }
    
    /**
     * Cerca contatti per nome
     */
    private void cercaPerNome() {
        String termine = campoRicercaNome.getText().trim();
        if (!termine.isEmpty()) {
            List<Contatto> risultati = rubrica.cercaContatti(termine);
            mostraRisultatiRicerca(risultati, "nome/cognome", termine);
        }
    }
    
    /**
     * Cerca contatti per telefono
     */
    private void cercaPerTelefono() {
        String termine = campoRicercaTelefono.getText().trim();
        if (!termine.isEmpty()) {
            List<Contatto> risultati = rubrica.cercaPerTelefono(termine);
            mostraRisultatiRicerca(risultati, "telefono", termine);
        }
    }
    
    /**
     * Mostra i risultati della ricerca
     */
    private void mostraRisultatiRicerca(List<Contatto> risultati, String tipoCerca, String termine) {
        listModel.clear();
        contattiVisualizati = risultati; // Aggiorna lista visualizzata con risultati ricerca
        if (risultati.isEmpty()) {
            areaDettagli.setText("Nessun contatto trovato per " + tipoCerca + ": " + termine);
        } else {
            for (int i = 0; i < risultati.size(); i++) {
                Contatto c = risultati.get(i);
                String item = String.format("%d. %s %s", i + 1, c.getNome(), c.getCognome());
                listModel.addElement(item);
            }
            areaDettagli.setText("Trovati " + risultati.size() + " contatti per " + tipoCerca + ": " + termine);
        }
    }
}