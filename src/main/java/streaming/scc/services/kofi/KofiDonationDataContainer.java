package streaming.scc.services.kofi;

/**
 * War ne idee, falls man alles automatisch hätte mappen können, aber ich hacke mir jetzt doch was zusammen...
 * @author jonas
 *
 */
@Deprecated
public class KofiDonationDataContainer {

	private KofiDonationDataObject data;

	public KofiDonationDataObject getData() {
		return data;
	}

	public void setData(KofiDonationDataObject data) {
		this.data = data;
	}
	
}
