# 01. Introdução ao BDD
    
Quando estamos escrevendo uma aplicação, é importante garantir que o código que foi implementado funcione corretamente e para tal usamos testes.   
Para cada regra de negócio, realizamos testes que cobrem os casos de uso do sistema.  
Existem algumas formas de fazer esses testes.   
Uma forma é imprimir mensagens no console e ir comparando o resultado.   
Outra forma é utilizar uma biblioteca de testes como junit ou testng.  
Qual a vantagem de utilizar uma biblioteca de testes a conferir manualmente os testes?  

- Utilizando uma biblioteca de testes, temos um feedback mais rápido e intuitivo sobre os testes.  
  Quando usamos uma biblioteca as respostas sobre o resultados dos testes é mais direta.    
  Dessa forma, temos um feedback mais rápido sobre a saúde do sistema.  
  
Resumo:
- O que é BDD (o Behaviour Driven Development)
  - BDD tenta melhorar a comunicação e colaboração
  - BDD tenta aproximar o negócio e criar um entendimento melhor como a aplicação deveria funcionar
- Existem vários tipos e níveis de testes, como por exemplo:
   - testes de unidade
   - testes de integração
   - testes ponta a ponta (end-to-end)


## 02. Escrevendo Features com Cucumber

Nomeando os testes:
Como vimos na aula, os métodos de testes precisam ser semânticos.   
Em muitos casos, os testes são utilizados como a documentação do código no projeto.  
Existem algumas formas de nomeação, contudo, em muitos casos os times definem qual padrão utilizar.   
O importante é que o nome dos métodos sejam expressivos e que o padrão escolhido seja seguido.  
Para conhecer outras formas comuns de nomear os testes, neste link https://dzone.com/articles/7-popular-unit-test-naming de um artigo em inglês mostra algumas formas de nomeação.  

Como o Cucumber sabe qual método tem que executar para cada passo (step)?
- Baseado na anotação logo acima do método.
  Repare que os métodos possuem anotações como @Dado, @Quando e @Entao. 
  Essas anotações informam ao Cucumber que esses métodos devem ser executados quando um passo com uma descrição que bata com o parâmetro passado para essas anotações for encontrado.

Apesar de ser uma ferramenta de testes, o Cucumber não fornece nenhuma ferramenta para efetivamente verificar condições.  
Para isso devemos utilizar o Junit que traz inúmeros métodos interessantes de verificação, a partir da classe org.junit.Assert  
Essa classe fornece métodos estáticos muito simples que verificam se o parâmetro é verdadeiro, se há igualdade etc, como por exemplo:  
```
Assert.assertEquals(Object expected, Object actual)
```
Para saber mais sobre essa função você pode conferir a documentação oficial: https://junit.org/junit4/javadoc/4.8/org/junit/Assert.html  
e a documentação do Cucumber: https://cucumber.io/docs/cucumber/checking-assertions/

Resumo:
- Como integrar a biblioteca Cucumber na aplicação
- Cucumber pode ser inicializado a partir do junit4 (@RunWith)
- os arquivos .feature são analisados pelo Gherkin e Cucumber
    - Gerkin é uma linguagem para definir os .feature
    - Cucumber gera e roda os passos (steps) associados ao .feature
- dentro do .feature escrevemos a funcionalidade e os critérios de aceitação
- um critério de aceitação segue a estrutura de um teste (passos ou steps)
  - os passos são Given-When-Then ou Dado-Quando-Entao
- cada passo será implementado por um método anotado (step)


## 03. Mais cenários e parâmetros

*Cucumber Expression vs RegExp:*
Já vimos como definir valores nos passos dentro do cenário, por exemplo:
```
Dado um lance de 10.0 reais do usuario "fulano"
```

No código Java usamos uma expressão delimitado por ```{}``` para definir o tipo de parâmetro:
```
@Dado("um lance de {double} reais do usuario {string}")
public void um_lance(Double valor, String nomeUsuario) {
//codigo omitido
```

Esse forma de definir os parâmetros se chama de *Cucumber Expression*.

Obs: Lembrando que o nome do método não importa.

*Regex*

