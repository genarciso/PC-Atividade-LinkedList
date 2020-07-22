# PC-Atividade-LinkedList

## Autores:
[Gabriel Estevam Narciso](https://github.com/genarciso)

[Júlia Ferreira de Souza Glória](https://github.com/juliafsg)
___
## Objetivos:

* Implementar uma versão concorrente para as operações básicas de ser realizada em uma lista encadeada, que foram as operações de inserção, remoção e busca.
___
## Descrição do problema:

Considere uma lista simplesmente encadeada cujo acesso é compartilhado por três tipos de threads: 

  * o tipo B realiza operações de busca sobre a lista; 

  * o tipo I realiza operações de inserção de itens no final da lista;

   * o tipo R realiza operações de remoção de itens a partir de qualquer posição da lista. 

Threads do tipo B meramente realizam operações de leitura sobre a lista e, portanto, podem ser executadas de forma simultânea com as outras. Por sua vez, as operações de inserção realizadas pelas threads do tipo I devem ser mutuamente exclusivas a fim de impedir que duas threads estejam inserindo itens no final da lista ao mesmo tempo, ainda que seja possível realizar essa operação de forma simultânea a uma operação de busca. Por fim, no máximo uma thread do tipo R pode acessar a lista por vez para realizar remoção de itens e essa operação deve ser mutuamente exclusiva com relação às demais (busca e inserção).
___
## Metodologia:

Foram utilizados a linguagem Java e mecanismos de sincronização de threads, locks e monitores, com a junção de variáveis de condição para chegar uma solução ideal para o problema, no qual é garantido a exclusão mutua entre as regras dadas pelo problema.
___
## Execução:


O programa pode ser executado por meio de uma IDE, como o Eclipse, no qual deve ser importado o projeto e através da IDE utilizada rodar a classe Main.java. Caso deseja rodar por linha de comando, desloque-se dentro da pasta onde localiza-se o Main do projeto (src/main), la pode ser executado o comando 

``javac -d Main.java``

Esse comando irá compilar o projeto java e o seguinte comando pode ser utilizado para executar:

``java Main``
