package com.lx3.learning.pojo;


import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.Date;
import java.util.List;

public class TvSeries {
    @Null
    private int id;

    @NotNull
    private String name;

    @Min(1)
    private int seasonCount;

    //@JsonFormat(shape= JsonFormat.Shape.NUMBER)
    @JsonFormat(timezone="GMT+8", pattern="yyyy-MM-dd")
    @Past
    private Date originRelease;

    @Valid
    @NotNull
    @Size(min=2)
    private List<TvCharacter> tvCharacters;

    public List<TvCharacter> getTvCharacters() {
        return tvCharacters;
    }

    public void setTvCharacters(List<TvCharacter> tvCharacters) {
        this.tvCharacters = tvCharacters;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSeasonCount() {
        return seasonCount;
    }

    public void setSeasonCount(int seasonCount) {
        this.seasonCount = seasonCount;
    }

    public Date getOriginRelease() {
        return originRelease;
    }

    public void setOriginRelease(Date originRelease) {
        this.originRelease = originRelease;
    }

    @Override
    public String toString() {
        return "TvSeries{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", seasonCount=" + seasonCount +
                ", originRelease=" + originRelease +
                ", tvCharacters=" + tvCharacters +
                '}';
    }

    /***
     * 构造器
     * @param id  in
     * @param name String
     * @param seasonCount int
     * @param originRelease Date
     */
    public TvSeries(int id, String name, int seasonCount, Date originRelease) {
        this.id = id;
        this.name = name;
        this.seasonCount = seasonCount;
        this.originRelease = originRelease;
    }

    /***
     *
     */
    public TvSeries() {
    }
}