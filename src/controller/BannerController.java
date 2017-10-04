package controller;

import Domain.AEXBanner;
import Domain.IFonds;
import database.IEffectenbeurs;
import database.MockEffectenbeurs;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class BannerController {


	private AEXBanner banner;
	private IEffectenbeurs mockBeurs;
	private Timer timer;

	public BannerController(AEXBanner banner) {
		// TODO - implement BannerController.BannerController
		mockBeurs = new MockEffectenbeurs();
		this.banner = banner;
		timer = new Timer();
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				update();
			}
		},0,1000);

	}

	public void stop() {
		// TODO - implement BannerController.stop
		timer.cancel();
	}

	public void update(){
		List<IFonds> koersen = mockBeurs.getKoersen();
		String sKoers = "";
		for(IFonds f : koersen){
			sKoers += f.toString();
		}
		banner.setKoersen(sKoers);
	}

}