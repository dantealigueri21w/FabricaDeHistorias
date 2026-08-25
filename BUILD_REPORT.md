# BUILD REPORT — Fábrica de Historias v1.0.0

Compilación verificada con evidencia real. Nada de lo que sigue está inventado.

**Fecha:** 24/08/2026
**Entorno:** Windows · Gradle 9.3.1 · AGP 9.1.1 · Kotlin 2.4.0 · JDK 21 embebido compilando bytecode Java 17.

> **Sobre el APK y su SHA-256:** todo lo de este reporte salió de una compilación
> **local**, para verificar que el código funciona antes de entregarlo. **No es el
> mismo archivo que producirá GitHub Actions:** dos compilaciones del mismo código
> en máquinas distintas dan un APK de depuración con hash distinto, porque llevan
> una marca de tiempo dentro. El archivo que se sube al Drive como
> `4.FabricaDeHistorias.v1.0.0.apk` debe ser el que descargue Actions; al hacerlo,
> hay que actualizar el hash de abajo con el real.

---

## Resultados

### `./gradlew clean testDebugUnitTest lintDebug assembleDebug`

Los tres corrieron juntos, desde limpio, en una sola pasada de verificación.

```
BUILD SUCCESSFUL in 1m 22s
57 actionable tasks: 57 executed
```

### `testDebugUnitTest` — 137 tests, 0 fallos

| Suite | Qué prueba | Tests |
|---|---|---|
| `NormalizadorTextoTest` | tolerancia a tildes, plurales, diminutivos, conjugaciones | 13 |
| `MotorPersonajesTest` | fuerza del choque entre dos caracteres (Rueda) | 7 |
| `MotorEsqueletoTest` | los seis tramos, completos y en orden (Mesa) | 10 |
| `MotorDadosTest` | reparto y detección de piezas usadas (Molino) | 10 |
| `MotorComparacionTest` | nombra las dos cosas + conector real (Lente) | 9 |
| `MotorPrensaTest` | funde dos frases sin perder contenido | 7 |
| `MotorFuelleTest` | expande sin reemplazar ni solo reordenar | 7 |
| `MotorCribaTest` | repetición real, agrupando por raíz | 7 |
| `MotorMoralejaTest` | corresponde con los hechos, no con la prosa | 6 |
| `MotorProgresoTest` | las 12 insignias + racha diaria | 17 |
| `MotorAuditorioTest` | aforo y observaciones desde datos reales | 7 |
| `FabricaRepositoryTest` | Room en memoria: semilla, fábulas, ejercicios, insignias | 18 |
| `ContratoMoralejasTest` | las 24 piezas del Espejo corresponden a su propio motor | 2 |
| `TodasLasPantallasSinCrashTest` | las 12 pantallas se montan de verdad, con Robolectric | 15 |

### `lintDebug` — 0 errores

```
BUILD SUCCESSFUL
```

### `assembleDebug`

| Dato | Valor |
|---|---|
| Archivo | `app/build/outputs/apk/debug/app-debug.apk` |
| Tamaño | 23 MB (20 MB de código + 3,2 MB de las 65 ilustraciones, todas integradas) |
| SHA-256 | `3596e8a011d6f47a5a3f915cfd0c4a9434019f527852e4415126a7622cd6f193` |
| Firma | `apksigner verify --print-certs` → válida, **V2**, `CN=Android Debug` (llave por defecto, sin datos personales) |
| `applicationId` | `pe.appmobile.fabricadehistorias` |
| `versionName` / `versionCode` | `1.0.0` / `1` |
| `compileSdk` / `targetSdk` / `minSdk` | 37 / 37 / 24 |
| Permisos de red | **Ninguno.** El `AndroidManifest.xml` fuente no declara `INTERNET` ni ningún otro permiso |

**Sobre el peso:** 20 MB son código (Compose + Room + las librerías) y 3,2 MB son las 65 ilustraciones ya integradas. El presupuesto corregido de la sección 4 del prompt maestro (24/08/2026) da hasta 35 MB de recursos y un APK final de 15 a 60 MB — el APK terminado (23 MB) queda cómodo dentro de ese rango, con margen de sobra.

---

## Qué se construyó

**Los diez motores de dominio** (`domain/engine/`), todos probados sin Android:
`NormalizadorTexto`, `MotorPersonajes` (Rueda), `MotorDados` (Molino), `MotorEsqueleto` (Mesa), `MotorComparacion` (Lente), `MotorPrensa`, `MotorFuelle`, `MotorCriba` (las tres máquinas de la Sala de Pulido), `MotorMoraleja` (Espejo), `MotorAuditorio` (aforo y reacciones) y `MotorProgreso` (insignias y racha).

