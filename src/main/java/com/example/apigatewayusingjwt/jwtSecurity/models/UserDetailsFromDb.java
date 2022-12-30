package com.example.apigatewayusingjwt.jwtSecurity.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "UserInformation")
public class UserDetailsFromDb{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int sl_no;
    private String username;
    private String password;
    private String role;


    public UserDetailsFromDb() {
        System.out.println("in userdetails no-arg cnstr");
    }
    
    public UserDetailsFromDb(String username, String password, String role) {
        System.out.println("in userdetails param cnstr");
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public int getSl_no() {
        return sl_no;
    }
    public void setSl_no(int sl_no) {
        this.sl_no = sl_no;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + sl_no;
        result = prime * result + ((username == null) ? 0 : username.hashCode());
        result = prime * result + ((password == null) ? 0 : password.hashCode());
        result = prime * result + ((role == null) ? 0 : role.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        UserDetailsFromDb other = (UserDetailsFromDb) obj;
        if (sl_no != other.sl_no)
            return false;
        if (username == null) {
            if (other.username != null)
                return false;
        } else if (!username.equals(other.username))
            return false;
        if (password == null) {
            if (other.password != null)
                return false;
        } else if (!password.equals(other.password))
            return false;
        if (role == null) {
            if (other.role != null)
                return false;
        } else if (!role.equals(other.role))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return sl_no + "," + username + "," + password + ","
                + role ;
    }
    
}
