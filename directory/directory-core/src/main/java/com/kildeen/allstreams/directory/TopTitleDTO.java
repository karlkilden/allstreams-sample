package com.kildeen.allstreams.directory;

public record TopTitleDTO(long id, String title, int views) {

    public record Highlights(String producer, String rating, String genre) {
    }
}