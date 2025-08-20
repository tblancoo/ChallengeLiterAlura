# LiterAlura 📚

Projeto desenvolvido durante o curso da Alura para prática de consumo de API REST com Java, utilizando Spring Boot, JPA e banco de dados PostgreSQL. A aplicação permite buscar livros, autores e idiomas através da API Gutendex e armazenar os dados em um banco relacional.

---

## 💡 Funcionalidades

- ✅ Buscar livros por título usando a API Gutendex
- ✅ Exibir detalhes do livro: título, idioma, autores, link para download e número de downloads
- ✅ Salvar os dados dos livros e autores no banco de dados
- ✅ Exibir todos os livros cadastrados
- ✅ Exibir livros por idioma
- ✅ Exibir autores vivos em um determinado ano
- ✅ Exibir estatísticas de livros com mais downloads

---

## 🛠️ Tecnologias e ferramentas utilizadas

- Java 21
- Spring Boot 3
- Maven
- PostgreSQL
- JPA / Hibernate
- API Rest (Gutendex)
- IntelliJ IDEA
- Git / GitHub

---

## 🚀 Como executar o projeto

### 1. Clonar o repositório:

```bash
git clone https://github.com/seu-usuario/literalura.git
```

### 2. Configurar o banco de dados

Certifique-se de que o PostgreSQL está rodando e crie um banco chamado `literalura`.

Exemplo de `application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/literalura
spring.datasource.username=postgres
spring.datasource.password=123456

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

### 3. Executar o projeto

No IntelliJ, rode a classe `LiterAluraApplication.java` (arquivo principal da aplicação).

---

## 🖼️ Demonstração

📌 Exemplo de saída no console após buscar e salvar um livro:

```
Digite o título do livro: dom casmurro

Livro salvo com sucesso:

📚 Livro: Dom Casmurro  
🌐 Idioma: pt  
✍️ Autores: Machado de Assis (1839 - 1908)  
🔗 Download: https://www.gutenberg.org/ebooks/55752.epub.noimages  
⬇️ Downloads: 1534
```

---

## 🧠 Desafios enfrentados

Durante o desenvolvimento do projeto, enfrentei alguns desafios importantes:

- 🔍 **A funcionalidade de busca por nome de autor ainda necessita de ajustes.** Em alguns casos, o programa não consegue localizar corretamente os autores na base de dados mesmo quando já cadastrados.
- 🔁 **Relacionamento ManyToMany entre livros e autores exigiu atenção especial.** Foi necessário entender bem como configurar as anotações `@ManyToMany`, `@JoinTable` e `mappedBy`, pois erros simples causavam falhas na persistência e duplicação de dados.
- 🔄 **Integração com a API externa (Gutendex)** exigiu tratamento de dados nulos, formatos inesperados e adaptação dos modelos locais.
- 🔁 **Conversão e mapeamento dos dados recebidos da API para as entidades locais** demandaram atenção ao lidar com listas aninhadas, autores com nomes compostos e possíveis campos ausentes ou incompletos.
- 📦 **Persistência de dados com JPA** exigiu revisão constante do modelo para evitar problemas de salvamento, atualização e duplicidade nos registros do banco de dados.
- ⛔ **Tratamento de erros e mensagens ao usuário** ainda pode ser melhorado para fornecer respostas mais claras quando a busca não encontra resultados ou ocorre alguma exceção durante a execução.

Apesar desses desafios, o projeto segue em constante evolução. Pretendo resolver os pontos pendentes e aprimorar a aplicação nas próximas versões.

---

## 📚 Fonte dos dados

Os dados utilizados neste projeto foram obtidos da API pública do [Gutendex](https://gutendex.com/), que fornece metadados sobre livros de domínio público.

---

## 👨‍💻 Autor

Desenvolvido por **Seu Nome Aqui**  
[LinkedIn](https://www.linkedin.com/in/seu-perfil) • [GitHub](https://github.com/seu-usuario)
