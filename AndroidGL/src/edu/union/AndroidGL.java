package edu.union;

import android.app.Activity;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;

public class AndroidGL extends Activity {
    public static final int ACTIVITY_VIEW = 0;
    
	public static final int FIRST_ID = Menu.FIRST;
	public static final int SECOND_ID = FIRST_ID+1;
	public static final int THIRD_ID = SECOND_ID+1;
	public static final int FOURTH_ID = THIRD_ID+1;
	public static final int FIFTH_ID = FOURTH_ID+1;
	public static final int SIXTH_ID = FIFTH_ID+1;
	public static final int SEVENTH_ID = SIXTH_ID+1;
	public static final int EIGHTH_ID = SEVENTH_ID+1;
	public static final int NINTH_ID = EIGHTH_ID+1;
	public static final int TENTH_ID = NINTH_ID+1;
    public static final int ELEVENTH_ID = TENTH_ID+1;
    public static final int TWELVE_ID = ELEVENTH_ID+1;
	
    public static final String GL_DRAW = "GL_DRAW";
    
    protected boolean isFullscreenOpaque() {
        // Our main window is set to translucent, but we know that we will
        // fill it with opaque data. Tell the system that so it can perform
        // some important optimizations.
        return true;
    }
    
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFormat(PixelFormat.TRANSLUCENT);
		
        setContentView(R.layout.main);
    }
    
    public boolean onCreateOptionsMenu(Menu menu) {
    	boolean res =  super.onCreateOptionsMenu(menu);
    	menu.add(0, FIRST_ID, Menu.NONE, R.string.first);
    	menu.add(0, SECOND_ID, Menu.NONE, R.string.second);
    	menu.add(0, THIRD_ID, Menu.NONE, R.string.third);
    	menu.add(0, FOURTH_ID, Menu.NONE, R.string.fourth);
    	menu.add(0, FIFTH_ID, Menu.NONE, R.string.fifth);
    	menu.add(0, SIXTH_ID, Menu.NONE, R.string.sixth);
    	menu.add(0, SEVENTH_ID, Menu.NONE, R.string.seventh);
    	menu.add(0, EIGHTH_ID, Menu.NONE, R.string.eighth);
    	menu.add(0, NINTH_ID, Menu.NONE, R.string.ninth);
    	menu.add(0, TENTH_ID, Menu.NONE, R.string.tenth);
    	menu.add(0, ELEVENTH_ID, Menu.NONE, R.string.eleventh);
    	menu.add(0, TWELVE_ID, Menu.NONE, R.string.twelve);
    	return res;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	Intent i = new Intent(this, GLActivity.class);
        switch (item.getItemId()) {
        case FIRST_ID:
        	i = i.putExtra(GL_DRAW, GLActivity.FIRST);
        	break;
        case SECOND_ID:
        	i.putExtra(GL_DRAW, GLActivity.SECOND);
        	break;
        case THIRD_ID:
        	i.putExtra(GL_DRAW, GLActivity.THIRD);
        	break;	
        case FOURTH_ID:
        	i.putExtra(GL_DRAW, GLActivity.FOURTH);
        	break;
        case FIFTH_ID:
        	i.putExtra(GL_DRAW, GLActivity.FIFTH);
        	break;
        case SIXTH_ID:
        	i.putExtra(GL_DRAW, GLActivity.SIXTH);
        	break;
        case SEVENTH_ID:
        	i.putExtra(GL_DRAW, GLActivity.SEVENTH);
        	break;
        case EIGHTH_ID:
        	i.putExtra(GL_DRAW, GLActivity.EIGHTH);
        	break;
        case NINTH_ID:
        	i.putExtra(GL_DRAW, GLActivity.NINTH);
        	break;
        case TENTH_ID:
        	i.putExtra(GL_DRAW, GLActivity.TENTH);
        	break;
        case ELEVENTH_ID:
        	i.putExtra(GL_DRAW, GLActivity.ELEVENTH);
        	break;
        case TWELVE_ID:
        	i.putExtra(GL_DRAW, GLActivity.TWELVE);
        	break;        	
        default:
        	i = null;
        }
        if (i != null)
        	this.startActivity(i);
    	return super.onOptionsItemSelected(item);
    }
}