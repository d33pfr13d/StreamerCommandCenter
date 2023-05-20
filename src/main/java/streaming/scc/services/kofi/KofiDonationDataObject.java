package streaming.scc.services.kofi;


public class KofiDonationDataObject {
	
	private String verification_token;
	
	private String message_id;
	
	private String timestamp;
	
	private String type;
	
	private boolean is_public;
	
	private String from_name;
	
	private String message;
	
	private String amount;
	
	private String url;
	
	private String email;
	
	private String currency;
	
	private boolean is_subscription_payment;
	
	private boolean is_first_subscription_payment;
	
	private String kofi_transaction_id;
	
	private String shop_items;
	
	private String tier_name;
	
	private String shipping;

	public String getVerification_token() {
		return verification_token;
	}

	public void setVerification_token(String verification_token) {
		this.verification_token = verification_token;
	}

	public String getMessage_id() {
		return message_id;
	}

	public void setMessage_id(String message_id) {
		this.message_id = message_id;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public boolean isIs_public() {
		return is_public;
	}

	public void setIs_public(boolean is_public) {
		this.is_public = is_public;
	}

	public String getFrom_name() {
		return from_name;
	}

	public void setFrom_name(String from_name) {
		this.from_name = from_name;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public boolean isIs_subscription_payment() {
		return is_subscription_payment;
	}

	public void setIs_subscription_payment(boolean is_subscription_payment) {
		this.is_subscription_payment = is_subscription_payment;
	}

	public boolean isIs_first_subscription_payment() {
		return is_first_subscription_payment;
	}

	public void setIs_first_subscription_payment(boolean is_first_suvscription_payment) {
		this.is_first_subscription_payment = is_first_suvscription_payment;
	}

	public String getKofi_transaction_id() {
		return kofi_transaction_id;
	}

	public void setKofi_transaction_id(String kofi_transaction_id) {
		this.kofi_transaction_id = kofi_transaction_id;
	}

	public String getShop_items() {
		return shop_items;
	}

	public void setShop_items(String shop_items) {
		this.shop_items = shop_items;
	}

	public String getTier_name() {
		return tier_name;
	}

	public void setTier_name(String tier_name) {
		this.tier_name = tier_name;
	}

	public String getShipping() {
		return shipping;
	}

	public void setShipping(String shipping) {
		this.shipping = shipping;
	}
	
	

}
