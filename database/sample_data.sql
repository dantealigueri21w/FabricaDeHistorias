-- Fábrica de Historias — datos semilla que la app carga en su primer arranque.
--
-- Estos son los mismos datos que viven en app/src/main/java/.../data/seed/,
-- transcritos a SQL para poder inspeccionarlos sin abrir el proyecto.
-- La app no ejecuta este archivo: lo siembra Room desde Kotlin al detectar
-- que las tablas estan vacias (FabricaRepository.sembrarSiHaceFalta()).

-- Los 18 animales del taller: costa, sierra y selva, cada uno con un solo
-- caracter. El zorro es CONFIADO y el cuy es ASTUTO a proposito (ver ficha).
INSERT INTO animal (id, nombre, region, caracter) VALUES
  ('pelicano', 'Pelícano', 'COSTA', 'PRESUMIDO'),
  ('lobo_marino', 'Lobo marino', 'COSTA', 'PEREZOSO'),
  ('cangrejo', 'Cangrejo', 'COSTA', 'MIEDOSO'),
  ('gallinazo', 'Gallinazo', 'COSTA', 'EGOISTA'),
  ('piquero', 'Piquero', 'COSTA', 'TRABAJADOR'),
  ('cuculi', 'Cuculí', 'COSTA', 'HUMILDE'),
  ('zorro_andino', 'Zorro andino', 'SIERRA', 'CONFIADO'),
  ('cuy', 'Cuy', 'SIERRA', 'ASTUTO'),
  ('condor', 'Cóndor', 'SIERRA', 'VALIENTE'),
  ('vicuna', 'Vicuña', 'SIERRA', 'PACIENTE'),
  ('sapo', 'Sapo', 'SIERRA', 'APURADO'),
  ('vizcacha', 'Vizcacha', 'SIERRA', 'GENEROSO'),
  ('otorongo', 'Otorongo', 'SELVA', 'EGOISTA'),
  ('guacamayo', 'Guacamayo', 'SELVA', 'PRESUMIDO'),
  ('boa', 'Boa', 'SELVA', 'PACIENTE'),
  ('tortuga_charapa', 'Tortuga charapa', 'SELVA', 'HUMILDE'),
  ('delfin_rosado', 'Delfín rosado', 'SELVA', 'GENEROSO'),
  ('perezoso', 'Perezoso', 'SELVA', 'APURADO');

-- Los 12 visitantes, en el orden en que llegan al taller.
INSERT INTO visitante (id, orden, nombre, descripcion, herramienta, tradicionEvocada, encargoDelVisitante, recibido) VALUES
  ('no_isidro', 1, 'Ño Isidro', 'El que hablaba de animales para decir verdades de la gente.', 'La Rueda de Animales', 'Esopo', 'Dame una fábula donde el más fuerte no sea el que gana.', 0),
  ('fortunato_buhonero', 2, 'Fortunato el Buhonero', 'El buhonero que lo decidía todo echando suertes.', 'El Molino de Ideas', 'Los dados de historia', 'Usa las tres piezas que te toquen, ninguna de más.', 0),
  ('el_bululu', 3, 'El Bululú', 'El cómico de plaza que inventaba la obra completa él solo, en el momento.', 'El Esqueleto', 'El teatro improvisado', 'Cuéntamela completa, con sus seis partes, de un tirón.', 0),
  ('seno_herminia', 4, 'Seño Herminia', 'La maestra que siempre preguntaba "¿y eso qué enseña?".', 'El Espejo de la Moraleja', 'Los fabulistas en español', 'Que tu moraleja diga de verdad lo que pasó, no una frase bonita suelta.', 0),
  ('don_espino', 5, 'Don Espino', 'El caballero flaco del yelmo abollado.', 'La Lente', 'El que veía gigantes donde había molinos', 'Compara algo de tu historia con otra cosa. Solo eso.', 0),
  ('la_killa', 6, 'La Killa', 'La que contaba de noche para que no amaneciera. Nadie la vio nunca de día.', 'El Gancho', 'Las mil y una noches', 'Termínala sin resolverlo todo. Déjame con la duda.', 0),
  ('abuela_remigia', 7, 'Abuela Remigia', 'La abuela de los tres intentos.', 'La Regla de Tres', 'Los cuentos populares', 'Que lo intente tres veces de verdad, no la misma tres veces.', 0),
  ('compadre_cuy', 8, 'Compadre Cuy', 'El cuy que burló al zorro.', 'El Sello del Burlador Burlado', 'Tradición andina del zorro y el cuy', 'Que el más astuto se lleve su merecido.', 0),
  ('tayta_condor', 9, 'Tayta Cóndor', 'El viejo que explicaba por qué el cóndor tiene el cuello pelado. No habla de otra cosa.', 'El Cuaderno del Porqué', 'Los relatos de origen', 'Explícame por qué un animal es como es. Invéntatelo bien.', 0),
  ('dona_puntada', 10, 'Doña Puntada', 'La costurera que unía dos retazos sin que se notara la costura.', 'La Prensa', 'Combinar oraciones (evidencia de Writing Next, tamaño de efecto 0.50)', 'Une dos frases sueltas en una sola, sin que se note la costura.', 0),
  ('dona_eusebia', 11, 'Doña Eusebia', 'La retablista que metía un pueblo entero en una caja.', 'El Fuelle', 'El retablo ayacuchano', 'Métele un detalle a una frase seca: dónde, cuándo o cómo.', 0),
  ('don_laconico', 12, 'Don Lacónico', 'El telegrafista al que le cobraban por palabra.', 'La Criba', 'La revisión, oficio de todo escritor', 'Quítale lo que sobra. Que no repitas ni una palabra de más.', 0);

