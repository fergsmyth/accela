package com.ferg.accelademo.usermanagement.dto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "user")
public class UserXMLDTO {

    private long userId;
    private String firstName;
    private String surname;

    public long getUserId() {
        return userId;
    }

    @XmlElement(required = true)
    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    @XmlElement(required = true)
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurname() {
        return surname;
    }

    @XmlElement(required = true)
    public void setSurname(String surname) {
        this.surname = surname;
    }
}
