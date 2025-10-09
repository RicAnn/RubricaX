import java.util.ArrayList;
import java.util.List;

/**
 * Classe che gestisce una rubrica di contatti
 */
public class Rubrica {
    
    private ArrayList<Contatto> contatti;
    
    /**
     * Costruttore della rubrica
     */
    public Rubrica() {
        this.contatti = new ArrayList<>();
    }
    
    /**
     * Aggiunge un contatto alla rubrica
     * @param contatto Il contatto da aggiungere
     * @return true se il contatto è stato aggiunto con successo
     */
    public boolean aggiungiContatto(Contatto contatto) {
        if (contatto != null) {
            contatti.add(contatto);
            return true;
        }
        return false;
    }
    
    /**
     * Rimuove un contatto dalla rubrica tramite indice
     * @param indice L'indice del contatto da rimuovere (0-based)
     * @return true se il contatto è stato rimosso con successo
     */
    public boolean rimuoviContatto(int indice) {
        if (indice >= 0 && indice < contatti.size()) {
            contatti.remove(indice);
            return true;
        }
        return false;
    }
    
    /**
     * Rimuove un contatto dalla rubrica tramite oggetto
     * @param contatto Il contatto da rimuovere
     * @return true se il contatto è stato rimosso con successo
     */
    public boolean rimuoviContatto(Contatto contatto) {
        return contatti.remove(contatto);
    }
    
    /**
     * Cerca contatti per nome o cognome
     * @param termine Il termine di ricerca
     * @return Lista dei contatti trovati
     */
    public List<Contatto> cercaContatti(String termine) {
        List<Contatto> risultati = new ArrayList<>();
        
        if (termine == null || termine.trim().isEmpty()) {
            return risultati;
        }
        
        String termineRicerca = termine.toLowerCase().trim();
        
        for (Contatto contatto : contatti) {
            if (contatto.getNome().toLowerCase().contains(termineRicerca) || 
                contatto.getCognome().toLowerCase().contains(termineRicerca)) {
                risultati.add(contatto);
            }
        }
        
        return risultati;
    }
    
    /**
     * Cerca contatti per numero di telefono
     * @param telefono Il numero di telefono da cercare
     * @return Lista dei contatti trovati
     */
    public List<Contatto> cercaPerTelefono(String telefono) {
        List<Contatto> risultati = new ArrayList<>();
        
        if (telefono == null || telefono.trim().isEmpty()) {
            return risultati;
        }
        
        for (Contatto contatto : contatti) {
            if (contatto.getTelefono().contains(telefono)) {
                risultati.add(contatto);
            }
        }
        
        return risultati;
    }
    
    /**
     * Ottiene un contatto tramite indice
     * @param indice L'indice del contatto (0-based)
     * @return Il contatto all'indice specificato, null se indice non valido
     */
    public Contatto getContatto(int indice) {
        if (indice >= 0 && indice < contatti.size()) {
            return contatti.get(indice);
        }
        return null;
    }
    
    /**
     * Ottiene tutti i contatti della rubrica
     * @return Lista di tutti i contatti (copia difensiva)
     */
    public List<Contatto> getTuttiContatti() {
        return new ArrayList<>(contatti);
    }
    
    /**
     * Modifica un contatto esistente
     * @param indice L'indice del contatto da modificare
     * @param nuovoContatto Il nuovo contatto
     * @return true se la modifica è avvenuta con successo
     */
    public boolean modificaContatto(int indice, Contatto nuovoContatto) {
        if (indice >= 0 && indice < contatti.size() && nuovoContatto != null) {
            contatti.set(indice, nuovoContatto);
            return true;
        }
        return false;
    }
    
    /**
     * Restituisce il numero di contatti nella rubrica
     * @return Il numero di contatti
     */
    public int size() {
        return contatti.size();
    }
    
    /**
     * Verifica se la rubrica è vuota
     * @return true se la rubrica è vuota
     */
    public boolean isEmpty() {
        return contatti.isEmpty();
    }
    
    /**
     * Svuota completamente la rubrica
     */
    public void svuotaRubrica() {
        contatti.clear();
    }
    
    /**
     * Verifica se un contatto è presente nella rubrica
     * @param contatto Il contatto da cercare
     * @return true se il contatto è presente
     */
    public boolean contieneContatto(Contatto contatto) {
        return contatti.contains(contatto);
    }
    
    /**
     * Trova l'indice di un contatto nella rubrica
     * @param contatto Il contatto da cercare
     * @return L'indice del contatto, -1 se non trovato
     */
    public int getIndiceContatto(Contatto contatto) {
        return contatti.indexOf(contatto);
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Rubrica con ").append(contatti.size()).append(" contatti:\n");
        
        for (int i = 0; i < contatti.size(); i++) {
            sb.append((i + 1)).append(". ").append(contatti.get(i)).append("\n");
        }
        
        return sb.toString();
    }
}