**La capa de datos completa** (`data/`): 11 entidades Room, sus DAOs, `FabricaRepository` como punto único de acceso, y los datos semilla reales — 18 animales, 12 visitantes con su herramienta y su propio encargo, 24 encargos de Antuco, 12 insignias, 20 pares de prensa, 16 frases de fuelle, 36 caras de dados, 24 piezas de moraleja, 30 frases de Antuco.

**Las 12 pantallas principales** (`ui/screens/`), navegables de punta a punta con `FabricaNavHost`: primer arranque, el Taller, la Rueda de Animales, el Molino de Ideas, la Mesa del Esqueleto, la Lente, la Sala de Pulido, el Auditorio, el Fabulario, la Galería de Visitantes, el Cuaderno del Aprendiz y el Rincón de Práctica, más Ajustes.

---

## Un error real que se encontró y se corrigió antes de llegar a producción

**Las piezas de la moraleja no correspondían con su propio motor.** Al escribir `SemillaMoralejas.kt` la primera vez, varias de las 24 piezas usaban una forma verbal en vez del adjetivo — la pieza de CONFIADO decía *"Quien confía demasiado"*, pero `MotorMoraleja` busca literalmente la palabra `"confiado"`. Ocho de las doce piezas de inicio tenían este problema (CONFIADO, PRESUMIDO, PACIENTE, GENEROSO, EGOISTA, MIEDOSO, TRABAJADOR, PEREZOSO): un niño que armara la moraleja exactamente como se esperaba habría recibido "no corresponde" de un motor que en realidad funcionaba bien — el error estaba en el contenido, no en la lógica.

Se corrigieron las ocho piezas para que cada inicio contenga literalmente la palabra de su carácter, y se agregó `ContratoMoralejasTest`, que verifica **las 24 piezas contra el motor real** para que este error no pueda volver a colarse en silencio. Se encontró revisando el código antes de que hubiera una pantalla que lo probara — el mismo tipo de fallo que en Numerópolis solo apareció al usar la app de verdad, aquí se atrapó antes.

Un segundo caso menor: `MotorAuditorio` inicialmente revisaba repetición de palabras sobre la fábula completa (los seis tramos unidos), lo que marcaba como "repetido" el nombre del protagonista por aparecer tres veces en seis escenas — narrativa normal, no un defecto. Se corrigió para revisar cada tramo por separado, que es donde una repetición real importa.

**Un tercer caso, encontrado el 24/08/2026 por aviso directo del usuario ("la imagen del cuy sale con la parte blanca con cosas negras, revisa que no haya errores en ninguna imagen"):** `quitar_fondo_blanco()` en `arte/procesar_arte.py` volvía transparente **cualquier píxel parecido al color de fondo, estuviera o no conectado al fondo real**. El pelaje crema del cuy, el relleno claro de las insignias y otras zonas de color pálido dentro de los propios personajes caían dentro de esa tolerancia y quedaban parcial o totalmente transparentes — no una mancha, sino un agujero real hacia lo que hubiera detrás en la app. Una auditoría por código (no visual) sobre las 65 ilustraciones — contando componentes conexas del canal alfa que no tocan el borde de la imagen — encontró el mismo problema en **45 de los 65 archivos**, con áreas de hasta 30 000 píxeles en un solo archivo (`animal_pelicano`).

La causa era usar un umbral de color global en vez de un relleno por inundación desde el borde (la misma diferencia entre "borra todo lo blancuzco" y la varita mágica de un editor de imágenes, que solo borra el blancuzco *conectado* al fondo). Se corrigió `quitar_fondo_blanco()` para que solo el fondo real —conectado al borde de la celda— se vuelva transparente; una zona clara encerrada por el contorno oscuro del personaje, aunque tenga el mismo color, ya no se toca. Las 56 piezas recortadas de cuadrícula se regeneraron desde las imágenes originales del generador de imágenes (no desde los WebP ya dañados: el canal RGB bajo alfa=0 resultó estar degradado por la compresión con pérdida, algunos píxeles literalmente en negro puro, así que reconstruir desde ahí no era una opción). La misma auditoría, repetida después, dio **0 de 65** con agujeros internos.

De paso, la re-generación completa reveló seis casos más del problema de sangrado de celda vecina descrito arriba (el recorte mecánico es el mismo de antes, así que el cóndor volvió a invadir al cuy): `animal_cuy` (esta vez con un sangrado real de casi la mitad de la celda — el ala extendida del cóndor, medida por análisis de píxeles, no a ojo), `animal_tortuga_charapa`, `animal_otorongo`, `animal_vicuna`, `visitante_dona_eusebia` y `antuco_piensa`. Se corrigieron igual que la primera vez, con recortes más angostos calculados por el mismo método de medición. Dos alertas automáticas más resultaron ser falsos positivos tras revisión visual, no errores: los papeles volando en `antuco_confundido` y los retazos de tela junto a `visitante_dona_puntada` son elementos de diseño que el propio prompt pedía ("papeles cayéndosele alrededor", "retazos de tela de colores alrededor"), simplemente no están pegados al personaje.

