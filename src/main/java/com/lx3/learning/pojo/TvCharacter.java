package com.lx3.learning.pojo;

import javax.validation.constraints.NotNull;

public class TvCharacter {
    @NotNull
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "TvCharacter{" +
                "name='" + name + '\'' +
                '}';
    }

    public TvCharacter(@NotNull String name) {
        this.name = name;
    }
}
