# Etapas do Projeto



1. Levantamento de Requisitos
2. Casos de Uso e Modelagem de Dados
3. Prototipação
4. Modelagem no banco de dados
5. Desenvolvimento
6. Testes
7. 





Requisitos



📌 Requisitos Essenciais para o MVP (Básico e Funcional)

📱 1. Visão do Cliente Final (Cardápio Digital Mobile)

\[RF01] Visualizar Cardápio: Consultar a lista de produtos organizada por categorias (ex: Bebidas, Lanches, Sobremesas), com foto, nome, descrição e preço.



\[RF02] Selecionar Adicionais: Escolher opções/adicionais simples para um produto (ex: Borda recheada ou Ingrediente extra).



\[RF03] Gerenciar Carrinho de Compras: Adicionar/remover itens, alterar quantidades e ver o valor total atualizado.



\[RF04] Identificação e Entrega: Informar nome, telefone/WhatsApp, endereço de entrega ou opção de retirada no local.



\[RF05] Escolher Forma de Pagamento: Selecionar entre Pix (com chave/QR Code da loja) ou Pagamento na Entrega (Cartão ou Dinheiro com indicação de troco).



\[RF06 - Atualizado] Finalizar e Acompanhar Pedido: Concluir o pedido, registrá-lo no banco de dados e redirecionar o cliente para a Tela de Acompanhamento de Status em Tempo Real, com a opção de abrir uma mensagem formatada de confirmação no WhatsApp web/app da loja via link direto (wa.me).



👨‍🍳 2. Visão da Cozinha / Atendimento (Painel Operacional)

\[RF07] Painel de Pedidos (Kanban): Visualizar os pedidos recebidos organizados por colunas de status: Novo Pedido ➔ Em Preparo ➔ Saiu para Entrega ➔ Concluído.



\[RF08] Atualização em Tempo Real: Receber novos pedidos na tela sem precisar recarregar a página (via WebSockets).



\[RF09] Alterar Status do Pedido: Mover o pedido entre as colunas do Kanban à medida que ele é preparado e entregue.





⚙️ 3. Visão do Administrador (Painel do Restaurante)

\[RF10] Login do Administrador: Autenticação segura com e-mail e senha.



\[RF11] Cadastro do Cardápio (CRUD): Cadastrar, editar, visualizar e excluir categorias, produtos e adicionais.



\[RF12] Configurações Básicas da Loja: Cadastrar nome do restaurante, logo, telefone/WhatsApp e taxa fixa de entrega.





⚡ 4. Requisitos Não-Funcionais Básicos (RNF)

\[RNF01] Multi-Tenancy Simples: Isolamento de dados dos restaurantes no banco através da identificação do tenant (restaurante\_id).



\[RNF02] Responsividade (Mobile-First): O cardápio deve funcionar de forma fluida no navegador de qualquer smartphone.



\[RNF03] Segurança: Autenticação do painel administrativo via Spring Security + JWT.



\[RNF04] Atualização em Tempo Real: Comunicação via WebSockets (STOMP) entre o servidor Spring e a tela Angular da cozinha.





📜 5. Regras de Negócio Básicas (RN)

\[RN01] Preenchimento Obrigatório: Não permitir a finalização do pedido se campos essenciais (nome, telefone e endereço) não forem preenchidos.



\[RN02] Troco Válido: No pagamento em dinheiro, o valor informado para troco deve ser maior que o total da compra.

