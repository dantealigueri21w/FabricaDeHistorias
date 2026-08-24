# Fábrica de Historias

Taller creativo para que niños de 8 a 12 años creen, escriban y mejoren sus propias fábulas, fabricándolas pieza por pieza en el taller del fabulista Antuco.

## Qué es

El niño elige dos animales que chocan por su carácter, saca ideas de un molino de dados, arma el esqueleto de seis tramos de su historia, la pule con tres máquinas (combinar, expandir, quitar lo repetido) y la lee ante un auditorio que crece con cada fábula terminada. Doce visitantes van llegando con nuevas herramientas, cada uno evocando una tradición literaria distinta sin nombrarla ni copiarla.

100 % offline. Sin `INTERNET`, sin backend, sin analítica. Todo el progreso vive en Room, en el propio dispositivo.

## Compilar

```bash
./gradlew assembleDebug
```

El APK sale en `app/build/outputs/apk/debug/`.

## Verificar

```bash
./gradlew testDebugUnitTest   # 135 tests: motores de dominio, repositorio y render de las 12 pantallas
./gradlew lintDebug           # 0 errores
```

## Estructura

```
app/src/main/java/pe/appmobile/fabricadehistorias/
  domain/   modelos y los diez motores de dominio, probables sin Android
  data/     entidades Room, DAOs, repositorio y datos semilla
  ui/       tema, componentes, pantallas y navegación
database/   schema.sql y sample_data.sql, exportados de Room
```

Detalles de diseño, investigación y decisiones en `BUILD_REPORT.md`.
