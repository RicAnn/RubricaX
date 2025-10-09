# RubricaX - Gestione Contatti con Interfaccia Grafica

Applicazione Java Swing per la gestione di una rubrica telefonica con interfaccia grafica intuitiva e moderna.

## 📋 Caratteristiche

### **Funzionalità Principali**
- ✅ **Aggiungi contatti** - Inserimento di nuovi contatti con validazione campi
- ✅ **Modifica contatti** - Modifica di contatti esistenti
- ✅ **Elimina contatti** - Rimozione con conferma di sicurezza
- ✅ **Visualizza contatti** - Lista ordinata con dettagli completi
- ✅ **Ricerca avanzata** - Ricerca per nome/cognome e numero di telefono
- ✅ **Interfaccia intuitiva** - Design moderno con Swing

### **Campi Contatto**
- **Nome** (obbligatorio)
- **Cognome** (obbligatorio)  
- **Telefono** (obbligatorio)
- **Via/Indirizzo** (obbligatorio)

## 🎨 Interfaccia Grafica

### **Layout Principale**
- **Panel di ricerca** (in alto) - Due campi di ricerca con bottoni dedicati
- **Lista contatti** (sinistra) - Elenco numerato e selezionabile
- **Area dettagli** (destra) - Visualizzazione formattata del contatto selezionato
- **Bottoni azione** (in basso) - Aggiungi, Modifica, Elimina con colori distintivi

### **Dialog di Input**
- Campi di testo per tutti gli attributi del contatto
- Validazione in tempo reale con messaggi di errore
- Tasti di scelta rapida (Enter per confermare, ESC per annullare)

## 🚀 Avvio dell'Applicazione

### **Prerequisiti**
- Java JDK 8 o superiore
- Sistema operativo: Windows, macOS, Linux

### **Compilazione**
```bash
# Compila tutti i file Java
javac -d bin src/*.java
```

### **Esecuzione**
```bash
# Avvia l'applicazione GUI
java -cp bin Main
```

## 📁 Struttura del Progetto

```
RubricaX/
├── .github/
│   └── copilot-instructions.md
├── src/
│   ├── Contatto.java        # Classe modello per un contatto
│   ├── Rubrica.java         # Logica di business per gestione contatti
│   ├── RubricaGUI.java      # Interfaccia grafica principale
│   ├── ContattoDialog.java  # Dialog per aggiunta/modifica contatti
│   └── Main.java            # Entry point dell'applicazione
├── bin/                     # File compilati (.class)
├── lib/                     # Librerie esterne (se necessarie)
└── README.md               # Questo file
```

## 🔧 Architettura del Codice

### **Pattern Utilizzati**
- **MVC (Model-View-Controller)** - Separazione tra logica e presentazione
- **Encapsulation** - Attributi privati con getter/setter
- **Defensive Programming** - Validazione input e gestione errori

### **Classi Principali**

#### `Contatto.java`
- Modello dati per un singolo contatto
- Metodi `equals()`, `hashCode()`, `toString()` implementati
- Validazione tramite getter/setter

#### `Rubrica.java`
- Gestione collezione di contatti (ArrayList)
- Metodi per CRUD operations
- Ricerca avanzata (nome/cognome e telefono)
- Copia difensiva per sicurezza

#### `RubricaGUI.java`
- Interfaccia grafica principale con Swing
- Event handlers per tutte le azioni utente
- Layout manager per organizzazione componenti

#### `ContattoDialog.java`
- Dialog modale per input contatti
- Validazione campi in tempo reale
- Supporto per aggiunta e modifica

## 🎯 Funzionalità Dettagliate

### **Ricerca**
- **Per nome/cognome**: Ricerca parziale case-insensitive
- **Per telefono**: Ricerca per numero completo o parziale
- **Mostra tutti**: Reset ricerca e visualizzazione completa

### **Gestione Contatti**
- **Aggiunta**: Dialog con tutti i campi obbligatori
- **Modifica**: Dialog precompilato con dati esistenti  
- **Eliminazione**: Conferma di sicurezza prima della rimozione
- **Visualizzazione**: Area dettagli formattata

### **Interfaccia Utente**
- **Colori distintivi**: Verde per aggiungere, arancione per modificare, rosso per eliminare
- **Feedback visivo**: Messaggi di successo e errore
- **Keyboard shortcuts**: Enter, ESC, selezione lista con tasti freccia

## 🔍 Come Usare l'Applicazione

1. **Avvio**: Esegui `java -cp bin Main`
2. **Aggiungi contatto**: Clicca "Aggiungi Contatto" e compila i campi
3. **Ricerca**: Usa i campi di ricerca in alto per trovare contatti
4. **Modifica**: Seleziona un contatto dalla lista e clicca "Modifica Contatto"
5. **Elimina**: Seleziona un contatto e clicca "Elimina Contatto" (con conferma)
6. **Visualizza dettagli**: Clicca su un contatto nella lista per vedere i dettagli

## 📝 Note Tecniche

- **Thread Safety**: GUI eseguita nel Event Dispatch Thread
- **Memory Management**: Copia difensiva per evitare side effects  
- **Error Handling**: Gestione eccezioni con messaggi utente
- **Performance**: Ricerca efficiente con algoritmi lineari

## 🔄 Estensioni Future

Possibili miglioramenti da implementare:
- Salvataggio/caricamento da file (JSON, XML, CSV)
- Import/export contatti
- Ordinamento personalizzabile
- Filtri avanzati
- Backup automatico
- Integrazione con database

---

**Creato con ❤️ usando Java Swing**