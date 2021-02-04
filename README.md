
# SumaTres

Proyecto Grupo IP - PL4 2020

La versión entregada fue la versión 13, el 14/01/2021.

SumaTres es un simple juego que trata de conseguir la mayor puntuación posible
sumando piezas. El valor de las fichas siguen un patrón, comenzando por el 1,
el 2, y el 3. A partir de aquí, las fichas consecutivas se multiplican por dos.
es decir: 6, 12, 24, etc. Solo las fichas que sean iguales (excepto el 1 y el 2,
que solo se pueden sumar entre sí) se pueden sumar. La partida termina cuando el
tablero está lleno y el jugador no tiene la posibilidad de sumar ninguna ficha.

Aunque las reglas básicas son estas, existen dos modos ligeramente diferentes con
los que jugar a SumaTres:

- El modo clásico sigue con todas las reglas establecidas en el enunciado del
trabajo original. Existen cuatro sentido de movimientos para las jugadas, la
consola está activada por defecto, se generan tres fichas por defecto al principio
de la partida (1, 2 y 3) y solo se pueden generar esos tres valores como fichas
aleatorias. Por lo general, el modo clásico mantiene el estado del programa al
ser entregado para corrección.

- El modo experimental incluye variaciones con respecto al sistema clásico del
enunciado. Al comenzar la partida, se generan en pantalla una cantidad de piezas
dependente al tamaño del tablero en sí. Además, las siguientes fichas generadas
dependen de la ficha máxima en pantalla, con lo que el juego se facilita con lo
que la partida sigue. En este modo, la consola está deshabilitada por defecto,
con lo que solo se puede observar la partida mediante la ventana gráfica. El
cambio más importante con respecto al modo clásico es la existencia de ocho
sentidos del movimiento frente a cuatro, añadiendo la posibilidad de mover las fichas diagonalmente. Los resultados de las partidas en modo experimental se guardan en el archivo 'resultados.txt' en el directorio actual. El modo experimental es eso, experimental, por lo que puede haber cambios más adelante y, más importante, puede que haya funciones que no estén implementadas correctamente.

Personalmente, recomiendo jugar en modo 'experimental' con un tablero pequeño,
como 5x5. Esto genera partidas más fluidas y cortas. El modo clásico es ligeramente
más frustrante, ya que no hay tantas opciones de movimiento ni de ayudas. Cuanto
más grande sea el tablero, más difícil es llegar al final de la partida. En pantallas de 1920x1080, el tamaño máximo del tablero es de 14x29. Estas partidas suelen tardar entre 20000 y 100000 turnos en terminar, por lo que el objetivo no es llegar al final, sino alcanzar la máxima puntuación y la ficha máxima más grande posible.