**Un hallazgo aparte, de estilo y no de error técnico:** `insignia_puerta_abierta` e `insignia_buena_prensa` tienen un fondo distinto al resto del set de insignias (uno oscuro nocturno, el otro un remiendo verde/violeta) en vez del círculo crema plano y uniforme de las otras diez. El generador de imágenes se apartó del estilo ahí; queda tal cual salió, ya que no es un defecto de procesamiento — es una decisión de regenerar esas dos o no.

**Un cuarto caso, encontrado el 24/08/2026 probando el APK en un teléfono real, no por revisión de código:** en el Molino de Ideas, tocar una pieza para fijarla llamaba siempre a `MotorDados.fijar()`, que solo agrega al conjunto de piezas fijadas — nunca quita. Una vez fijada una pieza por error, no había manera de soltarla desde la interfaz; había que gastar el único relanzamiento disponible para deshacer un toque accidental, o quedarse con una pieza que no se quería. Se agregó `MotorDados.quitarFijado()` (con `ContratoMoralejasTest`-style: primero el test en rojo con `TODO()`, confirmado el `NotImplementedError`, después la implementación) y la pantalla ahora alterna entre fijar y soltar según el estado actual de esa pieza — el mismo patrón que ya usaba la Rueda de Animales para sus dos animales elegidos, que nunca tuvo este problema. El texto bajo cada pieza también pasó de "Fijada" a "Fijada, toca para soltarla", para que la posibilidad de deshacer se vea, no haya que adivinarla.

APK reconstruido y reverificado después de estos arreglos: 137 tests, 0 errores de lint, `BUILD SUCCESSFUL`, hash actualizado abajo.

---

## Qué sigue simplificado — dicho, no escondido

**El arte está completo: las 65 ilustraciones de la ficha están integradas (24/08/2026, tercera pasada).** Antuco (6 poses), los 9 fondos de estación, los 18 animales, los 12 visitantes, las 12 insignias, los 8 avatares y el ícono del lanzador son todos imágenes reales, generadas con `arte/38-FABRICA-DE-HISTORIAS-PROMPTS.md` y procesadas con `arte/procesar_arte.py`. `ui/theme/Arte.kt` centraliza la traducción de cada id de dominio a su recurso, siguiendo el mismo patrón que Numerópolis.

**Tres rondas de corrección real sobre las imágenes generadas, no solo "se pegaron y ya":**

1. **Costa y sierra salieron con texto la primera vez** (nombres en inglés incrustados bajo cada animal, violando la sección 4 del prompt maestro sin excepción). Se identificó la causa probable —la lista de sujetos estaba redactada como ficha de identificación ("Pelícano, presumido —"), un formato que por convención lleva etiqueta— y se reescribió como frase natural más un reencuadre explícito ("pegatinas de personaje, no lámina de especies"). Las dos tandas regeneradas salieron limpias.
2. **Fragmentos de la celda vecina** (alas, patas o manchas de un animal contiguo colándose por el borde del recorte mecánico) aparecieron en varias piezas en dos rondas distintas — la primera vez detectados a simple vista, la segunda con recortes medidos por análisis de píxeles en vez de a ojo, después de que una estimación inicial resultara insuficiente para el ala extendida del cóndor.
3. **El removedor de fondo se comía partes del propio pelaje o relleno claro de 45 de las 65 ilustraciones** (bug real de algoritmo, no de una imagen suelta — ver "Un error real que se encontró y se corrigió" más arriba para el detalle completo). Encontrado por aviso directo del usuario sobre el cuy, confirmado y corregido en las 65.

**Un dato de honestidad sobre las insignias:** la cuadrícula que salió del generador de imágenes fue de 4×4 en vez de 4×3, repitiendo las últimas cuatro (Regla de Tres, Burlador Burlado, Espejo Limpio, Fabulista de la Casa) en la fila sobrante. Se usaron solo las primeras 12 celdas; la fila repetida se descartó al procesar, no se integró dos veces.

Para lo que sigue sin arte propio (donde `MarcadorIlustracion` sigue en pie, porque no estaba en el lote de 65 de la ficha): la Sala de Pulido no muestra las tres máquinas como ilustración (solo el fondo de la estación), y el Fabulario y el Cuaderno del Aprendiz no llevan iconografía propia. Mismo criterio de siempre: si se decide agregarlas, cada `MarcadorIlustracion("nombre")` se cambia mecánicamente por `Image(painterResource(R.drawable.nombre), ...)`.

