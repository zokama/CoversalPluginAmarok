package com.coversal.plugin.amarok;

import android.os.RemoteException;

import com.coversal.plugin.sshlib.DBusController;
import com.coversal.plugin.sshlib.Ssh;
import com.coversal.ucl.api.ControllerAPI;
import com.coversal.ucl.api.TextParameter;
import com.coversal.ucl.plugin.PluginAnnouncer;


public class Amarok extends Ssh {

	public  final static String PROFILE_NAME = "Amarok";
	private final static String LAYOUT_NAME = "SSHmote";
	
	class AmarokController  extends DBusController {
		
		public AmarokController() {
			super(Amarok.this);
			defineParameter(DBUS_SERVICE, new TextParameter("org.mpris.amarok", true));
			
			defineCommand(START_PLAY, "amarok -p %s", true);
			defineCommand(PLAY_FOLDER, "amarok -p %s", true);
			defineCommand(ENQUEUE_FOLDER, "amarok -a %s", true);
			defineCommand(PLAY_PAUSE, "/Player Pause", true);
			defineCommand(STOP, "/Player Stop", true);
			defineCommand(POWER, "", true);
			defineCommand(REWIND, "/Player Backward 5000", true);
			defineCommand(FORWARD, "/Player Forward 5000", true);
			defineCommand(VOL_UP,	"/Player VolumeUp 10", true);
			defineCommand(VOL_DOWN, "/Player VolumeDown 10", true);
			defineCommand(NEXT, "/Player Next", true);
			defineCommand(PREVIOUS, "/Player Prev", true);
			defineCommand(FULLSCREEN, "/Player ToggleFullScreen", true);
			defineCommand(PROGRAM_START, "amarok", true);
			
			defineKey(POWER, PROGRAM_START, false, "");
			
		}

		
		@Override
		public String getLayoutName() {
			return LAYOUT_NAME;
		}
	}


	public Amarok(PluginAnnouncer pa) {
		super(pa);
		
		setOptionValue(OPTION_STARTUP, START_OPTION_REMOTE);
	}
	
	@Override
	public String getProfileName() {
		return PROFILE_NAME;
	}

	@Override
	public String getIconName(){
		return "amarok";
	}

	AmarokController controller = new AmarokController();
	
	@Override
	public ControllerAPI getController() throws RemoteException {
		return controller;
	}
}
