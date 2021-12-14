# MiPokedex

Aplicacion hecha en kotlin que simula una pokedex del anime Pokemon, esta pokedex
muestra los primeros 151 pokemones, al tocar un pokemon se desplega informacion
de dicho pokemon (Nombre, Imagen, Numero en la pokedex, Peso, Altura, 
Experiencia base y Tipo(s))- Para esta aplicacion se utilizo las librerias de 
Retrofit y Glide.

Consideraciones adicionales
En el RecycleView se puede apreciar ademas del nombre del pokemon, su respectiva
imagen y el numero en la pokedex

En la seccion de detalles Se utilizan los sprites de 16 bits

La aplicacion viene con traduccion al idioma ingles

Al iniciar la app se despliega una splashscreen con el nombre del creador de la app

La aplicacion reproduce el sountrack midi del Pokemon center

Al entrar en detalles se reproduce el soundtrack del inicio del juego con el dr. Oak

El audio principal se pausa cuando entra a los detalles y al salir se reanuda donde quedo

Hay un switch para apagar el sonido

Al bloquar la pantalla el audio se pausa y se reanuda al desbloquearla

Al salir de la app se detiene el sonido

Exite un boton oculto en la seccion de detalles debido a que comentarios me indicaron que
el triangulo amarillo parecia un boton para regresar.

Tiene un boton de nombre "Estadisticas / Stats" que despliega las estadisticas base del pokemon
