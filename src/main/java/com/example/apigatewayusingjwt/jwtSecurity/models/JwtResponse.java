package com.example.apigatewayusingjwt.jwtSecurity.models;


public class JwtResponse {
    private String jwt;

    public JwtResponse() {
        System.out.println("in response no-arg cnstr");
    }

    public JwtResponse(String jwt) {
        this.jwt = jwt;
        System.out.println("in response param cnstr");
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((jwt == null) ? 0 : jwt.hashCode());
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
        JwtResponse other = (JwtResponse) obj;
        if (jwt == null) {
            if (other.jwt != null)
                return false;
        } else if (!jwt.equals(other.jwt))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "JwtResponse [jwt=" + jwt + "]";
    }
    
}
