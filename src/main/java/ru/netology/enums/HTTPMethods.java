package ru.netology.enums;

public enum HTTPMethods {
    GET ("GET"),
    POST ("POST"),
    DELETE ("DELETE");

    private String title;

    HTTPMethods(String title) {
        this.title = title;
    }

    public String getTitle(){
        return title;
    }

}


