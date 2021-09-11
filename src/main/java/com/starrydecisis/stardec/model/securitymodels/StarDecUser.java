package com.starrydecisis.stardec.model.securitymodels;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;

@Entity
@Table
public class StarDecUser {

    private static final Logger logger = LoggerFactory.getLogger(StarDecUser.class);


    // TODO -
    // Usually the "roles"/"authorities" are split off into a different table from users
    // As time permits, refactor and split the table in half to follow the normal conventions
    // https://docs.spring.io/spring-security/site/docs/current/reference/html5/#user-schema

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY) // Why use AUTO here instead of IDENTITY?
        private int id;  // Long?
        private String userName;
        private String password;
        private boolean active;
        private String roles;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public boolean isActive() {
            return active;
        }

        public void setActive(boolean active) {
            this.active = active;
        }

        public String getRoles() {
            return roles;
        }

        public void setRoles(String roles) {
            this.roles = roles;
        }

    @Override
    public String toString() {
        return "StarDecUser{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", active=" + active +
                ", roles='" + roles + '\'' +
                '}';
    }
}
