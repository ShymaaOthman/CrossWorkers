package com.shymaaothman.crossworkerstask.entities;

/**
 * Created by Shymaa Othman on 7/25/2018.
 */

public class Postfields {

    private int postFieldType_id;
    private String content ;
    private Content contentJson ;


    public int getPostFieldType_id() {
        return postFieldType_id;
    }

    public void setPostFieldType_id(int postFieldType_id) {
        this.postFieldType_id = postFieldType_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Content getContentJson() {
        return contentJson;
    }

    public void setContentJson(Content contentJson) {
        this.contentJson = contentJson;
    }
}
