/**
 * Classe che rappresenta un contatto della rubrica
 */
public class Contatto {
    
    // Attributi della classe
    private String nome;
    private String cognome;
    private String telefono;
    private String via;
    
    // Costruttore vuoto
    public Contatto() {
    }
    
    // Costruttore con parametri
    public Contatto(String nome, String cognome, String telefono, String via) {
        this.nome = nome;
        this.cognome = cognome;
        this.telefono = telefono;
        this.via = via;
    }
    
    // Getter per nome
    public String getNome() {
        return nome;
    }
    
    // Setter per nome
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    // Getter per cognome
    public String getCognome() {
        return cognome;
    }
    
    // Setter per cognome
    public void setCognome(String cognome) {
        this.cognome = cognome;
    }
    
    // Getter per telefono
    public String getTelefono() {
        return telefono;
    }
    
    // Setter per telefono
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    
    // Getter per via
    public String getVia() {
        return via;
    }
    
    // Setter per via
    public void setVia(String via) {
        this.via = via;
    }
    
    // Metodo toString per rappresentare il contatto come stringa
    @Override
    public String toString() {
        return "Contatto{" +
                "nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                ", telefono='" + telefono + '\'' +
                ", via='" + via + '\'' +
                '}';
    }
    
    // Metodo equals per confrontare due contatti
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        
        Contatto contatto = (Contatto) obj;
        
        if (nome != null ? !nome.equals(contatto.nome) : contatto.nome != null) return false;
        if (cognome != null ? !cognome.equals(contatto.cognome) : contatto.cognome != null) return false;
        if (telefono != null ? !telefono.equals(contatto.telefono) : contatto.telefono != null) return false;
        return via != null ? via.equals(contatto.via) : contatto.via == null;
    }
    
    // Metodo hashCode
    @Override
    public int hashCode() {
        int result = nome != null ? nome.hashCode() : 0;
        result = 31 * result + (cognome != null ? cognome.hashCode() : 0);
        result = 31 * result + (telefono != null ? telefono.hashCode() : 0);
        result = 31 * result + (via != null ? via.hashCode() : 0);
        return result;
    }
}