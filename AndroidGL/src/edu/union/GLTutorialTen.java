package edu.union;

import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLU;
import android.view.KeyEvent;

/**
 * Approximately corresponds to: http://www.zeuscmd.com/tutorials/opengles/22-Fog.php
 * @author bburns
 *
 */
public class GLTutorialTen extends GLTutorialBase {
	float[] lightPos = new float[] {0,0,3,1};
	float lightAmbient[] = new float[] { 0.3f, 0.3f, 0.3f, 1.0f };
	float lightDiffuse[] = new float[] { 1f, 1f, 1f, 1.0f };

	float matAmbient[] = new float[] { 0.6f, 0.6f, 0.6f, 1.0f };
	float matDiffuse[] = new float[] { 0.6f, 0.6f, 0.6f, 1.0f };
	
	int tex;
	boolean fog;
	
	float fogColor[] = { 0.5f, 0.5f, 0.5f, 1.0f };
	
	float box[] = new float[] {
			// FRONT
			-0.5f, -0.5f,  0.5f,
			 0.5f, -0.5f,  0.5f,
			-0.5f,  0.5f,  0.5f,
			 0.5f,  0.5f,  0.5f,
			// BACK
			-0.5f, -0.5f, -0.5f,
			-0.5f,  0.5f, -0.5f,
			 0.5f, -0.5f, -0.5f,
			 0.5f,  0.5f, -0.5f,
			// LEFT
			-0.5f, -0.5f,  0.5f,
			-0.5f,  0.5f,  0.5f,
			-0.5f, -0.5f, -0.5f,
			-0.5f,  0.5f, -0.5f,
			// RIGHT
			 0.5f, -0.5f, -0.5f,
			 0.5f,  0.5f, -0.5f,
			 0.5f, -0.5f,  0.5f,
			 0.5f,  0.5f,  0.5f,
			// TOP
			-0.5f,  0.5f,  0.5f,
			 0.5f,  0.5f,  0.5f,
			 -0.5f,  0.5f, -0.5f,
			 0.5f,  0.5f, -0.5f,
			// BOTTOM
			-0.5f, -0.5f,  0.5f,
			-0.5f, -0.5f, -0.5f,
			 0.5f, -0.5f,  0.5f,
			 0.5f, -0.5f, -0.5f,
		};
	
	float texCoords[] = new float[] {
			// FRONT
			 0.0f, 0.0f,
			 1.0f, 0.0f,
			 0.0f, 1.0f,
			 1.0f, 1.0f,
			// BACK
			 1.0f, 0.0f,
			 1.0f, 1.0f,
			 0.0f, 0.0f,
			 0.0f, 1.0f,
			// LEFT
			 1.0f, 0.0f,
			 1.0f, 1.0f,
			 0.0f, 0.0f,
			 0.0f, 1.0f,
			// RIGHT
			 1.0f, 0.0f,
			 1.0f, 1.0f,
			 0.0f, 0.0f,
			 0.0f, 1.0f,
			// TOP
			 0.0f, 0.0f,
			 1.0f, 0.0f,
			 0.0f, 1.0f,
			 1.0f, 1.0f,
			// BOTTOM
			 1.0f, 0.0f,
			 1.0f, 1.0f,
			 0.0f, 0.0f,
			 0.0f, 1.0f
		};

	FloatBuffer cubeBuff;
	FloatBuffer texBuff;
	Bitmap bmp;
	
	public GLTutorialTen(Context c) {
		super(c, 20);
		
		cubeBuff = makeFloatBuffer(box);
		texBuff = makeFloatBuffer(texCoords);
		bmp = BitmapFactory.decodeResource(c.getResources(), R.drawable.icon);
		
		setFocusable(true);
	}
	
	protected void init(GL10 gl) {
		gl.glEnable(GL10.GL_LIGHTING);
		gl.glEnable(GL10.GL_LIGHT0);
		
		gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_AMBIENT, lightAmbient,	0);
		gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_DIFFUSE, lightDiffuse,	0);
		gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_POSITION, lightPos, 0);
		
		gl.glEnable(GL10.GL_DEPTH_TEST);
		gl.glClearDepthf(1.0f);
		gl.glEnable(GL10.GL_TEXTURE_2D);
		gl.glEnable(GL10.GL_CULL_FACE);
		
		gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
		gl.glClearDepthf(1.0f);
		
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, cubeBuff);
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glTexCoordPointer(2, GL10.GL_FLOAT, 0, texBuff);
		gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
		
		gl.glShadeModel(GL10.GL_SMOOTH);
		
		tex = loadTexture(gl, bmp);

		gl.glFogf(GL10.GL_FOG_MODE, GL10.GL_EXP);;
		gl.glFogfv(GL10.GL_FOG_COLOR, fogColor, 0);
		gl.glFogf(GL10.GL_FOG_DENSITY, 0.75f);
		gl.glHint(GL10.GL_FOG_HINT, GL10.GL_DONT_CARE);
		gl.glFogf(GL10.GL_FOG_START, 1.0f);
		gl.glFogf(GL10.GL_FOG_END, 5.0f);
		gl.glEnable(GL10.GL_FOG);	
	}
	
	float xrot = 0.0f;
	float yrot = 0.0f;
	
	protected void drawFrame(GL10 gl) {
		if (fog) {
			gl.glEnable(GL10.GL_FOG);
		}
		else {
			gl.glDisable(GL10.GL_FOG);
		}
		
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
		
		gl.glMatrixMode(GL10.GL_MODELVIEW);
		gl.glLoadIdentity();
		GLU.gluLookAt(gl, 0, 0, 3, 0, 0, 0, 0, 1, 0);
		gl.glPushMatrix();
		
		gl.glRotatef(xrot, 1, 0, 0);
		gl.glRotatef(yrot, 0, 1, 0);

		gl.glBindTexture(GL10.GL_TEXTURE_2D, tex);
		gl.glEnable(GL10.GL_TEXTURE_2D);
		gl.glMaterialfv(GL10.GL_FRONT_AND_BACK, GL10.GL_AMBIENT, matAmbient, 0);
		gl.glMaterialfv(GL10.GL_FRONT_AND_BACK, GL10.GL_DIFFUSE, matDiffuse, 0);
		
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, cubeBuff);
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
		gl.glTexCoordPointer(2, GL10.GL_FLOAT, 0, texBuff);

		
		gl.glNormal3f(0,0,1);
		gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, 4);
		gl.glNormal3f(0,0,-1);
		gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 4, 4);
	
		gl.glNormal3f(-1,0,0);
		gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 8, 4);
		gl.glNormal3f(1,0,0);
		gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 12, 4);
		
		gl.glNormal3f(0,1,0);
		gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 16, 4);
		gl.glNormal3f(0,-1,0);
		gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 20, 4);
		
		xrot+=1f;
		yrot+=0.5f;
	}
	
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (event.getKeyCode() == KeyEvent.KEYCODE_DPAD_LEFT) {
			fog = true;
		}
		else if (event.getKeyCode() == KeyEvent.KEYCODE_DPAD_RIGHT) {
			fog = false;
		}	
		return super.onKeyDown(keyCode, event);
	}
}