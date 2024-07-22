package com.example.demo.model;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/* 
 * L'annotazione @Target viene utilizzata per specificare i tipi di elementi del programma a cui un'annotazione può essere applicata.

ElementType.METHOD: Indica che l'annotazione può essere applicata solo ai metodi. 
In questo modo, l'annotazione @Authorized può essere utilizzata solo sui metodi di una classe, come nei controller per proteggere specifici endpoint.
 * */

@Target(ElementType.METHOD)

/*
 * @Retention L'annotazione @Retention viene utilizzata per specificare quanto
 * tempo un'annotazione deve essere mantenuta.
 * 
 * RetentionPolicy.RUNTIME: Indica che l'annotazione deve essere mantenuta a
 * runtime. Questo significa che l'annotazione sarà disponibile durante
 * l'esecuzione del programma e può essere letta tramite riflessione. Questo è
 * essenziale per l'annotazione @Authorized perché l'aspetto (Aspect) deve poter
 * leggere l'annotazione a runtime per applicare la logica di autorizzazione.
 * Altri valori di RetentionPolicy includono:
 * 
 * RetentionPolicy.SOURCE: L'annotazione è presente solo nel codice sorgente e
 * viene scartata dal compilatore. RetentionPolicy.CLASS: L'annotazione è
 * presente nel bytecode della classe, ma non è disponibile a runtime. Questo è
 * il comportamento predefinito.
 */

@Retention(RetentionPolicy.RUNTIME)
public @interface Authorized {
	// Attributo dell'annotazione per specificare il ruolo
	String role() default "USER";
}
