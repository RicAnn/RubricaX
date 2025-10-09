import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Dialog per aggiungere o modificare un contatto
 */
public class ContattoDialog extends JDialog {
    
    private JTextField campoNome;
    private JTextField campoCognome;
    private JTextField campoTelefono;
    private JTextField campoVia;
    
    private JButton btnConferma;
    private JButton btnAnnulla;
    
    private boolean confermato = false;
    private Contatto contatto;
    
    /**
     * Costruttore per il dialog
     * @param parent La finestra parent
     * @param titolo Il titolo del dialog
     * @param contattoEsistente Il contatto da modificare (null per nuovo contatto)
     */
    public ContattoDialog(Frame parent, String titolo, Contatto contattoEsistente) {
        super(parent, titolo, true);
        this.contatto = contattoEsistente;
        
        initializeDialog();
        setupEventListeners();
        
        if (contattoEsistente != null) {
            popolaCampi(contattoEsistente);
        }
        
        pack();
        setLocationRelativeTo(parent);
    }
    
    /**
     * Inizializza il dialog
     */
    private void initializeDialog() {
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setResizable(false);
        
        // Panel principale
        JPanel panelPrincipale = new JPanel(new BorderLayout());
        panelPrincipale.setBorder(new EmptyBorder(20, 20, 20, 20));
        
        // Panel dei campi
        JPanel panelCampi = createFieldsPanel();
        panelPrincipale.add(panelCampi, BorderLayout.CENTER);
        
        // Panel dei bottoni
        JPanel panelBottoni = createButtonPanel();
        panelPrincipale.add(panelBottoni, BorderLayout.SOUTH);
        
        add(panelPrincipale);
    }
    
    /**
     * Crea il panel con i campi di input
     */
    private JPanel createFieldsPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        
        // Nome
        gbc.gridx = 0; gbc.gridy = 0; gbc.anchor = GridBagConstraints.EAST;
        panel.add(new JLabel("Nome:"), gbc);
        
        gbc.gridx = 1; gbc.anchor = GridBagConstraints.WEST; gbc.fill = GridBagConstraints.HORIZONTAL;
        campoNome = new JTextField(20);
        campoNome.setFont(new Font("SansSerif", Font.PLAIN, 12));
        panel.add(campoNome, gbc);
        
        // Cognome
        gbc.gridx = 0; gbc.gridy = 1; gbc.anchor = GridBagConstraints.EAST; gbc.fill = GridBagConstraints.NONE;
        panel.add(new JLabel("Cognome:"), gbc);
        
        gbc.gridx = 1; gbc.anchor = GridBagConstraints.WEST; gbc.fill = GridBagConstraints.HORIZONTAL;
        campoCognome = new JTextField(20);
        campoCognome.setFont(new Font("SansSerif", Font.PLAIN, 12));
        panel.add(campoCognome, gbc);
        
        // Telefono
        gbc.gridx = 0; gbc.gridy = 2; gbc.anchor = GridBagConstraints.EAST; gbc.fill = GridBagConstraints.NONE;
        panel.add(new JLabel("Telefono:"), gbc);
        
        gbc.gridx = 1; gbc.anchor = GridBagConstraints.WEST; gbc.fill = GridBagConstraints.HORIZONTAL;
        campoTelefono = new JTextField(20);
        campoTelefono.setFont(new Font("SansSerif", Font.PLAIN, 12));
        panel.add(campoTelefono, gbc);
        
        // Via
        gbc.gridx = 0; gbc.gridy = 3; gbc.anchor = GridBagConstraints.EAST; gbc.fill = GridBagConstraints.NONE;
        panel.add(new JLabel("Via:"), gbc);
        
        gbc.gridx = 1; gbc.anchor = GridBagConstraints.WEST; gbc.fill = GridBagConstraints.HORIZONTAL;
        campoVia = new JTextField(20);
        campoVia.setFont(new Font("SansSerif", Font.PLAIN, 12));
        panel.add(campoVia, gbc);
        
        return panel;
    }
    
    /**
     * Crea il panel dei bottoni
     */
    private JPanel createButtonPanel() {
        JPanel panel = new JPanel(new FlowLayout());
        panel.setBorder(new EmptyBorder(15, 0, 0, 0));
        
        btnConferma = new JButton("Conferma");
        btnConferma.setBackground(new Color(76, 175, 80));
        btnConferma.setForeground(Color.WHITE);
        btnConferma.setFont(new Font("SansSerif", Font.BOLD, 12));
        btnConferma.setPreferredSize(new Dimension(100, 30));
        
        btnAnnulla = new JButton("Annulla");
        btnAnnulla.setBackground(new Color(158, 158, 158));
        btnAnnulla.setForeground(Color.WHITE);
        btnAnnulla.setFont(new Font("SansSerif", Font.BOLD, 12));
        btnAnnulla.setPreferredSize(new Dimension(100, 30));
        
        panel.add(btnConferma);
        panel.add(Box.createHorizontalStrut(10));
        panel.add(btnAnnulla);
        
        return panel;
    }
    
    /**
     * Configura i listener degli eventi
     */
    private void setupEventListeners() {
        btnConferma.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                confermaDialog();
            }
        });
        
        btnAnnulla.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                annullaDialog();
            }
        });
        
        // Enter per confermare
        getRootPane().setDefaultButton(btnConferma);
        
        // ESC per annullare
        KeyStroke escapeKeyStroke = KeyStroke.getKeyStroke("ESCAPE");
        getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(escapeKeyStroke, "ESCAPE");
        getRootPane().getActionMap().put("ESCAPE", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                annullaDialog();
            }
        });
    }
    
    /**
     * Popola i campi con i dati del contatto esistente
     */
    private void popolaCampi(Contatto contatto) {
        campoNome.setText(contatto.getNome());
        campoCognome.setText(contatto.getCognome());
        campoTelefono.setText(contatto.getTelefono());
        campoVia.setText(contatto.getVia());
    }
    
    /**
     * Conferma il dialog
     */
    private void confermaDialog() {
        // Validazione campi
        if (campoNome.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Il campo Nome è obbligatorio!", 
                "Errore di Validazione", JOptionPane.ERROR_MESSAGE);
            campoNome.requestFocus();
            return;
        }
        
        if (campoCognome.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Il campo Cognome è obbligatorio!", 
                "Errore di Validazione", JOptionPane.ERROR_MESSAGE);
            campoCognome.requestFocus();
            return;
        }
        
        if (campoTelefono.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Il campo Telefono è obbligatorio!", 
                "Errore di Validazione", JOptionPane.ERROR_MESSAGE);
            campoTelefono.requestFocus();
            return;
        }
        
        if (campoVia.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Il campo Via è obbligatorio!", 
                "Errore di Validazione", JOptionPane.ERROR_MESSAGE);
            campoVia.requestFocus();
            return;
        }
        
        // Crea il contatto con i dati inseriti
        contatto = new Contatto(
            campoNome.getText().trim(),
            campoCognome.getText().trim(),
            campoTelefono.getText().trim(),
            campoVia.getText().trim()
        );
        
        confermato = true;
        dispose();
    }
    
    /**
     * Annulla il dialog
     */
    private void annullaDialog() {
        confermato = false;
        dispose();
    }
    
    /**
     * Restituisce true se l'utente ha confermato
     */
    public boolean isConfermato() {
        return confermato;
    }
    
    /**
     * Restituisce il contatto creato/modificato
     */
    public Contatto getContatto() {
        return contatto;
    }
}