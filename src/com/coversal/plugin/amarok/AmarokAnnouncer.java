package com.coversal.plugin.amarok;

import com.coversal.ucl.plugin.PluginAnnouncer;


public class AmarokAnnouncer extends PluginAnnouncer {

	public AmarokAnnouncer() {
		defineProfile("Amarok", Amarok.class);
	}

}
