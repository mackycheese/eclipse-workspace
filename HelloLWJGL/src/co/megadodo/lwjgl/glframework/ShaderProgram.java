package co.megadodo.lwjgl.glframework;

import org.lwjgl.*;
import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;
import org.lwjgl.system.*;

import com.hackoeur.jglm.Mat4;

import java.io.File;
import java.nio.*;
import java.util.Scanner;

import static org.lwjgl.glfw.Callbacks.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL12.*;
import static org.lwjgl.opengl.GL13.*;
import static org.lwjgl.opengl.GL14.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL21.*;
import static org.lwjgl.opengl.GL30.*;
import static org.lwjgl.opengl.GL31.*;
import static org.lwjgl.opengl.GL32.*;
import static org.lwjgl.opengl.GL33.*;
import static org.lwjgl.opengl.GL40.*;
import static org.lwjgl.opengl.GL41.*;
import static org.lwjgl.opengl.GL42.*;
import static org.lwjgl.opengl.GL43.*;
import static org.lwjgl.opengl.GL44.*;
import static org.lwjgl.opengl.GL45.*;
import static org.lwjgl.opengl.GL46.*;
import static org.lwjgl.system.MemoryStack.*;
import static org.lwjgl.system.MemoryUtil.*;

public class ShaderProgram implements GLResource  {
	
	public static int compileShader(int type, String strType,String source) {
		int id=glCreateShader(type);
		glShaderSource(id,source);
		glCompileShader(id);
		if(glGetShaderi(id, GL_COMPILE_STATUS)==GL_FALSE) {
			System.out.println("Shader compile error for type "+strType+":\n"+glGetShaderInfoLog(id));
		}
		return id;
	}
	
	public static int compileShaderFiles(int type, String strType, String filename) {
		return compileShader(type,strType,Utilities.loadStrFromFile(filename));
	}

	public int id;
	
	public ShaderProgram() {
		
	}
	
	public void gen() {
		id=glCreateProgram();
	}
	
	public void attach(int shader) {
		glAttachShader(id, shader);
	}
	
	public void link() {
		glLinkProgram(id);
	}
	
	public void bind() {
		glUseProgram(id);
	}
	
	public void unbind() {
		glUseProgram(0);
	}
	
	public void setInt(String paramName,int i) {
		glUniform1i(glGetUniformLocation(id,paramName),i);
	}
	
	public void setTexture(String paramName,Texture t) {
		setInt(paramName,t.id);
	}
	
	public void setMat4(String paramName,Mat4 m) {
		int loc=glGetUniformLocation(id,paramName);
		FloatBuffer buf=BufferUtils.createFloatBuffer(16);
		buf.put(new float[] {m.m00,m.m01,m.m02,m.m03
							,m.m10,m.m11,m.m12,m.m13
							,m.m20,m.m21,m.m22,m.m23
							,m.m30,m.m31,m.m32,m.m33});
		buf.flip();
		glUniformMatrix4fv(loc,true,buf);
	}
	
	public void delete() {
		glDeleteProgram(id);
	}
	
}