package espacioUPM.Publicaciones;//
//
//  Generated by StarUML(tm) Java Add-In
//
//  @ Project : Untitled
//  @ File Name : PublicacionFactory.java
//  @ Date : 5/9/2019
//  @ Author : 
//
//


import espacioUPM.Database.DB_Main;
import espacioUPM.Database.IDB_Publicacion;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class PublicacionFactory {

	private static final IDB_Publicacion DB = IDB_Publicacion.getInstance();

	// Clase estática - no debe instanciarse
	private PublicacionFactory() {}

	private static boolean isURL(String data) {
		try {
			URL url = new URL(data);
		}
		catch(MalformedURLException e) { return false; }
		return true;
	}

	public static Publicacion createPublicacion(int id, String data,
										 String autor, LocalDateTime fecha,
										 ArrayList<IComentario> comentarios,
										 int numLikes, int numDislikes) {
		if(isURL(data)) {
			return new PublicacionEnlace(id, autor, fecha, comentarios, numLikes, numDislikes, data);
		}
		else {
            Publicacion ref = null;
		    if (data.startsWith("/ref"))
                ref = DB.getPublicacion(Integer.decode(data.split("/ref")[1]));


			if(ref != null) {
				return new PublicacionReferencia(id, autor, fecha, comentarios, numLikes, numDislikes, ref);
			}
			return new PublicacionTexto(id, autor, fecha, comentarios, numLikes, numDislikes, data);
		}
	}

	public static Publicacion createPublicacion(String autor, String data) {
		Publicacion p = null;
		if(isURL(data)) {
			p =  new PublicacionEnlace(autor, data);
		}
		else {
            Publicacion ref = null;
            if (data.startsWith("/ref"))
                ref = DB.getPublicacion(Integer.decode(data.split("/ref")[1]));

			if(ref != null)
				p = new PublicacionReferencia(autor, ref);

			p = new PublicacionTexto(autor, data);
		}
		DB.setPublicacion(p);
		return p;
	}
}