-- Los 24 encargos de Antuco: 8 faciles, 8 medios, 8 dificiles.
INSERT INTO encargo (id, dificultad, textoAntuco, restriccion) VALUES
  ('F1', 'FACIL', 'Un pescador presumido le apostó al mar que nunca se perdería. Se perdió.', 'Que alguien pierda por su propio orgullo'),
  ('F2', 'FACIL', 'Cerca de un puente colgante, alguien muy apurado se cayó por no mirar dónde pisaba.', 'Que la prisa salga cara'),
  ('F3', 'FACIL', 'Un guacamayo se pasó la tarde entera contando sus plumas a quien quisiera oírlo. Nadie quiso oírlo dos veces.', 'Que el presumido se quede solo'),
  ('F4', 'FACIL', 'En el arenal, un cangrejo miedoso no salió de su hueco ni cuando lo necesitaban de verdad.', 'Que el miedo le cueste algo a otro, no solo a quien tiene miedo'),
  ('F5', 'FACIL', 'Una vicuña paciente esperó tanto que se le adelantó uno todavía más lento.', 'Que hasta la paciencia tenga un límite gracioso'),
  ('F6', 'FACIL', 'Una tortuga humilde encontró un atajo y no se lo contó a nadie. Ni falta que hizo.', 'Que lo humilde gane sin necesidad de presumirlo'),
  ('F7', 'FACIL', 'A un gallinazo le sobraba comida, y a su vecino no le sobraba nada.', 'Que la generosidad (o su falta) tenga una consecuencia visible'),
  ('F8', 'FACIL', 'En la chacra, un sapo apurado sembró antes de tiempo. Cosechó antes de tiempo también: nada.', 'Que apurarse salga más caro que esperar'),
  ('M1', 'MEDIO', 'En el mercado, alguien cambió su sombrero por algo que no valía ni la mitad. Se dio cuenta tarde. O nunca.', 'Un astuto se aprovecha de un confiado, y algo se les tuerce a los dos'),
  ('M2', 'MEDIO', 'Un cóndor cruzó la quebrada por el camino largo, y un sapo por el corto. Llegaron casi juntos.', 'Que el valiente y el miedoso lleguen a resultados parecidos, por caminos distintos'),
  ('M3', 'MEDIO', 'Dos animales subieron a la misma balsa: uno cargó de más por generoso, el otro no cargó nada por egoísta. La balsa casi se hunde igual.', 'Que los dos extremos —dar todo, no dar nada— terminen en el mismo problema'),
  ('M4', 'MEDIO', 'Bajo un tejado de calamina, un zorro le prestó su costal a un cuy. Adivina quién se quedó con el costal.', 'Usa la pareja zorro–cuy tal como es: el astuto se aprovecha del confiado'),
  ('M5', 'MEDIO', 'Una olla se quedó sin dueño en la orilla. Un pelícano dijo que la había encontrado él solo. No era del todo cierto.', 'Que el presumido se atribuya un mérito que no es solo suyo'),
  ('M6', 'MEDIO', 'Una boa ayudó a un otorongo a cruzar un tronco caído, atada con una soga vieja. El otorongo ni le dio las gracias.', 'Que la paciencia se ponga a prueba frente a quien no la merece'),
  ('M7', 'MEDIO', 'Con la neblina bien espesa, una vizcacha compartió su refugio con alguien que ni conocía. Nunca supo si hizo bien.', 'Generosidad hacia un desconocido, sin saber cómo termina — el niño decide'),
  ('M8', 'MEDIO', 'En el arenal, un piquero cargó costales todo el día mientras un lobo marino dormía a la sombra del mismo costal.', 'Que el trabajo de uno sostenga la comodidad del otro, y en algún momento se note'),
  ('D1', 'DIFICIL', 'El cuy volvió a engañar al zorro con el mismo truco de la otra vez. Esta vez el zorro ya lo esperaba.', 'El burlador burlado: que el astuto de siempre se tope con alguien que aprendió su truco'),
  ('D2', 'DIFICIL', 'Nadie en el pueblo sabe por qué el guacamayo grita tan fuerte apenas amanece. Alguien debería inventárselo.', 'Fábula de origen: explica por qué un animal tiene la costumbre que tiene'),
  ('D3', 'DIFICIL', 'El pescador guardó algo en el bolsillo esa noche y nunca contó qué era. Ni yo lo sé.', 'Usa el Gancho: termina sin resolver ese detalle a propósito'),
  ('D4', 'DIFICIL', 'Dicen que el cóndor vuela tan alto porque una vez algo lo asustó desde abajo. No sé si es cierto, pero debería serlo.', 'Usa la Lente (una comparación) y que la moraleja salga de ahí, no que se diga aparte'),
  ('D5', 'DIFICIL', 'Un delfín de río le contó a un lobo marino que su agua era mejor. Ninguno de los dos había visto la del otro.', 'Dos personajes de regiones distintas discuten algo que ninguno conoce del todo — que la fábula resuelva quién tenía razón, o que ninguno la tenga'),
  ('D6', 'DIFICIL', 'Una vizcacha intentó cruzar el mismo derrumbe tres veces, y las tres veces algo distinto la hizo volver.', 'Usa la Regla de Tres: tres intentos de verdad distintos, no el mismo repetido'),
  ('D7', 'DIFICIL', 'Al gallinazo un día le sobró tanto que ya no supo ni qué hacer con lo que no quiso compartir.', 'Que el propio defecto del personaje se vuelva en su contra, sin que nadie más se lo cause'),
  ('D8', 'DIFICIL', 'La última vez que un otorongo perdió contra alguien pequeño, la gente contó mal esa historia durante años. Cuéntala tú, la de verdad.', 'Que la moraleja sorprenda: no "el fuerte siempre gana", sino lo contrario, defendido de verdad por lo que pasa en la fábula');

