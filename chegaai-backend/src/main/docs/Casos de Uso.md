# Documento de Arquitetura e Plano de Implementação - ChegaAI SaaS

## Visão Geral do Projeto
O **ChegaAI** é um backend para um SaaS de delivery multi-tenant, desenvolvido com **Java** e **Spring Boot**, utilizando arquitetura em camadas (`Controller -> Service -> Repository`), persistência via Spring Data JPA.

---

## Fases de Implementação e Ordem de Desenvolvimento

### 1. Fase 1: A Base do Sistema e Autenticação
*Objetivo: Estabelecer as fundações multilocatárias (multi-tenant), segurança e controle de acesso.*

* **Entidades Envolvidas**:
    * `Restaurante` (Dados cadastrais, CNPJ, status)
    * `Usuario` (Controle de credenciais e perfis)
* **Perfis de Acesso (`Roles`)**:
    * `ROLE_GLOBAL_ADMIN`: Administrador geral (gerencia restaurantes, inadimplência e faturamento global).
    * `ROLE_DONO_RESTAURANTE`: Dono do estabelecimento (acesso total ao painel da loja).
    * `ROLE_GERENTE_RESTAURANTE`: Gerente/Caixa (opera o caixa, aceita/cancela pedidos e despacha entregadores).
    * `ROLE_COZINHA_RESTAURANTE`: Cozinha/KDS (visualiza e altera status no Kanban).
* **Requisitos Funcionais (RF)**:
    * **RF01**: O sistema deve permitir o cadastro e aprovação de novos restaurantes pelo administrador global. ✅​
    * **RF02**: O sistema deve autenticar usuários com base em seus perfis (`Roles`) e isolar o acesso às funcionalidades da respectiva loja.✅​
* **Requisitos Não Funcionais (RNF)**:
    * **RNF01**: As senhas dos usuários devem ser armazenadas utilizando criptografia forte (BCrypt).
    * **RNF02**: O isolamento multi-tenant deve garantir que dados de um restaurante nunca sejam acessados por usuários de outro.

---

### 2. Fase 2: O Catálogo e Cardápio Digital
*Objetivo: Permitir que o lojista estruture o cardápio que será consumido publicamente pelos clientes.*

* **Entidades Envolvidas**:
    * `Cardapio`
    * `Categoria`
    * `Produto`
    * `ComplementoGrupo` e `ComplementoItem` (Gerenciamento de adicionais obrigatórios/opcionais)
* **Requisitos Funcionais (RF)**:
    * **RF03**: O cliente deve conseguir visualizar a lista de produtos categorizados (ex: Lanches, Bebidas) acessando o link público da loja.
    * **RF04**: O cliente deve conseguir selecionar complementos/adicionais e adicionar observações personalizadas aos itens do pedido.

---

### 3. Fase 3: Cadastros Operacionais Auxiliares
*Objetivo: Estruturar as entidades de apoio para a logística e identificação de clientes.*

* **Entidades Envolvidas**:
    * `Cliente` (Nome, Telefone, E-mail)
    * `Endereco` (Rua, Número, Bairro, Complemento, Coordenadas)
    * `Entregador` (Cadastro operacional simples contendo apenas nome, telefone e `restaurante_id`, sem necessidade de conta de usuário).
* **Requisitos Funcionais (RF)**:
    * **RF05**: O sistema deve permitir que o cliente informe seus dados cadastrais e endereço de entrega no momento do fechamento do pedido.
    * **RF06**: O painel administrativo da loja deve permitir o cadastro e gerenciamento de entregadores operacionais vinculados ao restaurante.

---

### 4. Fase 4: O Fluxo Transacional (Pedidos e Pagamentos)
*Objetivo: O coração do SaaS, integrando carrinho, regras financeiras, gateways e tempo real.*

* **Entidades Envolvidas**:
    * `FormaDePagamento` (Transacional: `restaurante_id`, `transacao_id`, `metodo`, `status_pagamento`)
    * `Pedido` (Aggregate Root: valores, totais, taxas de entrega, troco, status)
    * `ItemPedido` e `ItemPedidoComplemento` (Gerenciados via JPA Cascading)
* **Requisitos Funcionais (RF)**:
    * **RF07**: O sistema deve gerenciar o carrinho de compras, calculando o valor acumulado dos produtos e taxas de entrega.
    * **RF08**: O cliente deve selecionar a forma de pagamento (Pix com chave/QR Code, Dinheiro com cálculo de troco, ou Cartão na entrega/retirada).
    * **RF09**: O backend deve gravar o pedido no banco com status inicial de `PENDENTE` e disparar notificações em tempo real via WebSocket para a cozinha.
    * **RF10 (Painel KDS / Kanban)**: O cozinheiro (`ROLE_KITCHEN`) deve conseguir alterar o status do pedido entre `AGUARDANDO_LOJA_ACEITAR`, `EM_PREPARACAO` e `EM_ROTA_DE_ENTREGA` com um toque na tela.
    * **RF11 (Atendimento / Frente de Loja)**: O operador deve conseguir aceitar pedidos online, lançar pedidos presenciais e atribuir o entregador ao pedido através de um select simples (`entregador_id`).
    * **RF12 (Tracking Page)**: O cliente deve conseguir acompanhar o status do pedido em tempo real através de uma tela dedicada com link/token único.
* **Requisitos Não Funcionais (RNF)**:
    * **RNF03**: A comunicação em tempo real para o painel da cozinha deve possuir baixa latência utilizando WebSockets.
    * **RNF04**: A persistência do pedido e das informações de pagamento deve garantir consistência transacional atômica (ACID).

---

## Diretrizes Técnicas do Backend

* **Arquitetura em Camadas**: Adesão estricta ao fluxo `Controller -> Service -> Repository`. Services são obrigatórios para transações (`@Transactional`) e regras de negócio.
* **Persistência**: Uso do `JpaRepository` para operações padrão de CRUD.