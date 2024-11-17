# Guia para Rodar o Projeto

## Passo 1: Rodar o Docker do Projeto

1. Após clonar o repositório com `git clone`, navegue até a pasta `infra`, que está dentro da pasta `resources` do projeto.
2. Execute o seguinte comando no terminal para subir os contêineres:

   ```bash
   docker compose up -d
   ```

---

## Passo 2: Criar o Bucket no MinIO

1. Após o passo 1, acesse o MinIO no seguinte endereço: [http://localhost:9001/login](http://localhost:9001/login).
2. Faça login utilizando as credenciais abaixo:
   - **Username:** `minioserve`
   - **Password:** `minioserve`
3. Siga os passos para criar o bucket:
   - Clique no botão **"Create Bucket"**.
   - Insira o nome do bucket como **`elevar`**.
   - Clique em **"Create Bucket"** para concluir.
4. Acesse o bucket recém-criado e altere a política de acesso para **pública**.

---

## Passo 3: Rodar o Projeto

1. Certifique-se de que todas as dependências estão configuradas corretamente.
2. Execute o projeto usando sua IDE ou via terminal.

---

## Breve Resumo do Projeto

O projeto foi desenvolvido com foco em simplicidade e funcionalidade. Algumas regras de negócio foram implementadas:

- **Categorias**: Cada categoria possui um nome único.
- **Produtos**:
  - Foi adicionado o atributo **quantidade**.
  - Regras de negócio:
    - Não é permitido cadastrar produtos com **valor** ou **quantidade negativa**.
  - Como funcionalidade extra foi colocado um filtro por categoria, onde se passa nomente o nome da categoria
---

Agora o projeto está pronto para ser utilizado! 🚀
