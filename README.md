# Caixas-de-Matrioskas
Projeto 3 para a cadeira "Laboratórios de Programação"
# Projeto
A empresa PackAndGo tem como negócio encaixotar artigos e transportá-los da maneira mais eficiente possível. Recentemente, optaram por entrar no negócio de negociar matrioskas. 
Para transportar as matrioskas, a empresa pode optar por compactá-las para que ocupem o mínimo espaço possível ou transportá-las separadamente.
O desafios desse novo processo de vendas está em: 
- (i) organizar as matrioskas na forma de pilhas, permitindo assim que somente a matrioska mais externa seja acessível;
- (ii) para economizar espaço, deve ser possível juntar dois conjuntos de matrioskas numa só, contanto que não haja matrioskas de tamanhos iguais;
- (iii) conjuntos de matrioskas devem poder ser inseridas em caixas (com exatamente cinco lugares disponíveis);
- (iv) as caixas devem poder ser empilhadas umas sobre as outras.
Pede-se aos alunos de LabP que desenvolvam um programa que permita à empresa modelar o funcionamento deste sistema.
Para este projeto considere que matrioskas individuais podem ser encaixadas uma dentro da outra contanto que o tamanho da que está mais dentro seja inferior ao da mais externa. 
Matrioskas encaixadas umas dentro de outras são tratadas como conjuntos de matrioskas. 
Deve-se considerar que cada matrioska individual tem um peso igual ao seu tamanho.
Deve ser permitido também que, dado dois conjuntos de matrioskas, estes possam ser unidos caso não tenham matrioskas de tamanho igual. 
Por exemplo, considere os conjuntos de matrioskas dos exemplos abaixo. 
O primeiro é composto por matrioskas de tamanhos 5, 4 e 1. 
O segundo, por matrioskas de tamanhos 3 e 2. Depois de juntá-los, temos um único conjunto de matrioskas, com matrioskas de tamanhos 5,4,3,2 e 1.
Tratando-se de uma pilha, a única a que se tem acesso externo é a de tamanho 5.

As caixas do sistema têm todas a mesma capacidade, comportando até cinco (5) conjuntos
de matrioskas, independentemente do respectivo peso. 
No exemplo que analisamos agora, 3 inicialmente tínhamos dois conjuntos de matrioskas.
Depois de juntá-los, temos somente um conjunto de matrioska.
Deve ser permitido ao usuário compactar caixas, isto é, compactar os conjuntos de matrioskas que estão dentro da caixa.
A organização interna da caixa permite ao usuário verificar o estado de qualquer conjunto de matrioska em qualquer ordem.
Contudo, só deve ser permitido remover o último conjunto de matrioskas adicionado.
As caixas também devem poder ser empilhadas. Neste caso, é obrigatório que a caixa embaixo seja sempre mais pesada do que a caixa que está por cima.
Também deve ser permitido empilhar pilhas de caixas; neste caso, a caixa no topo da pilha que ficará embaixo deve ser mais pesada do que caixa mais pesada da pilha que ficará por cima.
# Objetivo / O que aprendi
- Utilizar a estrutura de dados Pilha;
- Utilizar o interface Stack e a classe ArrayStack (estudados na cadeira de AED).
