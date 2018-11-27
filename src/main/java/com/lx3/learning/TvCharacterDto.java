package com.lx3.learning;

import javax.validation.constraints.NotNull;

public class TvCharacterDto {
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
        return "TvCharacterDto{" +
                "name='" + name + '\'' +
                '}';
    }

    public TvCharacterDto(@NotNull String name) {
        this.name = name;
    }
}
