package com.paymentchain.businessdomain.transactions.enums;

public enum Channel {
    ATM("ATM", 0),
    POS("POS", 1),
    WEB("WEB", 2),
    MOBILE("MOBILE", 3);

    private String channel;
    private Long channelId;

    Channel(String channel) {
        this.channel = channel;
    }

    Channel(String channel, long channelId) {
        this.channel = channel;
        this.channelId = channelId;
    }
}
