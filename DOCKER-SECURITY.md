# Docker Security Guide for RubricaX

## 🔒 Sicurezza Docker Implementata

### **Vulnerabilità Risolta**
- ❌ **OLD**: `openjdk:21-jdk-slim` - Vulnerabilità alta, non aggiornato da 2 anni
- ✅ **NEW**: `eclipse-temurin:21-jdk-jammy` - Mantenuto da Eclipse Foundation, aggiornamenti regolari

### **Miglioramenti di Sicurezza**

#### 1. **Immagine Base Sicura**
```dockerfile
FROM eclipse-temurin:21-jdk-jammy
```
- Eclipse Temurin è la distribuzione OpenJDK ufficiale raccomandata
- Aggiornamenti di sicurezza regolari
- Supporto a lungo termine (LTS)

#### 2. **Utente Non-Root**
```dockerfile
RUN groupadd -r appuser && useradd -r -g appuser appuser
USER appuser
```
- Esecuzione con privilegi limitati
- Riduce superficie di attacco

#### 3. **Cleanup Sistema**
```dockerfile
&& rm -rf /var/lib/apt/lists/* \
&& apt-get clean
```
- Rimuove cache e file temporanei
- Riduce dimensione immagine
- Elimina potenziali vulnerabilità residue

#### 4. **Health Check**
```dockerfile
HEALTHCHECK --interval=30s --timeout=3s --start-period=5s --retries=3
```
- Monitoraggio automatico dello stato applicazione
- Riavvio automatico in caso di problemi

#### 5. **Alternative Ultra-Sicura (Distroless)**
- File: `Dockerfile.secure`
- Immagine Google Distroless
- Nessun shell, package manager, o utility non necessarie
- Minima superficie di attacco possibile

## 🚀 Utilizzo

### **Build Standard (con GUI)**
```bash
docker build -t rubricax:latest .
```

### **Build Ultra-Sicuro (headless)**
```bash
docker build -f Dockerfile.secure -t rubricax:secure .
```

### **Docker Compose**
```bash
docker-compose up -d
```

## 🔍 Scan Sicurezza

Per verificare la sicurezza delle immagini:

```bash
# Scan con Docker
docker scout quickview rubricax:latest

# Scan con Trivy (se installato)
trivy image rubricax:latest

# Scan con Snyk (se installato)
snyk container test rubricax:latest
```

## 📊 Comparazione Sicurezza

| Aspetto | openjdk:21-jdk-slim | eclipse-temurin:21-jdk-jammy | distroless:java21 |
|---------|-------------------|------------------------------|-------------------|
| Vulnerabilità | ❌ Alta | ✅ Bassa | ✅ Minima |
| Aggiornamenti | ❌ Rari | ✅ Regolari | ✅ Automatici |
| Dimensione | ~226MB | ~300MB | ~200MB |
| Superficie attacco | ❌ Grande | ⚠️ Media | ✅ Minima |
| Shell access | ✅ Sì | ✅ Sì | ❌ No |
| GUI Support | ✅ Configurabile | ✅ Completo | ❌ No |

## 🛡️ Best Practices Implementate

1. **Multi-stage build** per ridurre size finale
2. **Non-root user** per sicurezza runtime
3. **Health checks** per monitoring
4. **Minimal dependencies** solo quelle necessarie
5. **Regular base image** con supporto LTS
6. **Clean package cache** per ridurre vulnerabilità

## 🔄 Aggiornamenti Regolari

Per mantenere la sicurezza:
```bash
# Update base image
docker pull eclipse-temurin:21-jdk-jammy

# Rebuild
docker build --no-cache -t rubricax:latest .
```