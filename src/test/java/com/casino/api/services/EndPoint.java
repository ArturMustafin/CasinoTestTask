package com.casino.api.services;

public enum EndPoint {
    TOKEN("/v2/oauth2/token"),
    LOG_IN("/v2/players"),
    PLAYER_ID("/v2/players/");

    private final String path;

    EndPoint(String path) {
        this.path = path;
    }

    public String toString() {
        return path;
    }
}
