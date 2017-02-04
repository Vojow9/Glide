package info.androidhive.glide.activity;

import java.io.Serializable;

/**
 * Created by Wojtek on 04.02.2017.
 */

public class Comment implements Serializable {

    private String comment;

    public Comment(String comment){
        this.comment = comment;
    }

    public String getComment(){
        return comment;
    }


}
