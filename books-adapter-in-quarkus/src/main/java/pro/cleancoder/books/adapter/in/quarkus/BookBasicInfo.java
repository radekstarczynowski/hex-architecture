package pro.cleancoder.books.adapter.in.quarkus;

import java.util.UUID;

record BookBasicInfo(UUID uuid, String title, String author, String gender) {}
