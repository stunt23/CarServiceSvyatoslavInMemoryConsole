package org.example.Entity;

import lombok.Data;
import org.example.EnamStatus.RepairerStatus;

@Data
public class Repairer {

    private long id;
    private String name;
    private RepairerStatus status;

    private Repairer(String name){
        this.name = name;
        this.status = RepairerStatus.IS_FREE;
    }

    public static Repairer repairerCreator(String name){
        if( !(name.isBlank() || name.isBlank()) ){
            return new Repairer(name);
        }
        System.out.println("Bad name");
        return null;
    }



}
