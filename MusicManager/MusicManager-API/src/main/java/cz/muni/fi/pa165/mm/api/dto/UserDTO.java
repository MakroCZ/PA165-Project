package cz.muni.fi.pa165.mm.api.dto;

/**
 *
 * @author Marek Barinka; 456295
 */
public class UserDTO {
    
    private String username;
  
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
        if (getUsername().isEmpty()) {
            return result;
        }
        return result * getUsername().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof UserDTO)) {
            return false;
        }
        UserDTO o = (UserDTO) obj;
        return getUsername().equals(o.getUsername());
    }
}
