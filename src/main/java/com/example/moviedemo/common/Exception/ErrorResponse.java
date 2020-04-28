package com.example.moviedemo.common.Exception;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlRootElement;

@Getter
@Setter
@XmlRootElement(name = "error")
public class ErrorResponse 
{
    public ErrorResponse(String message) {
        super();
        this.message = message;
    }
 
    //General error message about nature of error
    private String message;

}