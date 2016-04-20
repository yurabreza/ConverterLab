
package com.example.yurab.converterlab.model;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

@Table(name = "OrganizationTypes",id ="_id")
public class OrganizationType extends Model {

 @Column(name = "id")
    private long id;

    @Column(name = "Name")
    private String name;

    public OrganizationType(){
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
