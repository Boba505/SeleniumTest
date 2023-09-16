package api.data;

import api.data.RegisterData;

public class SuccessRegData extends RegisterData {
    private Integer id;
    private String token;

    public SuccessRegData() {
        super();
    }

    public SuccessRegData(String email,
                          String password,
                          Integer id,
                          String token) {
        super(email,password);
        this.id = id;
        this.token = token;

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getEmail(){
        return super.getEmail();
    }

    public void setEmail(String email){
        super.setEmail(email);
    }

    public String getPassword(){
        return super.getPassword();
    }

    public void setPassword(String password){
        super.setPassword(password);
    }
}
