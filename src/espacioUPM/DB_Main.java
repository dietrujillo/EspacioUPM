//
//
//  Generated by StarUML(tm) Java Add-In
//
//  @ Project : Untitled
//  @ File Name : DB_Main.java
//  @ Date : 5/9/2019
//  @ Author : 
//
//



public class DB_Main extends IDB_Main, IDB_Usuario, IDB_Comunidad, IDB_Publicacion {
	private Object Connection connection;
	private Object DB_Main instancia;
	private void DB_Main() {
	
	}
	
	public DB_Main getInstance() {
	
	}
	
	public Usuario getUsuario(String alias) {
	
	}
	
	public boolean setUsuario(Usuario usuario) {
	
	}
	
	public boolean setPublicacion(Publicacion publi) {
	
	}
	
	public Publicacion[] getPublicaciones(Usuario usuario) {
	
	}
	
	public boolean borrarPublicacion(Publicacion publi) {
	
	}
	
	public String[] getSeguidos(Usuario usuario) {
	
	}
	
	public String[] getSeguidores(Usuario usuario) {
	
	}
	
	public boolean cambiarAlias(Usuario usuario, String aliasNuevo) {
	
	}
	
	public boolean borrarUsuario(String alias) {
	
	}
	
	public boolean borrarMiembroComunidad(String id, String alias) {
	
	}
	
	public boolean insertarMiembroComunidad(String id, String alias) {
	
	}
	
	public boolean seguir(String seguidor, String seguido) {
	
	}
	
	public boolean dejarDeSeguir(String seguidor, String seguido) {
	
	}
	
	public HashMap<String,Integer> puntuar(Usuario usuario, Publicacion publi, int puntuacion) {
	
	}
	
	public Publicacion getPublicacion(String id) {
	
	}
	
	public boolean hacerAdminComunidad(String id, String alias) {
	
	}
	
	public void comentar(Publicacion publi, Usuario usuario, String contenido) {
	
	}
	
	public Usuario[] getMiembros() {
	
	}
}
