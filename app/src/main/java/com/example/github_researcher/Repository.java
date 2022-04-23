package com.example.github_researcher;

public class Repository {
    private String name;
    private String[] languages;
    private StringBuilder builder;


    public Repository(String name, String[] languages) {
        this.name = name;
        this.languages = languages;
        this.builder = new StringBuilder();
    }

    public void buildLanguages(){

    }


}
