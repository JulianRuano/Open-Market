package co.unicauca.openmarket.commons.domain;

/**
 *
 * @authorbt¡rayan
 */
public class User {
    private int userId;
    private String firstName;
    private String lastName;
    private String username;
    private String contrasenia;
    private String rol;
    //Demas atributos

   

    public User() {
        
    }

    public User(int userId, String firstName, String lastName, String username, String contrasenia, String rol) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.contrasenia = contrasenia;
        this.rol = rol;
    }
    
    public int getUserId() {
        return userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUsername() {
        return username;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public User(String username, String contrasenia) {
        this.username = username;
        this.contrasenia = contrasenia;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }
    
}
