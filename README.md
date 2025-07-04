# 📚 Challenge Literalura

![Badge](https://img.shields.io/badge/Projeto-Literalura-blueviolet?style=for-the-badge)
![License](https://img.shields.io/badge/license-MIT-green?style=for-the-badge)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.0-brightgreen?style=for-the-badge)
![Java](https://img.shields.io/badge/Java-17-yellow?style=for-the-badge)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-💾-blue?style=for-the-badge)

---

## 🧠 Sobre o Projeto

Literalura é uma aplicação em Java com Spring Boot que consome dados da API do [Projeto Gutendex](https://gutendex.com/), armazena livros e autores no banco de dados e oferece um menu interativo via console com diversas funcionalidades.

---

## 💡 Funcionalidades

- 🔍 Buscar livro por título
- 📚 Listar livros registrados
- ✍️ Listar autores registrados
- 📆 Filtrar autores vivos em determinado ano
- 🌍 Listar livros por idioma

---

## ▶️ Como Executar o Projeto

### 1️⃣ Clone o repositório

Abra o terminal e execute:

```bash
git clone https://github.com/wellingtonnovais/challenge-literalura.git
cd challenge-literalura
```

---

### 2️⃣ Configure o Banco de Dados PostgreSQL

Crie um banco de dados chamado `literalura` (ou outro nome de sua preferência) e edite o arquivo `src/main/resources/application.properties` com suas credenciais:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/literalura
spring.datasource.username=SEU_USUARIO
spring.datasource.password=SUA_SENHA
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

---

### 3️⃣ Execute o Projeto

No terminal, rode o seguinte comando:

```bash
./mvnw spring-boot:run
```

> Obs: Certifique-se de estar na raiz do projeto e com o banco de dados rodando.

---

### 4️⃣ Use o Menu Interativo no Console

```text
ESCOLHA O NÚMERO DA OPÇÃO DESEJADA

1 - Buscar livro pelo título
2 - Listar livros registrados
3 - Listar autores registrados
4 - Listar autores vivos em um determinado período
5 - Listar livros em um determinado idioma
0 - Sair
```

---

## 🧑‍💻 Desenvolvedor

Feito com 💙 por **Wellington Novais**

[![LinkedIn](https://img.shields.io/badge/LinkedIn-wellington--novais-blue?style=flat-square&logo=linkedin)](https://www.linkedin.com/in/wellington-novais-dev/)

---

## 📄 Licença

Este projeto está sob a licença **MIT**.  
Veja o arquivo [LICENSE](./LICENSE) para mais detalhes.


---
