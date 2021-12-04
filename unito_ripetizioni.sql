-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Creato il: Apr 06, 2021 alle 21:48
-- Versione del server: 10.4.18-MariaDB
-- Versione PHP: 8.0.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `unito_ripetizioni`
--

-- --------------------------------------------------------

--
-- Struttura della tabella `corso`
--

CREATE TABLE `corso` (
  `titoloCorso` varchar(50) NOT NULL,
  `corsoAttivo` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dump dei dati per la tabella `corso`
--

INSERT INTO `corso` (`titoloCorso`, `corsoAttivo`) VALUES
('Fisica', 'True'),
('Inglese', 'True'),
('italiano', 'False'),
('Matematica', 'True'),
('nnn', 'False'),
('Programmazione', 'True'),
('test', 'False');

-- --------------------------------------------------------

--
-- Struttura della tabella `docente`
--

CREATE TABLE `docente` (
  `idDocente` int(11) NOT NULL,
  `nome` varchar(25) NOT NULL,
  `cognome` varchar(25) NOT NULL,
  `docenteAttivo` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dump dei dati per la tabella `docente`
--

INSERT INTO `docente` (`idDocente`, `nome`, `cognome`, `docenteAttivo`) VALUES
(10, 'Luca', 'Verdi', 'True'),
(11, 'Luigi', 'Neri', 'True'),
(12, 'Francesca', 'Pitti', 'True'),
(13, 'test', 'test', 'False'),
(14, 'test', 'test', 'False');

-- --------------------------------------------------------

--
-- Struttura della tabella `insegnamento`
--

CREATE TABLE `insegnamento` (
  `idInsegnamento` int(10) NOT NULL,
  `idDocente` int(11) NOT NULL,
  `idCorso` varchar(30) NOT NULL,
  `insegnamentoAttivo` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dump dei dati per la tabella `insegnamento`
--

INSERT INTO `insegnamento` (`idInsegnamento`, `idDocente`, `idCorso`, `insegnamentoAttivo`) VALUES
(1044, 10, 'Fisica', 'True'),
(1045, 10, 'Matematica', 'True'),
(1048, 11, 'Programmazione', 'True'),
(1049, 12, 'Inglese', 'False');

-- --------------------------------------------------------

--
-- Struttura della tabella `prenotazione`
--

CREATE TABLE `prenotazione` (
  `idPrenotazione` int(10) NOT NULL,
  `idUtente` varchar(10) NOT NULL,
  `idDocente` int(11) NOT NULL,
  `idCorso` varchar(30) NOT NULL,
  `Orario` varchar(15) NOT NULL,
  `Giorno` varchar(15) NOT NULL,
  `Stato` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dump dei dati per la tabella `prenotazione`
--

INSERT INTO `prenotazione` (`idPrenotazione`, `idUtente`, `idDocente`, `idCorso`, `Orario`, `Giorno`, `Stato`) VALUES
(1635, 'Federico', 10, 'Fisica', '15-16', 'Lunedì', 'Disdetta'),
(1636, 'Federico', 10, 'Fisica', '16-17', 'Lunedì', 'Svolta'),
(1637, 'null', 10, 'Fisica', '17-18', 'Lunedì', 'Libera'),
(1638, 'null', 10, 'Fisica', '18-19', 'Lunedì', 'Libera'),
(1639, 'null', 10, 'Fisica', '15-16', 'Martedì', 'Libera'),
(1640, 'null', 10, 'Fisica', '16-17', 'Martedì', 'Libera'),
(1641, 'null', 10, 'Fisica', '17-18', 'Martedì', 'Libera'),
(1642, 'null', 10, 'Fisica', '18-19', 'Martedì', 'Libera'),
(1643, 'null', 10, 'Fisica', '15-16', 'Mercoledì', 'Libera'),
(1644, 'null', 10, 'Fisica', '16-17', 'Mercoledì', 'Libera'),
(1645, 'null', 10, 'Fisica', '17-18', 'Mercoledì', 'Libera'),
(1646, 'null', 10, 'Fisica', '18-19', 'Mercoledì', 'Libera'),
(1647, 'null', 10, 'Fisica', '15-16', 'Giovedì', 'Libera'),
(1648, 'null', 10, 'Fisica', '16-17', 'Giovedì', 'Libera'),
(1649, 'null', 10, 'Fisica', '17-18', 'Giovedì', 'Libera'),
(1650, 'null', 10, 'Fisica', '18-19', 'Giovedì', 'Libera'),
(1651, 'null', 10, 'Fisica', '15-16', 'Venerdì', 'Libera'),
(1652, 'null', 10, 'Fisica', '16-17', 'Venerdì', 'Libera'),
(1653, 'null', 10, 'Fisica', '17-18', 'Venerdì', 'Libera'),
(1654, 'null', 10, 'Fisica', '18-19', 'Venerdì', 'Libera'),
(1655, 'null', 10, 'Matematica', '15-16', 'Lunedì', 'Libera'),
(1656, 'null', 10, 'Matematica', '16-17', 'Lunedì', 'Libera'),
(1657, 'null', 10, 'Matematica', '17-18', 'Lunedì', 'Libera'),
(1658, 'null', 10, 'Matematica', '18-19', 'Lunedì', 'Libera'),
(1659, 'null', 10, 'Matematica', '15-16', 'Martedì', 'Libera'),
(1660, 'null', 10, 'Matematica', '16-17', 'Martedì', 'Libera'),
(1661, 'null', 10, 'Matematica', '17-18', 'Martedì', 'Libera'),
(1662, 'null', 10, 'Matematica', '18-19', 'Martedì', 'Libera'),
(1663, 'null', 10, 'Matematica', '15-16', 'Mercoledì', 'Libera'),
(1664, 'null', 10, 'Matematica', '16-17', 'Mercoledì', 'Libera'),
(1665, 'null', 10, 'Matematica', '17-18', 'Mercoledì', 'Libera'),
(1666, 'null', 10, 'Matematica', '18-19', 'Mercoledì', 'Libera'),
(1667, 'null', 10, 'Matematica', '15-16', 'Giovedì', 'Libera'),
(1668, 'null', 10, 'Matematica', '16-17', 'Giovedì', 'Libera'),
(1669, 'null', 10, 'Matematica', '17-18', 'Giovedì', 'Libera'),
(1670, 'null', 10, 'Matematica', '18-19', 'Giovedì', 'Libera'),
(1671, 'null', 10, 'Matematica', '15-16', 'Venerdì', 'Libera'),
(1672, 'null', 10, 'Matematica', '16-17', 'Venerdì', 'Libera'),
(1673, 'null', 10, 'Matematica', '17-18', 'Venerdì', 'Libera'),
(1674, 'null', 10, 'Matematica', '18-19', 'Venerdì', 'Libera'),
(1675, 'null', 11, 'Programmazione', '15-16', 'Lunedì', 'Libera'),
(1676, 'null', 11, 'Programmazione', '16-17', 'Lunedì', 'Libera'),
(1677, 'null', 11, 'Programmazione', '17-18', 'Lunedì', 'Libera'),
(1678, 'null', 11, 'Programmazione', '18-19', 'Lunedì', 'Libera'),
(1679, 'null', 11, 'Programmazione', '15-16', 'Martedì', 'Libera'),
(1680, 'null', 11, 'Programmazione', '16-17', 'Martedì', 'Libera'),
(1681, 'null', 11, 'Programmazione', '17-18', 'Martedì', 'Libera'),
(1682, 'null', 11, 'Programmazione', '18-19', 'Martedì', 'Libera'),
(1683, 'null', 11, 'Programmazione', '15-16', 'Mercoledì', 'Libera'),
(1684, 'null', 11, 'Programmazione', '16-17', 'Mercoledì', 'Libera'),
(1685, 'null', 11, 'Programmazione', '17-18', 'Mercoledì', 'Libera'),
(1686, 'null', 11, 'Programmazione', '18-19', 'Mercoledì', 'Libera'),
(1687, 'null', 11, 'Programmazione', '15-16', 'Giovedì', 'Libera'),
(1688, 'null', 11, 'Programmazione', '16-17', 'Giovedì', 'Libera'),
(1689, 'null', 11, 'Programmazione', '17-18', 'Giovedì', 'Libera'),
(1690, 'null', 11, 'Programmazione', '18-19', 'Giovedì', 'Libera'),
(1691, 'null', 11, 'Programmazione', '15-16', 'Venerdì', 'Libera'),
(1692, 'null', 11, 'Programmazione', '16-17', 'Venerdì', 'Libera'),
(1693, 'null', 11, 'Programmazione', '17-18', 'Venerdì', 'Libera'),
(1694, 'null', 11, 'Programmazione', '18-19', 'Venerdì', 'Libera'),
(1715, 'Federico', 10, 'Fisica', '15-16', 'Lunedì', 'Prenotata'),
(1716, 'null', 10, 'Fisica', '16-17', 'Lunedì', 'Libera'),
(1717, 'null', 10, 'Fisica', '15-16', 'Lunedì', 'Libera');

-- --------------------------------------------------------

--
-- Struttura della tabella `utente`
--

CREATE TABLE `utente` (
  `nome` varchar(25) NOT NULL,
  `cognome` varchar(25) NOT NULL,
  `nomeUtente` varchar(25) NOT NULL,
  `password` varchar(25) NOT NULL,
  `ruolo` varchar(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dump dei dati per la tabella `utente`
--

INSERT INTO `utente` (`nome`, `cognome`, `nomeUtente`, `password`, `ruolo`) VALUES
('1', '1', '1', '1', 'Amministratore'),
('Federico', 'Filì', 'Federico', '1', 'Amministratore'),
('Mario', 'Rossi', 'Mario', '1', 'Utente'),
('null', 'null', 'null', 'null', 'utente');

--
-- Indici per le tabelle scaricate
--

--
-- Indici per le tabelle `corso`
--
ALTER TABLE `corso`
  ADD PRIMARY KEY (`titoloCorso`);

--
-- Indici per le tabelle `docente`
--
ALTER TABLE `docente`
  ADD PRIMARY KEY (`idDocente`);

--
-- Indici per le tabelle `insegnamento`
--
ALTER TABLE `insegnamento`
  ADD PRIMARY KEY (`idInsegnamento`),
  ADD UNIQUE KEY `idDocente` (`idDocente`,`idCorso`),
  ADD UNIQUE KEY `idDocente_2` (`idDocente`,`idCorso`),
  ADD KEY `insegnamento_ibfk_2` (`idCorso`);

--
-- Indici per le tabelle `prenotazione`
--
ALTER TABLE `prenotazione`
  ADD PRIMARY KEY (`idPrenotazione`),
  ADD KEY `prenotazione_ibfk_1` (`idUtente`),
  ADD KEY `prenotazione_ibfk_4` (`idDocente`),
  ADD KEY `idCorso` (`idCorso`);

--
-- Indici per le tabelle `utente`
--
ALTER TABLE `utente`
  ADD PRIMARY KEY (`nomeUtente`);

--
-- AUTO_INCREMENT per le tabelle scaricate
--

--
-- AUTO_INCREMENT per la tabella `docente`
--
ALTER TABLE `docente`
  MODIFY `idDocente` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT per la tabella `insegnamento`
--
ALTER TABLE `insegnamento`
  MODIFY `idInsegnamento` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1051;

--
-- AUTO_INCREMENT per la tabella `prenotazione`
--
ALTER TABLE `prenotazione`
  MODIFY `idPrenotazione` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1718;

--
-- Limiti per le tabelle scaricate
--

--
-- Limiti per la tabella `insegnamento`
--
ALTER TABLE `insegnamento`
  ADD CONSTRAINT `insegnamento_ibfk_2` FOREIGN KEY (`idCorso`) REFERENCES `corso` (`titoloCorso`) ON DELETE NO ACTION ON UPDATE CASCADE,
  ADD CONSTRAINT `insegnamento_ibfk_3` FOREIGN KEY (`idDocente`) REFERENCES `docente` (`idDocente`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Limiti per la tabella `prenotazione`
--
ALTER TABLE `prenotazione`
  ADD CONSTRAINT `prenotazione_ibfk_4` FOREIGN KEY (`idDocente`) REFERENCES `docente` (`idDocente`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `prenotazione_ibfk_5` FOREIGN KEY (`idCorso`) REFERENCES `corso` (`titoloCorso`) ON DELETE NO ACTION ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
