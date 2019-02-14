package com.lambdaschool.cities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class City
{
    private @Id
    @GeneratedValue Long id;
    private String name;
    private long medianHome;
    private int affordability;

    public City()
    {
        // default constructor
    }

    public City(String name, long medianHome, int affordability)
    {
        this.name = name;
        this.medianHome = medianHome;
        this.affordability = affordability;
    }

    public Long getId()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public long getMedianHome()
    {
        return medianHome;
    }

    public void setMedianHome(long medianHome)
    {
        this.medianHome = medianHome;
    }

    public int getAffordability()
    {
        return affordability;
    }

    public void setAffordability(int affordability)
    {
        this.affordability = affordability;
    }
}
