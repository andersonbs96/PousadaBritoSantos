-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3307
-- Tempo de geração: 22-Maio-2024 às 23:53
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

DELIMITER $$
--
-- Procedimentos
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `verificar_conflito_reserva` (IN `quarto_id` INT, IN `data_entrada` DATE, IN `data_saida` DATE)   BEGIN
	IF EXISTS (
        SELECT 1
        FROM tabela_reservas
        WHERE reservas_quartosID = quarto_id
            AND (
                (data_entrada < reservas_dataSaida AND data_entrada >= reservas_dataEntrada) OR
                (data_saida <= reservas_dataSaida AND data_saida > reservas_dataEntrada) OR
                (data_entrada <= reservas_dataEntrada AND data_saida >= reservas_dataSaida)
            )
    ) THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Conflito de reserva: As datas de entrada e saída se sobrepõem com outra reserva.';
    END IF;
END$$

DELIMITER ;

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
(1, 'Anderson Brito Santos', '35573131826', 'Avenida Presidente João Goulart', '11', '934240847', 'São Paulo', 'SP', 'andersonsantos6991@gmail.com'),
(2, 'Marcia de Brito Santos', '13482304874', 'Rua Teixeira de Freitas', '75', '995957479', 'Monte Santo', 'BA', 'marciabbbbsantos@gmail.com'),
(3, 'Galvão Bueno de Menezes', '50923453814', 'Rua Mello Franco', '21', '978520156', 'Teresópolis', 'RJ', 'galvaobueno@gmail.com'),
(4, 'José Almeida de Oliveira', '94747885102', 'Rua Parati', '62', '974830943', 'Goiânia', 'GO', 'josealmeida89@outlook.com.br'),
(5, 'Ana Paula Cesário Duarte', '59629205971', 'Rua Iugoslávia', '47', '964320189', 'Balneário Camboriu', 'SC', 'anapaula65@gmail.com'),
(6, 'Aldair Gonçalves Nunes', '40565901680', 'São José', '32', '974893218', 'Barbacena', 'MG', 'aldairgoncalves75@outlook.com'),
(7, 'Hugo Oliveira Silva', '15994685305', 'Rua Major Inacio Almeida', '86', '959547082', 'Teresina', 'PI', 'hugooliveira32@gmail.com'),
(8, 'Marcela Verônica Martins', '94303042404', 'Rua Mônaco ', '83', '964538096', 'Campina Grande', 'PB', 'marciaveronica@hotmail.com'),
(9, 'Josefina Eduardo de Castro', '86073816448', 'Passagem Pinto Martins', '84', '932518912', 'Natal', 'RN', 'josefinadecastro81@gmail.com'),
(10, 'Mateus Pinheiros Juarez', '57333363589', 'Rua Jair', '71', '941250465', 'Salvador', 'BA', 'mateuspinheiros@outlook.com.br'),
(11, 'Marieta Carvalho dos Santos', '44896931505', 'Rua Doutor João João Emídio', '75', '948793970', 'Caldas do Jorro', 'BA', 'marietacarvalho@hotmail.com'),
(12, 'Joaquim Teixeira de Brito', '85999545390', 'Rua Principal', '88', '963700977', 'Crateús', 'CE', 'joaquimteixeira96@gmail.com'),
(13, 'Maria José Pereira Amadeus', '78248084400', 'Rua Marrocos', '83', '958436040', 'Campina Grande', 'PB', 'mariajosepereira@gmail.com'),
(14, 'Carlos Manoel Mendonça Filho', '87794672961', 'Rua Iracema Leondina Rios', '42', '984025335', 'Ponta Grossa', 'PR', 'carlosmanoel106@hotmail.com'),
(15, 'Antônia de Jesus Pinto', '51564629449', 'Avenida Walter Wanderley', '84', '959633109', 'Mossoró', 'RN', 'antoniajesus67@outlook.com'),
(16, 'José Santos', '71143398521', 'Rua Doutor Edgard Tupinambá', '75', '994540970', 'Conceição do Almeida', 'BA', 'josesantos38@hotmail.com'),
(17, 'Sebastião Moraes Filho', '81447590791', 'Rua Izidoro Sechim', '11', '929317012', 'Cachoeiro do Itapemirim', 'ES', 'sebastiaomoraes@gmail.com');

