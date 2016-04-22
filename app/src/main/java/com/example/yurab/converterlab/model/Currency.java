package com.example.yurab.converterlab.model;

import com.activeandroid.Model;

//@Table(name = "Currencies",id ="_id")
public class Currency  extends Model {
 //@Column(name = "id")
    private long id;
   // @Column(name = "Name")
    private String name;


    public  Currency(){
        super();
    }

    public void setId(long _id) {
        this.id = _id;
    }



    public void setName(String _name) {
        this.name = _name;
    }

    public String getName() {
        return name;
    }
}
