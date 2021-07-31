package com.example.demo.exception;

public class PrayerListNotFoundException extends RuntimeException {
    public PrayerListNotFoundException(String msg, Throwable e) {
        super(msg, e);
    }
    public PrayerListNotFoundException(String msg) {
        super(msg);
    }
}
