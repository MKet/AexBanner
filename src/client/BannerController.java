package client;

import shared.IEffectenbeurs;
import shared.IFonds;

import java.rmi.RemoteException;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class BannerController {

	private AEXBanner banner;
	private IEffectenbeurs beurs;
	private Timer timer;

	public BannerController(AEXBanner banner) {
		RMIClient client = new RMIClient();
		beurs = client.getbeurs();
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
		timer.cancel();
	}

	public void update(){
		List<IFonds> koersen = null;
		try {
			koersen = beurs.getKoersen();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		String sKoers = "";
		for(IFonds f : koersen){
			sKoers += f.toString();
		}
		banner.setKoersen(sKoers);
	}

}