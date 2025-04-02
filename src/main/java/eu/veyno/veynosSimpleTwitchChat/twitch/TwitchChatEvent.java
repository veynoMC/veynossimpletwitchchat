package eu.veyno.veynosSimpleTwitchChat.twitch;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class TwitchChatEvent extends Event {

    private static final HandlerList HANDLERS = new HandlerList();

    private final String senderName;
    private final String message;
    private final String channelName;
    private final boolean isSubscriber;
    private final long timestamp;

    public TwitchChatEvent(String senderName, String message, String channelName, boolean isSubscriber, long timestamp) {
        this.senderName = senderName;
        this.message = message;
        this.channelName = channelName;
        this.isSubscriber = isSubscriber;
        this.timestamp = timestamp;
    }

    public String getSenderName() {
        return senderName;
    }

    public String getMessage() {
        return message;
    }

    public String getChannelName() {
        return channelName;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public boolean isSubscriber() {
        return isSubscriber;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }

}