Existe uma alternativa ao Cucumber Expression, os famosos *expressões regulares*. 
Em lugar da Cucumber Expression podemos usar uma regex, e assim temos todo o poder dessas expressões para definir mais detalhe sobre o formato do parâmetro, por exemplo:

```
@Dado("um lance de {double} reais do usuario {string}")
public void um_lance_de_reais_do_usuario_fulano(Double valor, String nomeUsuario) {
//resto o mitido
```

Se torna:

```
@Dado("^um lance de (\\d+[.]\\d\\d?) reais do usuario (\\w+)$")
public void um_lance_de_reais_do_usuario_fulano(Double valor, String nomeUsuario) {
System.out.println(valor);
System.out.println(nomeUsuario);
}
```

Repare que os *expressões regulares* tem a sua própria complexidade. 
Em alguns momentos ajudam, em outros fica mais complicado como é o caso da definicao de um número decimal (```(\\d+[.]\\d\\d?)```).

Se gostaria de dominar os regex, aconselhamos assistir o curso abaixo: https://cursos.alura.com.br/course/expressoes-regulares

*Cuidado com os Hooks!*

Vimos no vídeo as anotações ```@After``` e ```@Before``` usado em métodos nas classes que implementam os passos de um cenário.  
Esse métodos também são chamados de Hooks (ganchos) que nada mais são métodos chamados automaticamente quando algum evento acontece.   
No caso, o evento é a execução de um cenário.  
Também existem os hooks ```@BeforeStep``` e ```@AfterStep```.   
Como o nome já indica, nessas anotações o evento é a execução de um step.   
Ou seja, cada vez que um métodos anotado com ```@Dado``` ```@Quando``` ou ```@Entao``` é chamado, será chamado o hook (antes ou depois, dependendo da anotação).  
Em geral, vale a pena alertar que devemos ter cuidado com o uso de hooks pois esses métodos não ficam visíveis para quem lê apenas o arquivo ```.feature```.  

Resumo:
- um arquivo ```.feature``` pode ter vários cenários e passos (steps)
- os métodos associado aos passos são reaproveitados entre cenários
  - podemos passar parâmetros do cenário ao método
- Cucumber possui anotações para inicializar (```@Before```) e finalizar (```@After```) o cenários
  - os métodos anotados com ```@Before``` e ```@After``` são chamados de Hooks
  - cuidado, pois os Hooks não são visíveis no arquivo ```.feature```
  
  
## 04. DataTables e Exemplos

Tabela de uma coluna

Vimos o use e o poder de Data Tables quando precisamos inicializar vários dados de uma vez só.  
Quando temos apenas uma lista de valores, ou seja, uma tabela de uma coluna, podemos simplificar mais ainda e receber uma lista de valores. Vejo o exemplo:  
```
Cenario: Propondo um varios lances validos
 Dado varios lances validos
  | 10.0 
  | 15.0 
  | 17.0 

```

```
@Dado("varios lances validos")
public void dado_um_lance_valido(Lista<String> valores) {
    System.out.println(valores);
    //codigo omitido
}
```

Repare que recebemos um ```List<String>``` como parâmetro em lugar da DataTable.   
Assim fica mais simples de usar e acessar.

Resumo:
- como usar Exemplos para alimentar o mesmo teste com dados diferentes
- como usar DataTables para passar vários dados ao teste de uma vez só
- usar o plugin do “Cucumber” no Intellij


## 05. Integração com Selenium

O que é um teste E2E?
- É um teste que testa todo o fluxo da aplicação pela perspectiva de um usuário real.  
  E2E (end-to-end) traduzido como ponta a ponta, é um teste que realmente utiliza a aplicação como se fosse um usuário real.   
  Se é uma aplicação web, como no nosso caso, abre um navegador, interage com links, formulários, etc.

Ao fazermos uma bateria de testes com o Selenium estamos navegando pela aplicação como uma pessoa faria. 
Isso trás algumas consequências para nossos teste e de como precisamos organizá-los.
Quais as alternativas corretas em relação a como precisamos organizar os testes.
- Para iniciarmos um teste precisamos colocar o aplicativo em um estado inicial conhecida e a partir dai podemos começar o teste.
  É muito importante deixar o sistema em um estado inicial conhecido e comum entre todos os testes daquela feature.

