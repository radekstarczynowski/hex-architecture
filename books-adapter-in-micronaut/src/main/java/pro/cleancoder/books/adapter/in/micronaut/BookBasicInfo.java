package pro.cleancoder.books.adapter.in.micronaut;

import java.util.UUID;

public record BookBasicInfo(UUID uuid, String title, String author, String gender) {}