-- --------------------------------------------------------

--
-- Estrutura da tabela `tabela_clientespagamento`
--

CREATE TABLE `tabela_clientespagamento` (
  `clientesPagamento_id` int(11) NOT NULL,
  `clientesPagamento_clientesID` int(11) NOT NULL,
  `clientesPagamento_metodo` varchar(50) NOT NULL,
  `clientesPagamento_numeroCartao` char(16) DEFAULT NULL,
  `clientesPagamento_nomeCartao` varchar(50) DEFAULT NULL,
  `clientesPagamento_validadeCartao` date DEFAULT NULL,
  `clientesPagamento_nomeBanco` varchar(100) DEFAULT NULL,
  `clientesPagamento_dataPagamento` date DEFAULT curdate()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Acionadores `tabela_clientespagamento`
--
DELIMITER $$
CREATE TRIGGER `atualizarPagamento` AFTER INSERT ON `tabela_clientespagamento` FOR EACH ROW begin
	UPDATE tabela_pagamentos SET pagamentos_situacao = 'Pago' WHERE pagamentos_id = new.clientesPagamento_id;
end
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Estrutura da tabela `tabela_pagamentos`
--

CREATE TABLE `tabela_pagamentos` (
  `pagamentos_id` int(11) NOT NULL,
  `pagamentos_reservasID` int(11) DEFAULT NULL,
  `pagamentos_clientesID` int(11) DEFAULT NULL,
  `pagamentos_valorTotal` decimal(10,2) NOT NULL,
  `pagamentos_situacao` varchar(20) NOT NULL DEFAULT 'Pendente',
  `pagamentos_dataReserva` date DEFAULT curdate()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Acionadores `tabela_pagamentos`
--
DELIMITER $$
CREATE TRIGGER `deletarPagamento` AFTER INSERT ON `tabela_pagamentos` FOR EACH ROW begin
	DECLARE pagamentoData DATE;
    DECLARE pagamentoReserva DATE;
    
	SELECT pagamentos_dataReserva INTO pagamentoReserva FROM tabela_pagamentos WHERE pagamentos_id = new.pagamentos_id and pagamentos_situacao = 'Pendente';
   
   SET pagamentoData = CURRENT_DATE();
   
   IF DATEDIFF(pagamentoData, pagamentoReserva) > 3 THEN
   		DELETE FROM tabela_reservas WHERE reservas_id = new.pagamentos_reservasID;
   END IF;
end
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Estrutura da tabela `tabela_quartos`
--

CREATE TABLE `tabela_quartos` (
  `quartos_id` int(11) NOT NULL,
  `quartos_numero` char(3) NOT NULL,
  `quartos_descricao` varchar(200) NOT NULL,
  `quartos_preco` decimal(5,2) NOT NULL,
  `quartos_disponibilidade` varchar(20) DEFAULT 'disponivel'
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
CREATE TRIGGER `atualizarReserva` AFTER UPDATE ON `tabela_reservas` FOR EACH ROW begin
	IF new.reservas_dataEntrada <= CURRENT_DATE() and new.reservas_dataSaida > CURRENT_DATE() THEN
    	UPDATE tabela_quartos
        SET quartos_disponibilidade = 'Ocupado'
        WHERE quartos_id = new.reservas_quartosID;
    ELSEIF new.reservas_dataEntrada < CURRENT_DATE() and new.reservas_dataSaida < CURRENT_DATE() THEN
    	UPDATE tabela_quartos
        SET quartos_disponibilidade = 'Disponivel'
        WHERE quartos_id = new.reservas_quartosID;
	ELSEIF new.reservas_dataEntrada > CURRENT_DATE() and new.reservas_dataSaida > CURRENT_DATE() THEN
    	UPDATE tabela_quartos
        SET quartos_disponibilidade = 'Reservado'
        WHERE quartos_id = new.reservas_quartosID;
    END IF;
end
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `deletarReserva` AFTER DELETE ON `tabela_reservas` FOR EACH ROW begin
	UPDATE tabela_quartos 
    SET quartos_disponibilidade = 'Disponivel'
    WHERE quartos_id = old.reservas_quartosID;
end
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `inserirReserva` AFTER INSERT ON `tabela_reservas` FOR EACH ROW begin
	IF new.reservas_dataEntrada <= CURRENT_DATE() and new.reservas_dataSaida > CURRENT_DATE() THEN
    	UPDATE tabela_quartos
        SET quartos_disponibilidade = 'Ocupado'
        WHERE quartos_id = new.reservas_quartosID;
    ELSEIF new.reservas_dataEntrada < CURRENT_DATE() and new.reservas_dataSaida < CURRENT_DATE() THEN
    	UPDATE tabela_quartos
        SET quartos_disponibilidade = 'Disponivel'
        WHERE quartos_id = new.reservas_quartosID;
	ELSEIF new.reservas_dataEntrada > CURRENT_DATE() and new.reservas_dataSaida > CURRENT_DATE() THEN
    	UPDATE tabela_quartos
        SET quartos_disponibilidade = 'Reservado'
    	WHERE quartos_id = new.reservas_quartosID;
     END IF;
end
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `reservaPagamento` AFTER INSERT ON `tabela_reservas` FOR EACH ROW begin
	DECLARE quarto_preco DECIMAL(10, 2);
	DECLARE valor_total DECIMAL(10, 2);
    
	SELECT quartos_preco into quarto_preco from tabela_quartos WHERE quartos_id = new.reservas_quartosID;
    
    SET valor_total = DATEDIFF(new.reservas_dataSaida, new.reservas_dataEntrada) * quarto_preco;
    
	INSERT INTO tabela_pagamentos (pagamentos_reservasID, pagamentos_clientesID, pagamentos_valorTotal, pagamentos_situacao)
    VALUES
    (new.reservas_id, new.reservas_clientesID, valor_total, 'pendente');
end
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `verificarAtualizacao` BEFORE UPDATE ON `tabela_reservas` FOR EACH ROW begin
	CALL verificar_conflito_reserva(new.reservas_quartosID, new.reservas_dataEntrada, new.reservas_dataSaida);
end
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `verificarInsercao` BEFORE INSERT ON `tabela_reservas` FOR EACH ROW BEGIN
    CALL verificar_conflito_reserva(
        NEW.reservas_quartosID,
        NEW.reservas_dataEntrada,
        NEW.reservas_dataSaida
    );
END
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
-- Índices para tabela `tabela_clientespagamento`
--
ALTER TABLE `tabela_clientespagamento`
  ADD PRIMARY KEY (`clientesPagamento_id`),
  ADD KEY `clientesPagamento_clientesID` (`clientesPagamento_clientesID`);

--
-- Índices para tabela `tabela_pagamentos`
--
ALTER TABLE `tabela_pagamentos`
  ADD PRIMARY KEY (`pagamentos_id`),
  ADD KEY `pagamentos_clientesID` (`pagamentos_clientesID`),
  ADD KEY `pagamentos_reservasID` (`pagamentos_reservasID`);

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
  MODIFY `clientes_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT de tabela `tabela_clientespagamento`
--
ALTER TABLE `tabela_clientespagamento`
  MODIFY `clientesPagamento_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de tabela `tabela_pagamentos`
--
ALTER TABLE `tabela_pagamentos`
  MODIFY `pagamentos_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de tabela `tabela_quartos`
--
ALTER TABLE `tabela_quartos`
  MODIFY `quartos_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=31;

--
-- AUTO_INCREMENT de tabela `tabela_reservas`
--
ALTER TABLE `tabela_reservas`
  MODIFY `reservas_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- Restrições para despejos de tabelas
--

--
-- Limitadores para a tabela `tabela_clientespagamento`
--
ALTER TABLE `tabela_clientespagamento`
  ADD CONSTRAINT `tabela_clientespagamento_ibfk_1` FOREIGN KEY (`clientesPagamento_clientesID`) REFERENCES `tabela_clientes` (`clientes_id`);

--
-- Limitadores para a tabela `tabela_pagamentos`
--
ALTER TABLE `tabela_pagamentos`
  ADD CONSTRAINT `pagamentos_reservasID` FOREIGN KEY (`pagamentos_reservasID`) REFERENCES `tabela_reservas` (`reservas_id`),
  ADD CONSTRAINT `tabela_pagamentos_ibfk_1` FOREIGN KEY (`pagamentos_clientesID`) REFERENCES `tabela_clientes` (`clientes_id`);

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
