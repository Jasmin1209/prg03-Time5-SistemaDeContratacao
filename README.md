# 🧑‍💼 Sistema de Contratação

Este projeto tem como objetivo desenvolver um sistema para gerenciamento de processos seletivos, conectando empresas que disponibilizam vagas de emprego a candidatos que desejam se candidatar de forma simples, organizada e rápida.

A aplicação permite que candidatos criem perfis profissionais, enviem candidaturas e acompanhem o processo seletivo, enquanto empresas podem cadastrar vagas, avaliar candidatos e atualizar o status das candidaturas.

---

## 🧱 Tecnologias Utilizadas

| Tecnologia | Função |
|-----------|--------|
| Java / Spring Boot | Back-end da aplicação |
| Spring Data JPA | Persistência de dados |
| Hibernate | ORM |
| Supabase (PostgreSQL online) | Banco de dados |
| Maven | Gerenciamento de dependências |
| Lombok (opcional) | Redução de boilerplate |
| Spring Validation | Validações |
| Git + GitHub | Controle de versão |

---

## 📌 Funcionalidades Principais

### 👤 Para candidatos
- Criar e editar perfil pessoal
- Adicionar formação, idiomas, experiência e competências
- Pesquisar e visualizar vagas
- Candidatar-se a vagas
- Acompanhar o status da candidatura

### 🏢 Para empresas
- Criar perfil empresarial
- Cadastrar uma ou mais vagas
- Gerenciar e atualizar status das candidaturas
- Filtrar candidatos por requisitos

---

## 🗂 Arquitetura do Projeto

- Arquitetura em camadas (Controller → Service → Repository → Entity)
- Entidades mapeadas com JPA/Hibernate
- DTOs para entrada e saída de dados

---

## 🗃 Diagrama de Classes UML

<p align="center">
  <img src="https://raw.githubusercontent.com/Jasmin1209/prg03-Time5-SistemaDeContratacao/main/SistemaDeContratacao.drawio (8).png" width="600">
</p>




---

### Passo 1 — Clonar o projeto

```bash
git clone https://github.com/SEU-REPOSITORIO/SistemaDeContratacao.git
cd SistemaDeContratacao


