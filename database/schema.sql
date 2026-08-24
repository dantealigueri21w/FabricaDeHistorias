-- Esquema real de Room, exportado desde app/schemas/…/AppDatabase/1.json.
-- 11 tablas, tal como las genera Room a partir de las entidades de data/local/entity/.

CREATE TABLE IF NOT EXISTS `perfil` (`id` INTEGER NOT NULL, `alias` TEXT NOT NULL, `avatarId` INTEGER NOT NULL, PRIMARY KEY(`id`));

CREATE TABLE IF NOT EXISTS `animal` (`id` TEXT NOT NULL, `nombre` TEXT NOT NULL, `region` TEXT NOT NULL, `caracter` TEXT NOT NULL, PRIMARY KEY(`id`));

CREATE TABLE IF NOT EXISTS `visitante` (`id` TEXT NOT NULL, `nombre` TEXT NOT NULL, `descripcion` TEXT NOT NULL, `herramienta` TEXT NOT NULL, `tradicionEvocada` TEXT NOT NULL, `encargoDelVisitante` TEXT NOT NULL, `orden` INTEGER NOT NULL, `recibido` INTEGER NOT NULL, `fechaRecibidoEpochMillis` INTEGER, PRIMARY KEY(`id`));

CREATE TABLE IF NOT EXISTS `encargo` (`id` TEXT NOT NULL, `dificultad` TEXT NOT NULL, `textoAntuco` TEXT NOT NULL, `restriccion` TEXT NOT NULL, PRIMARY KEY(`id`));

CREATE TABLE IF NOT EXISTS `fabula` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `titulo` TEXT NOT NULL, `animalAId` TEXT NOT NULL, `animalBId` TEXT NOT NULL, `encargoId` TEXT, `terminada` INTEGER NOT NULL, `fechaCreacionEpochMillis` INTEGER NOT NULL, `fechaTerminadaEpochMillis` INTEGER, `usoLente` INTEGER NOT NULL, `cumplioReglaDeTres` INTEGER NOT NULL, `esBurladorBurlado` INTEGER NOT NULL, FOREIGN KEY(`animalAId`) REFERENCES `animal`(`id`) ON UPDATE NO ACTION ON DELETE NO ACTION , FOREIGN KEY(`animalBId`) REFERENCES `animal`(`id`) ON UPDATE NO ACTION ON DELETE NO ACTION , FOREIGN KEY(`encargoId`) REFERENCES `encargo`(`id`) ON UPDATE NO ACTION ON DELETE SET NULL );

CREATE TABLE IF NOT EXISTS `tramo` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `fabulaId` INTEGER NOT NULL, `tipoTramo` TEXT NOT NULL, `texto` TEXT NOT NULL, FOREIGN KEY(`fabulaId`) REFERENCES `fabula`(`id`) ON UPDATE NO ACTION ON DELETE CASCADE );

CREATE TABLE IF NOT EXISTS `ejercicio` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `tipo` TEXT NOT NULL, `textoResultado` TEXT NOT NULL, `confirmado` INTEGER NOT NULL, `cantidad` INTEGER NOT NULL, `fechaEpochMillis` INTEGER NOT NULL);

CREATE TABLE IF NOT EXISTS `pagina_cuaderno` (`herramienta` TEXT NOT NULL, `fraseElegida` TEXT, `fechaGuardadaEpochMillis` INTEGER, PRIMARY KEY(`herramienta`));

CREATE TABLE IF NOT EXISTS `intento` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `fabulaId` INTEGER NOT NULL, `tipo` TEXT NOT NULL, `fueCorrecto` INTEGER NOT NULL, `cantidad` INTEGER NOT NULL, `fechaEpochMillis` INTEGER NOT NULL, FOREIGN KEY(`fabulaId`) REFERENCES `fabula`(`id`) ON UPDATE NO ACTION ON DELETE CASCADE );

CREATE TABLE IF NOT EXISTS `insignia` (`id` TEXT NOT NULL, `nombre` TEXT NOT NULL, `descripcion` TEXT NOT NULL, `obtenida` INTEGER NOT NULL, `fechaObtencionEpochMillis` INTEGER, PRIMARY KEY(`id`));

CREATE TABLE IF NOT EXISTS `racha` (`id` INTEGER NOT NULL, `rachaActual` INTEGER NOT NULL, `ultimaFechaJuegoEpochDay` INTEGER NOT NULL, PRIMARY KEY(`id`));

CREATE INDEX IF NOT EXISTS `index_fabula_animalAId` ON `fabula` (`animalAId`);
CREATE INDEX IF NOT EXISTS `index_fabula_animalBId` ON `fabula` (`animalBId`);
CREATE INDEX IF NOT EXISTS `index_fabula_encargoId` ON `fabula` (`encargoId`);
CREATE INDEX IF NOT EXISTS `index_tramo_fabulaId` ON `tramo` (`fabulaId`);
CREATE INDEX IF NOT EXISTS `index_intento_fabulaId` ON `intento` (`fabulaId`);
