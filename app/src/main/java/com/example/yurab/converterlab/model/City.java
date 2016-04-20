package com.example.yurab.converterlab.model;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

@Table(name = "Cities",id ="_id")
public class City extends Model {


    @Column(name = "Name")
    private String name;

@Column(name = "id")
    private long id;

    public City(){
        super();
    }

    public void setName(String _name) {
        this.name = _name;

    }

    public String getName() {
        return name;
    }

    public void setId(long _id) {
        this.id = _id;
    }

}
