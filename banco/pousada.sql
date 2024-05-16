-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3307
-- Tempo de geração: 16-Maio-2024 às 02:59
-- Versão do servidor: 10.4.32-MariaDB
-- versão do PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Banco de dados: `pousada`
--

-- --------------------------------------------------------

--
-- Estrutura da tabela `tabela_admin`
--

CREATE TABLE `tabela_admin` (
  `admin_id` int(11) NOT NULL,
  `admin_login` varchar(50) NOT NULL,
  `admin_senha` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Extraindo dados da tabela `tabela_admin`
--

INSERT INTO `tabela_admin` (`admin_id`, `admin_login`, `admin_senha`) VALUES
(1, 'andersonbs96', 'Zxcvb00*');

-- --------------------------------------------------------

--
-- Estrutura da tabela `tabela_clientes`
--

CREATE TABLE `tabela_clientes` (
  `clientes_id` int(11) NOT NULL,
  `clientes_nome` varchar(250) NOT NULL,
  `clientes_cpf` char(11) NOT NULL,
  `clientes_endereco` varchar(250) NOT NULL,
  `clientes_ddd` char(2) NOT NULL,
  `clientes_telefone` char(9) NOT NULL,
  `clientes_cidade` varchar(250) NOT NULL,
  `clientes_estado` char(2) NOT NULL,
  `clientes_email` varchar(150) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Extraindo dados da tabela `tabela_clientes`
--

INSERT INTO `tabela_clientes` (`clientes_id`, `clientes_nome`, `clientes_cpf`, `clientes_endereco`, `clientes_ddd`, `clientes_telefone`, `clientes_cidade`, `clientes_estado`, `clientes_email`) VALUES
(1, 'Anderson Brito Santos', '35573131826', 'Av. Pres. João Goulart', '11', '934240847', 'São Paulo', 'SP', 'andersonsantos6991@gmail.com'),
(2, 'Marcia de Brito Santos', '13482304874', 'Avenida Presidente João Goulart', '75', '995957479', 'Monte Santo', 'BA', 'marciabbbbsantos@gmail.com');

-- --------------------------------------------------------

--
-- Estrutura da tabela `tabela_quartos`
--

CREATE TABLE `tabela_quartos` (
  `quartos_id` int(11) NOT NULL,
  `quartos_numero` char(3) NOT NULL,
  `quartos_descricao` varchar(200) NOT NULL,
  `quartos_preco` decimal(5,2) NOT NULL,
  `quartos_disponibilidade` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Extraindo dados da tabela `tabela_quartos`
--

INSERT INTO `tabela_quartos` (`quartos_id`, `quartos_numero`, `quartos_descricao`, `quartos_preco`, `quartos_disponibilidade`) VALUES
(1, '101', 'Quarto Simples com duas camas de solteiro', 250.00, 'Disponivel'),
(2, '102', 'Quarto Simples com duas camas de solteiro', 250.00, 'Disponivel'),
(3, '103', 'Quarto Simples com duas camas de solteiro', 250.00, 'Disponivel'),
(4, '104', 'Quarto Simples com duas camas de solteiro', 250.00, 'Disponivel'),
(5, '105', 'Quarto Simples com uma cama de casal e uma solteiro', 250.00, 'Disponivel'),
(6, '106', 'Quarto Simples com uma cama de casal e uma solteiro', 250.00, 'Disponivel'),
(7, '107', 'Quarto Simples com uma cama de casal e uma solteiro', 250.00, 'Disponivel'),
(8, '108', 'Quarto Simples com uma cama de casal', 250.00, 'Disponivel'),
(9, '109', 'Quarto Simples com uma cama de casal', 250.00, 'Disponivel'),
(10, '110', 'Quarto Simples com uma cama de casal', 250.00, 'Disponivel'),
(11, '201', 'Quarto Simples com duas camas de solteiro', 250.00, 'Disponivel'),
(12, '202', 'Quarto Simples com duas camas de solteiro', 250.00, 'Disponivel'),
(13, '203', 'Quarto Simples com duas camas de solteiro', 250.00, 'Disponivel'),
(14, '204', 'Quarto Simples com duas camas de solteiro', 250.00, 'Disponivel'),
(15, '205', 'Quarto Simples com uma cama de casal e uma solteiro', 250.00, 'Disponivel'),
(16, '206', 'Quarto Simples com uma cama de casal e uma solteiro', 250.00, 'Disponivel'),
(17, '207', 'Quarto Simples com uma cama de casal e uma solteiro', 250.00, 'Disponivel'),
(18, '208', 'Quarto Simples com uma cama de casal', 250.00, 'Disponivel'),
(19, '209', 'Quarto Simples com uma cama de casal', 250.00, 'Disponivel'),
(20, '210', 'Quarto Simples com uma cama de casal', 250.00, 'Disponivel'),
(21, '301', 'Quarto Simples com duas camas de solteiro', 250.00, 'Disponivel'),
(22, '302', 'Quarto Simples com duas camas de solteiro', 250.00, 'Disponivel'),
(23, '303', 'Quarto Simples com duas camas de solteiro', 250.00, 'Disponivel'),
(24, '304', 'Quarto Simples com duas camas de solteiro', 250.00, 'Disponivel'),
(25, '305', 'Quarto Simples com uma cama de casal e uma solteiro', 250.00, 'Disponivel'),
(26, '306', 'Quarto Simples com uma cama de casal e uma solteiro', 250.00, 'Disponivel'),
(27, '307', 'Quarto Simples com uma cama de casal e uma solteiro', 250.00, 'Disponivel'),
(28, '308', 'Quarto Simples com uma cama de casal', 250.00, 'Disponivel'),
(29, '309', 'Quarto Simples com uma cama de casal', 250.00, 'Disponivel'),
(30, '310', 'Quarto Simples com uma cama de casal', 250.00, 'Disponivel');

-- --------------------------------------------------------

--
-- Estrutura da tabela `tabela_reservas`
--

CREATE TABLE `tabela_reservas` (
  `reservas_id` int(11) NOT NULL,
  `reservas_dataEntrada` date NOT NULL,
  `reservas_dataSaida` date NOT NULL,
  `reservas_clientesID` int(11) NOT NULL,
  `reservas_quartosID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Acionadores `tabela_reservas`
--
DELIMITER $$
CREATE TRIGGER `disponibilidadeQuarto` AFTER INSERT ON `tabela_reservas` FOR EACH ROW begin
	UPDATE tabela_quartos SET quartos_disponibilidade = 'Ocupado' WHERE quartos_id = quartos_id;
end
$$
DELIMITER ;

--
-- Índices para tabelas despejadas
--

--
-- Índices para tabela `tabela_admin`
--
ALTER TABLE `tabela_admin`
  ADD PRIMARY KEY (`admin_id`);

--
-- Índices para tabela `tabela_clientes`
--
ALTER TABLE `tabela_clientes`
  ADD PRIMARY KEY (`clientes_id`);

--
-- Índices para tabela `tabela_quartos`
--
ALTER TABLE `tabela_quartos`
  ADD PRIMARY KEY (`quartos_id`);

--
-- Índices para tabela `tabela_reservas`
--
ALTER TABLE `tabela_reservas`
  ADD PRIMARY KEY (`reservas_id`),
  ADD KEY `reservas_clientesID` (`reservas_clientesID`),
  ADD KEY `reservas_quartosID` (`reservas_quartosID`);

--
-- AUTO_INCREMENT de tabelas despejadas
--

--
-- AUTO_INCREMENT de tabela `tabela_admin`
--
ALTER TABLE `tabela_admin`
  MODIFY `admin_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de tabela `tabela_clientes`
--
ALTER TABLE `tabela_clientes`
  MODIFY `clientes_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de tabela `tabela_quartos`
--
ALTER TABLE `tabela_quartos`
  MODIFY `quartos_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=31;

--
-- AUTO_INCREMENT de tabela `tabela_reservas`
--
ALTER TABLE `tabela_reservas`
  MODIFY `reservas_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- Restrições para despejos de tabelas
--

--
-- Limitadores para a tabela `tabela_reservas`
--
ALTER TABLE `tabela_reservas`
  ADD CONSTRAINT `reservas_clientesID` FOREIGN KEY (`reservas_clientesID`) REFERENCES `tabela_clientes` (`clientes_id`),
  ADD CONSTRAINT `reservas_quartosID` FOREIGN KEY (`reservas_quartosID`) REFERENCES `tabela_quartos` (`quartos_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
