# language: pt

Funcionalidade: Propondo lances ao leilao

  Cenario: Propondo um unico lance valido
    Dado um lance valido
    Quando eu proponho um lance no leilao
    Entao o lance e aceito

  Cenario: Propondo varios lances validos
    Dado um lance de 10.00 reais do usuario "fulano"
    E um lance de 15.00 reais do usuario "beltrano"
    Quando eu proponho varios lances no leilao
    Entao os lances sao aceitos

  Esquema do Cenario: Propondo um lancem invalidos
    Dado um lance invalido de <valor> e do usuario '<usuario>'
    Quando eu proponho um lance no leilao
    Entao o lance nao e aceito

    Exemplos:
      | valor | usuario  |
      | 0     | fulano   |
      | -1    | beltrano |

  Cenario: Propondo um sequencia de lances
    Dado dois lances
      | valor | usuario |
      | 10    | fulano  |
      | 15    | fulano  |
    Quando eu proponho varios lances no leilao
    Entao o segundo lance nao e aceito