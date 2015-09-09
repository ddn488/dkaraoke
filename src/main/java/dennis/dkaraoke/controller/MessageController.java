package dennis.dkaraoke.controller;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import dennis.dkaraoke.model.SongAction;

/*
 * Create a message-handling controller
 */
@Controller
public class MessageController {

	private Set<String> songQueue = new LinkedHashSet<String>();// to keep the
																// elements in
																// the inserting
																// order.

	@MessageMapping("/song")
	@SendTo("/topic/song")
	public Object[] songActionHandle(SongAction songAction) throws InterruptedException {

		if (songAction.getAction().equals(SongAction.ADD)) {
			songQueue.add(songAction.getSongIndex());
		} else if (songAction.getAction().equals(SongAction.REMOVE) &&
				songQueue.contains(songAction.getSongIndex())) {
				songQueue.remove(songAction.getSongIndex());
		}

		// list of string song indexes is sent to the topic queue where all subscribers will receive
		return songQueue.toArray(); 
	}

	@RequestMapping(value = "/getHostAddress", method = RequestMethod.GET)
	@ResponseBody
	public String getHostAddress() throws UnknownHostException {
		InetAddress ip = InetAddress.getLocalHost();

		// return ip.getHostName();
		return ip.getHostAddress();

	}
}
