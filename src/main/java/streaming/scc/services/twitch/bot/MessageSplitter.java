package streaming.scc.services.twitch.bot;

public class MessageSplitter {
	
	
	public Message splittIt(String raw) {
		
		//sollte 3 infos liefern:
		// - badges und irgend nen schrott
		// - user name geschrottet
		// - die message
		String[] split =  raw.split(":");
		
		if(split.length <3) {
			//XXX unexpected
			return null;
		}
		//XXX the length differs but I think either way we are interested in the last two
		int nameIdx = split.length-2;
		int msgIdx = split.length-1;
		
		
		String nameInfo =split[nameIdx];
		//wehe der username hat ein ausrufezeichen
		int bangIndex = nameInfo.indexOf("!");
		if(bangIndex == -1 || bangIndex > nameInfo.length()) {
			//XXX unexpected
			return null;
		}
		String name = nameInfo.substring(0, bangIndex);
		
		
		int privIdx = nameInfo.indexOf("PRIVMSG #");
		String channel = "";
		if(privIdx+9 < nameInfo.length() ) {
			channel = nameInfo.substring(privIdx+9).trim();
		}
		
		
		String message =split[msgIdx];
		
		return new Message(channel, name, message);
	}
	
	
	
	public static void main(String[] args) {
		String testmessage1 = "@badge-info=;badges=broadcaster/1,premium/1;client-nonce=5fd1fbbefd1e4c4a739a6650d6296fde;color=;display-name=d33pfr13d;emotes=;flags=;id=9300de8b-8e90-4adc-aefc-c10c89772d96;mod=0;room-id=197644407;subscriber=0;tmi-sent-ts=1594581510268;turbo=0;user-id=197644407;user-type= :d33pfr13d!d33pfr13d@d33pfr13d.tmi.twitch.tv PRIVMSG #d33pfr13d :TEST ME";
		String testmessage2 = "@badge-info=;badges=;client-nonce=3baa1b5885976983e58f99593dbf5246;color=;display-name=d33pbo7;emotes=;flags=0-2:S.5;id=d757f2b2-03ce-458f-96db-2be18e7142a6;mod=0;room-id=197644407;subscriber=0;tmi-sent-ts=1594581803706;turbo=0;user-id=548953542;user-type= :d33pbo7!d33pbo7@d33pbo7.tmi.twitch.tv PRIVMSG #d33pfr13d :xxx";
		String testmessage3 = "@badge-info=;badges=;client-nonce=fb4cd7e29f1ba7aab5faeefce9e2897e;color=;display-name=d33pbo7;emotes=;flags=;id=e5365fcd-4f49-426f-bc64-7876b6037f29;mod=0;room-id=417170140;subscriber=0;tmi-sent-ts=1594583365027;turbo=0;user-id=548953542;user-type= :d33pbo7!d33pbo7@d33pbo7.tmi.twitch.tv PRIVMSG #brentarus :cool";
		
		MessageSplitter ms = new MessageSplitter();
		
		System.out.println(ms.splittIt(testmessage1));
		System.out.println(ms.splittIt(testmessage2));
		System.out.println(ms.splittIt(testmessage3));
		
	}

}
