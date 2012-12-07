/*
 * Amarok.java
 *
 * Amarok plugin for Coversal (http://www.coversal.com)
 *
 * Copyright (C) 2012 Zokama <zokama@coversal.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 */


package com.coversal.plugin.amarok;

import com.coversal.plugin.sshlib.DBusController;
import com.coversal.plugin.sshlib.SshProfile;
import com.coversal.ucl.api.ControllerAPI;
import com.coversal.ucl.api.TextParameter;
import com.coversal.ucl.plugin.ProfileAnnouncer;


public class Amarok extends SshProfile {

	public  final static String PROFILE_NAME = "Amarok";
	private final static String LAYOUT_NAME = "coversal1";
	
	class AmarokController  extends DBusController {
		
		public AmarokController() {
			super(Amarok.this);
			defineParameter(DBUS_SERVICE, new TextParameter("org.mpris.amarok", true));
			
			defineCommand(START_PLAY, "amarok -p %s", true);
			defineCommand(PLAY_FOLDER, "amarok -p %s", true);
			defineCommand(ADD_TO_PLAYLIST, "amarok -a %s", true);
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

	
	

	public Amarok(ProfileAnnouncer pa) {
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
	public ControllerAPI getController() {
		return controller;
	}

//	@Override
//	public PlaylistManager getPlaylistManager() {
//		return null;
//	}
}
