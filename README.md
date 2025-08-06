# Cadastro - Java Spring Boot com Bootstrap

Este sistema tem como objetivo criar uma aplicação web simples que utiliza o Spring Boot no backend e o Bootstrap no frontend para gerenciar um formulário de cadastro. O sistema captura dados como nome e e-mail, salva esses dados e exibe-os em uma tabela.

## 🪛Ferramentas utilizadas
- VS Code
- Java 17: Versão recomendada do JDK.
- Spring Boot: Framework para criação de aplicações Java.
- Bootstrap 5: Framework CSS para o frontend.

## 🔦Extensões utilizadas
- Java Extension Pack: Pacote de extensões para desenvolvimento em Java.
- Spring Boot Extension Pack: Extensões para facilitar o desenvolvimento com Spring Boot.
  
<img width="706" height="304" alt="image" src="https://github.com/user-attachments/assets/d77ba09c-bf04-4c05-8104-1c6fdfa12e9b" />

## 📁 Sistema
```
cadastro/
├── .git/                         ← Repositório Git
├── .gitignore                    ← Arquivos ignorados pelo Git
├── .gitattributes                ← Configurações Git adicionais
├── .mvn/                         ← Wrapper do Maven
│   └── wrapper/
│       └── maven-wrapper.properties
├── .vscode/                      ← Configurações do VS Code
│   └── launch.json
├── HELP.md                       ← Arquivo de ajuda (provavelmente gerado pelo Spring Initializr)
├── mvnw, mvnw.cmd                ← Scripts para executar o Maven wrapper
├── pom.xml                       ← Arquivo de configuração do Maven
├── src/
│   ├── main/
│   │   ├── java/com/cadastro/cadastro/
│   │   │   ├── CadastroApplication.java         ← Classe principal da aplicação
│   │   │   ├── dto/
│   │   │   │   └── CadastroDTO.java             ← Objeto de transferência de dados
│   │   │   └── resources/
│   │   │       └── CadastroResource.java        ← Controlador REST (provavelmente)
│   │   └── resources/
│   │       ├── application.properties           ← Configurações da aplicação
│   │       ├── static/
│   │       │   └── cadastro.html                ← Página estática HTML
│   │       └── templates/
│   │           └── lista.html                   ← Template HTML (provavelmente com Thymeleaf)
│   └── test/
│       └── java/com/cadastro/cadastro/
│           └── CadastroApplicationTests.java    ← Classe de teste da aplicação
├── target/                         ← Diretório de build (gerado pelo Maven)

```


## 🔧Passo a Passo

1. Abra o VS Code
2. Na aba "Explorador", clique no botão "Create Java Project".

<img width="313" height="140" alt="image" src="https://github.com/user-attachments/assets/2e79bbb9-4627-4a27-9e43-65f9f96e1c74"/>

3. Escolha a opção Spring Boot com o Maven.
4. Digite os seguintes dados:
   - Group: com.cadastro
   - Artifact: cadastro
   - Java Version: 17
   - Type: Jar
5. Adicione as dependências:
   - Spring Web
   - Thymeleaf
   - Lombok
   - Spring Boot DevTools
  
   <img width="736" height="222" alt="image" src="https://github.com/user-attachments/assets/cb8735e7-1ac7-49be-b92c-3b91bc641ca7" />

6. Escolha o diretório onde o projeto será salvo e clique em Open.


## 🏗️ Criando o Formulário de Cadastro (Frontend)

1. No VS Code, crie o arquivo cadastro.html em src/main/resources/static.
2. Preencha o arquivo com o seguinte conteúdo HTML, utilizando o Bootstrap 5 para o estilo e layout:
```
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/css/bootstrap.min.css" rel="stylesheet" crossorigin="anonymous">
    <title>Formulário de Cadastro</title>
</head>
<body>

<!-- Formulário -->
<form method="post" action="cadastroPost">
  <div class="mb-3">
    <label for="exampleInputEmail1" class="form-label">Email address</label>
    <input type="email" class="form-control" name="email" required>
    <div id="emailHelp" class="form-text">We'll never share your email with anyone else.</div>
  </div>

  <div class="mb-3">
    <label for="formGroupInput2" class="form-label">Name</label>
    <input type="text" class="form-control" name="name" required>
  </div>

  <div class="mb-3 form-check">
    <input type="checkbox" class="form-check-input" id="exampleCheck1">
    <label class="form-check-label" for="exampleCheck1">Check me out</label>
  </div>

  <button type="submit" class="btn btn-primary">Submit</button>
</form>

</body>
</html>

```

## 🎮 Criando o Controlador (Backend)
  1. No VS Code, crie a classe CadastroResource.java em src/main/java/com/cadastro/controller.
  2. Adicione o seguinte código ao controlador:
```
package com.cadastro.cadastro.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;
import com.cadastro.cadastro.dto.CadastroDTO;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CadastroResource {

    // Lista para armazenar cadastros
    private List<CadastroDTO> cadastros = new ArrayList<>();

    // Método para processar o formulário
    @PostMapping("cadastroPost")
    public String doPost(CadastroDTO cadastroDTO, Model model) {
        cadastros.add(cadastroDTO);
        model.addAttribute("cadastros", cadastros);
        return "lista"; // Retorna para a página de lista
    }
}

```
## 🎲 Criando o DTO (Data Transfer Object)

1. Crie a classe CadastroDTO.java em src/main/java/com/cadastro/dto:
```
package com.cadastro.cadastro.dto;

import lombok.Data;

@Data
public class CadastroDTO {
    private String email;
    private String name;
}
```
## ✅ Criando a Página de Lista de Cadastros
1. Crie o arquivo lista.html em src/main/resources/templates.
2. Preencha o arquivo com o seguinte código para exibir os cadastros em uma tabela:
```
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/css/bootstrap.min.css" rel="stylesheet" crossorigin="anonymous">
    <title>Lista de Cadastros</title>
</head>
<body>

<table class="table">
  <thead>
    <tr>
      <th scope="col">Nome</th>
      <th scope="col">E-mail</th>
    </tr>
  </thead>
  <tbody>
    <tr th:each="cadastro : ${cadastros}">
        <td th:text="${cadastro.name}"></td>
        <td th:text="${cadastro.email}"></td>
    </tr>
  </tbody>
</table>

</body>
</html>

```
## ⚙️ Executando o Projeto

1. Para iniciar o servidor, vá até o painel lateral no VS Code e clique no botão de "Power" para rodar o Spring Boot.
2. O terminal do VS Code exibirá as informações do servidor. Normalmente, a aplicação rodará na porta 8080.
   
## Testando a Aplicação
Abra o navegador e acesse a URL: http://localhost:8080/cadastro.html.

Preencha o formulário e clique em Submit.

Após o envio, você será redirecionado para a página lista.html, onde será exibido o cadastro realizado.

