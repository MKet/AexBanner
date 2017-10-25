package client;

import shared.IEffectenbeurs;

import java.rmi.RemoteException;

public class BannerController {

	private AEXBanner banner;
	private IEffectenbeurs beurs;

	public BannerController(AEXBanner banner) {
		RMIClient client = new RMIClient();
		beurs = client.getbeurs();
        try {
            beurs.addListener(new Listener(this));
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        this.banner = banner;

	}

    public void setKoersen(String koersen) {
        banner.setKoersen(koersen);
    }
}