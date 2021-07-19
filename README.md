# Formação Arquitetura e Design de Projetos Java

## BDD e Java: Behavior Driven Development com Cucumber

Curso: https://cursos.alura.com.br/course/bdd-cucumber-java   
Formação: https://cursos.alura.com.br/formacao-arquitetura-design-projetos-java

Conteúdo:
- Saiba o que é BDD e onde ele se encaixa no desenvolvimento de software
- Aprenda a criar funcionalidades e cenários
- Use o Cucumber e a linguagem Gherkin para criar requisitos executáveis
- Personalize os testes, passe valores e conjuntos de dados
- Integre o Selenium e execute testes End-2-End pelo Cucumber
- Crie testes mais valiosos, orientados ao negócio

### Rodando a aplicação
- Ambiente de Teste:
  - Adicione *-Dspring.profiles.active=test* em VM Option
  - Para popular o banco de dados: http://localhost:8080/db/seed
  - Os usuarios previamente cadastrados estão na classe *DBController*
  - Atualizar a versão do driver do navagador na pasta drivers para refletir a versão correta do seu navegador.
    Para isso acesse a página: http://chromedriver.storage.googleapis.com/index.html ou https://github.com/mozilla/geckodriver/releases	
  - Caso queira mudar o browser é necessário alterar a configuração *System.getProperty* na classe *BrowserFactory* 