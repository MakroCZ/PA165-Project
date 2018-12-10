package cz.muni.fi.pa165.mm.daolayer.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Marek Barinka; 456295
 */
@Entity
@Table(name="Users")
public class User {
    
    @Column(nullable=false, unique=true)
    @Id
    private String username;
    
    @NotNull
    private String passwordHash;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    @Override
    public int hashCode() {
        int result = 31;
        if (getUsername()== null) {
            return result;
        }
        return result * getUsername().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof User)) {
            return false;
        }
        User other = (User) obj;
        return getUsername().equals(other.getUsername());
    }
}
