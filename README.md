![Codacy grade](https://img.shields.io/codacy/grade/645541a0086c40c2af9bdcf53859de47) ![GitHub repo size](https://img.shields.io/github/repo-size/miermontoto/SumaTres) 
# SumaTres
Proyecto de grupo, IP - PL4 2020

La versión entregada fue la [versión 13](https://github.com/miermontoto/SumaTres/tree/f85b0cb72ed033d36211e62354214de6f3cf8d31), el 14/01/2021.

SumaTres es un simple juego que trata de conseguir la mayor puntuación posible
sumando piezas adyacentes del mismo valor, excepto las piezas 1 y 2 que solo
se pueden sumar entre sí. La partida termina cuando el tablero esté lleno y el
jugador no tenga la posibilidad de sumar ninguna ficha.

Aunque las reglas básicas son estas, existen dos modos ligeramente diferentes con
los que jugar a SumaTres:

-   El modo clásico sigue con todas las reglas establecidas en el enunciado del
trabajo original. Existen cuatro sentidos de movimientos para las jugadas
(arriba, abajo, izquierda, derecha), la salida por consola está activada,
se generan tres fichas por defecto al principio de cada partida (1, 2 y 3)
y solo se pueden generar esos tres valores como fichas aleatorias. Por lo
general, el modo clásico mantiene el estado del programa al ser entregado
para corrección.

-   El modo experimental incluye variaciones con respecto al sistema clásico
del enunciado. Al comenzar la partida, se generan en pantalla una cantidad
de piezas dependiente del tamaño del tablero en sí. Además, las siguientes
fichas generadas dependen de la ficha máxima en pantalla, con lo que el
juego se facilita al conseguir fichas más grandes. En este modo, la consola
está deshabilitada por defecto, con lo que solo se puede ver el tablero
mediante la ventana gráfica. El cambio más importante con respecto al modo
clásico es la existencia de ocho sentidos del movimiento frente a cuatro en
el modo clásico, añadiendo la posibilidad de mover las fichas diagonalmente.
Los resultados de las partidas en modo experimental se guardan en un archivo
de texto. El modo experimental es eso, experimental, por lo que puede haber
cambios más adelante y, más importantemente, puede que haya funciones que no
estén implementadas correctamente.

Personalmente, recomiendo jugar en modo 'experimental' con un tablero pequeño,
como 5x5. Esto genera partidas más fluidas y cortas. El modo clásico es
ligeramente más frustrante, ya que no hay tantas opciones de movimiento ni de
ayudas. Cuanto más grande sea el tablero, más difícil es llegar al final de
la partida. En pantallas de 1920x1080, el tamaño máximo del tablero es de
14x29. Estas partidas suelen tardar entre 20000 y 100000 turnos en terminar,
por lo que el objetivo no es llegar al final, sino alcanzar la máxima puntuación
y la ficha máxima más grande posible.

<p xmlns:cc="http://creativecommons.org/ns#" xmlns:dct="http://purl.org/dc/terms/"><a property="dct:title" rel="cc:attributionURL" href="https://github.com/miermontoto/SumaTres">SumaTres</a> by <a rel="cc:attributionURL dct:creator" property="cc:attributionName" href="https://github.com/miermontoto/">Juan Mier</a> is licensed under <a href="http://creativecommons.org/licenses/by-nc-sa/4.0/?ref=chooser-v1" target="_blank" rel="license noopener noreferrer" style="display:inline-block;">CC BY-NC-SA 4.0
