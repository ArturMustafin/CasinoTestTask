package com.casino.api.services;


public class EndPoints {
    private static final String TOKEN = "/v2/oauth2/token";
    private static final String LOG_IN = "/v2/players";
    private static final String PLAYER_ID = "/v2/players/";

    public static String getTOKEN() {
        return TOKEN;
    }

    public static String getLOG_IN() {
        return LOG_IN;
    }

    public static String getPLAYER_ID() {
        return PLAYER_ID;
    }
}

