package com.shymaaothman.crossworkerstask.data.server;

/**
 * Created by Shymaa Othman on 8/30/2017.
 */

public class GenerericResponse {

    private String  code ;
    private String  message ;

    public GenerericResponse(){}

    public GenerericResponse(String code, String message) {
        this.code = code;
        this.message = message;
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

   }