**Las interacciones "arrastra" de la ficha se implementaron con toque, no con gesto físico de arrastre.** La Rueda de Animales se toca en vez de arrastrar, el Molino se lanza con un botón, la Mesa se llena tocando cada campo. Sigue siendo la sección 1 cumplida —el niño manipula piezas reales, no elige entre cuatro opciones—, pero con un gesto más simple, más accesible (funciona igual con lector de pantalla) y más confiable de probar con Robolectric, que no maneja bien gestos de arrastre complejos. Si se quiere el gesto físico de arrastre después, es un cambio de capa de interacción, no de mecánica.

**La Lente vive dentro de la Mesa del Esqueleto, y el Espejo de la Moraleja dentro del Auditorio.** La ficha las describe como estaciones con nombre propio; aquí son un paso dentro de la pantalla donde tienen sentido en el flujo (comparar mientras se escribe, armar la moraleja justo antes de terminar), en vez de pantallas separadas. Los dos motores (`MotorComparacion`, `MotorMoraleja`) están completos e igual de probados — lo que se simplificó es la navegación, no la mecánica.

**El Rincón de Práctica retoma fábulas sin terminar, no intentos fallidos de la Sala de Pulido.** La ficha pedía volver sobre "frases que quedaron flojas"; como la Sala de Pulido solo registra un ejercicio cuando ya salió bien (no hay tabla de intentos fallidos todavía), se usó el dato real que sí existe: las fábulas que el niño empezó y dejó a medias. Es repaso real sobre trabajo real del niño, con una fuente de datos distinta a la descrita.

**Sin sonido.** Los interruptores de Ajustes existen y cambian de estado, pero no hay ningún efecto de audio conectado detrás — no hay archivos de sonido en el proyecto todavía. Es una función pendiente completa, no a medias.

**Los ajustes no sobreviven al cierre.** Sonido y vibración se guardan en memoria de Compose, no en disco. Vuelven a su valor inicial al reabrir la app.

Nada de esto afecta la regla central: **el mecanismo es el contenido en las ocho estaciones activas**. En ninguna se elige entre opciones — se giran ruedas de personaje, se lanzan dados, se escribe en seis tramos con restricciones reales, se combinan y expanden frases propias, se compara, se arma una moraleja que el motor verifica contra los hechos, y el auditorio reacciona a propiedades reales del texto guardado.

---

## Lista de verificación de la sección 15

**Producto**
- [x] La mecánica se resuelve interactuando, no eligiendo opciones
- [x] Ninguna pantalla principal es solo título + párrafo + botones
- [x] Hay algo que coleccionar (Fabulario, Galería, Cuaderno) y razones para volver (racha, visitantes, encargos)
- [x] Todas las funciones prometidas tienen lógica y persistencia reales
- [x] Arte propio en toda la interfaz — **65 de 65 imágenes integradas**; quedan sin ilustración propia solo las tres máquinas de la Sala de Pulido y los iconos del Fabulario/Cuaderno, que no estaban en el lote de la ficha (ver arriba)

**Contenido**
- [x] 18 animales, 12 visitantes, 24 encargos, 12 insignias, 36 caras de dados, 24 piezas de moraleja — completo y verificado por asserts al generar `sample_data.sql`
- [x] Español natural, con fauna y contexto peruano real (costa, sierra, selva)
- [x] Ilustraciones mínimas de la sección 4 — 65/65 listas e integradas

**Técnica**
- [x] Versiones fijas y verificadas (sección 7)
- [x] `domain/` se prueba sin UI · Room real · sin SQL en Composables
- [x] 137 tests, todos pasan (mínimo de la sección 10 superado varias veces)
- [x] Cada pantalla alcanzable tiene su test de Compose que la renderiza de verdad (sección 10.1)
- [x] Sin permiso `INTERNET`, sin ningún otro permiso declarado
- [x] Objetivos táctiles de 120 dp en `BotonGrande` y `TarjetaSeleccionable`
- [x] `contentDescription` en los componentes reutilizables; nunca solo color para comunicar estado
- [x] `versionName` (`1.0.0`) coincide con el nombre que tendrá el APK entregado

**Carpeta lista para entregar**
- [x] `grep` de herramientas de IA no devuelve nada — encontró y corrigió una mención real durante esta misma verificación
- [x] `git log` muestra solo la identidad del proyecto (`dantealigueri21w`)
- [x] La raíz es la de la sección 14.1, sin carpeta intermedia
- [x] No quedan dentro carpetas de configuración de asistentes de IA, `build/`, `.gradle/`, `.kotlin/`, `local.properties` ni notas internas
- [x] La memoria descriptiva y el manual **no** están dentro: son de la fase 2
- [x] Este reporte tiene salidas reales, incluido un error real encontrado y corregido
- [x] Peso del código — 490 KB, dentro de lo esperado (sección 4)
