package dab;

public class DABExec {
	private static String serverExec = "C:/Program Files/Java/jdk1.8.0_121/bin/javaw.exe " 
                              + "-Dfile.encoding=Cp1252 " 
                              + "-classpath " 
                              + "C:/Users/ThugLife/workspace/DAB/bin;" 
                              + "C:/SRC/DAB/LWJGL/joml-1.9.2.jar;" 
		                      + "C:/SRC/DAB/LWJGL/lwjgl.jar;" 
                              + "C:/SRC/DAB/LWJGL/lwjgl-glfw.jar;" 
                              + "C:/SRC/DAB/LWJGL/lwjgl-glfw-natives-windows.jar;" 
		                      + "C:/SRC/DAB/LWJGL/lwjgl-natives-windows.jar;" 
		                      + "C:/SRC/DAB/LWJGL/lwjgl-opengl.jar;" 
		                      + "C:/SRC/DAB/LWJGL/lwjgl-opengl-natives-windows.jar " 
		                      + "dab.server.GameServer";
	private static String clientExec = "C:/Program Files/Java/jdk1.8.0_121/bin/javaw.exe " 
                                     + "-Dfile.encoding=Cp1252 " 
		                             + "-classpath " 
		                             + "C:/Users/ThugLife/workspace/DAB/bin;" 
                                     + "C:/SRC/DAB/LWJGL/joml-1.9.2.jar;" 
		                             + "C:/SRC/DAB/LWJGL/lwjgl.jar;" 
		                             + "C:/SRC/DAB/LWJGL/lwjgl-glfw.jar;" 
                                     + "C:/SRC/DAB/LWJGL/lwjgl-glfw-natives-windows.jar;" 
		                             + "C:/SRC/DAB/LWJGL/lwjgl-natives-windows.jar;" 
                                     + "C:/SRC/DAB/LWJGL/lwjgl-opengl.jar;" 
		                             + "C:/SRC/DAB/LWJGL/lwjgl-opengl-natives-windows.jar " 
                                     + "dab.client.GameClient ";
	
	public static String getServerExec() {
		return serverExec;
	}
	
	public static String getClientExec() {
		return clientExec;
	}
}