- É mais complicado fazer com que os testes não tenham interdependência e por isso precisamos tomar cuidado em qual estado o sistema está ao acabarmos o teste. 
  Uma estratégia é que dentro de uma mesma feature todos os testes acabem na mesma tela em que eles começaram ou todos iniciam a partir da tela inicial.

*Fixando conhecimento*
- Testes de UI devem ser criados em menor quantidade que testes de unidade.
- O feijão com arroz do uso do Selenium WebDriver compreende descobrir a melhor estratégia de captura de um elemento HTML que se deseja interagir e usar a classe By para criar objetos do tipo WebElement através do método findElement().
- Mudanças no HTML são constantes e necessárias. A aplicação web deve evoluir à medida que clientes e usuários aumentam seu entendimento sobre suas demandas. Por isso é necessário escrever código de teste que minimize o impacto dessas mudanças e o padrão Page Object existe para cumprir esse objetivo.

Resumo:
- que PageObject é um padrão de projeto para encapsular o acesso a uma pagina da aplicação
  - todo o código especifico da interface com Selenium fica dentro do PageObject
- não devemos usar Selenium diretamente nas classe de "steps" do Cucumber
- o teste, mesmo com Selenium, deve sempre começar a partir de estado "limpo"
- a melhor estrategia de buscar um elemento na interface é usar a ID


## 06. Tags e Contexto

Como rodar apenas os testes que possuem a tag Important mas não são Smoketests?

```
@CucumberOptions(...,
tags = "@Important and not @Smoketest")
```

Podemos usar a expressão lógica an not.

*Para saber mais: Steps com Lambda*

O Cucumber oferece uma alternativa ao uso das anotações como ```@Dado``` ou ```@Quando```.   
Podemos implementar todos os steps de um cenários usando as famosas expressões lambda do Java8!

Para tal, devemos definir uma dependência extra dentro do nosso ```pom.xml```:
```
<dependency>
    <groupId>io.cucumber</groupId>
    <artifactId>cucumber-java8</artifactId>
    <version>${cucumber.version}</version>
    <scope>test</scope>
</dependency>
```

Uma vez feita, podemos rescrever a classe com os steps.   
Importante é implementar a interface ```io.cucumber.java8.Pt ``` que possui para cada anotacao um método.   
Todos os métodos devem ser definidos dentro do construtor da classe. Vejo o exemplo:

```java
public class LeilaoSteps implements io.cucumber.java8.Pt {
  private LoginPage loginPage;
  private LeiloesPage leiloesPage;
  private NovoLeilaoPage novoLeilaoPage;
  private Browser browser;

  public LeilaoSteps() {
    Dado("um usuario logado", () -> {
      this.browser = new Browser();
      browser.seed();
      loginPage = browser.getLoginPage();
      leiloesPage = loginPage.realizaLoginComoFulano();
    });

    Quando("acessa a pagina de novo leilao", () -> {
      novoLeilaoPage = this.leiloesPage.visitaPaginaParaCriarUmNovoLeilao();
    });

    //resto omitido
  }
}
``` 
O codigo completo dessa classe pode ser encontrado no [link](https://gist.github.com/steppat/64eae8b2005345c71f23b78941bf2a2e)


Agora, o que é o BDD, na prática?
- É unir as equipes técnicas e não técnicas para definir os comportamentos necessários do software, e a partir dessas definições, desenvolver.  
  Essa pode ser uma definição simplória de BDD, e repare que testes nem faz parte dessa definição. Para garantir que o que você desenvolveu está de acordo com a funcionalidade descrita, nós testamos.  
  O ponto principal é colaboração para melhorar o entendimento das funcionalidades a implementar.  

Resumo:
- como usar de tags para marcar ou agrupar funcionalidades e cenários
  - tags podemos ser escolhidas pela anotação @CucumberOptions ou na linha de comando
- como definir um contexto (Background) no arquivo .feature
  - um contexto define um passo comum entre todos os cenários dessa funcionalidade

