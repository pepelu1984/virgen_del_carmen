package com.dynamicdroides.party.standalone;

import java.util.Date;

import com.dynamicdroides.virgendelcarmen.manager.VirgenDelCarmenManager;

public class TestHibernate {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		VirgenDelCarmenManager manager=new VirgenDelCarmenManager();
		manager.isDataComidasFilled(1, new Date(), "admin", "cbf64a2b1732b9fb06ad298a9057460f");
	}

}
