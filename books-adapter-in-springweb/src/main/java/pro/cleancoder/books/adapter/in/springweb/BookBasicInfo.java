package pro.cleancoder.books.adapter.in.springweb;

import java.util.UUID;

record BookBasicInfo(UUID uuid, String title, String author, String gender) {}