-- Las 12 insignias del taller.
INSERT INTO insignia (id, nombre, descripcion, obtenida) VALUES
  ('PRIMERA_FABULA', 'Primera Fábula', 'Terminar y leer la primera fábula en el auditorio', 0),
  ('PUERTA_ABIERTA', 'Puerta Abierta', 'Recibir a los 12 visitantes', 0),
  ('CUADERNO_LLENO', 'Cuaderno Lleno', 'Guardar una frase propia en las 12 páginas', 0),
  ('CASA_LLENA', 'Casa Llena', 'Llegar a 20 animales en el auditorio', 0),
  ('OJO_DE_CABALLERO', 'Ojo de Caballero', 'Usar la lente en 5 fábulas distintas', 0),
  ('BUENA_PRENSA', 'Buena Prensa', 'Combinar 10 pares de frases sin deshacerlas', 0),
  ('MANO_LIGERA', 'Mano Ligera', 'Quitar 15 palabras repetidas con la criba', 0),
  ('AIRE_FRESCO', 'Aire Fresco', 'Expandir 10 frases con un detalle nuevo', 0),
  ('REGLA_DE_TRES', 'Regla de Tres', 'Terminar una fábula con los tres intentos completos', 0),
  ('BURLADOR_BURLADO', 'Burlador Burlado', 'Escribir una fábula donde el astuto caiga en su trampa', 0),
  ('ESPEJO_LIMPIO', 'Espejo Limpio', '5 moralejas que corresponden a lo que pasó, al primer intento', 0),
  ('FABULISTA_DE_LA_CASA', 'Fabulista de la Casa', 'Tener 10 fábulas en el Fabulario', 0);

-- Las 12 paginas del Cuaderno del Aprendiz, sembradas vacias: el niño llena
-- fraseElegida cuando decide que frase suya merece guardarse ahi.
INSERT INTO pagina_cuaderno (herramienta, fraseElegida) VALUES
  ('La Rueda de Animales', NULL),
  ('El Molino de Ideas', NULL),
  ('El Esqueleto', NULL),
  ('El Espejo de la Moraleja', NULL),
  ('La Lente', NULL),
  ('El Gancho', NULL),
  ('La Regla de Tres', NULL),
  ('El Sello del Burlador Burlado', NULL),
  ('El Cuaderno del Porqué', NULL),
  ('La Prensa', NULL),
  ('El Fuelle', NULL),
  ('La Criba', NULL);
