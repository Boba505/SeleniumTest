package api.data;

import api.data.RegisterData;

public class UnSuccessRegData extends RegisterData {
    private String error;

    public UnSuccessRegData(String error,
                            String email,
                            String password) {
        super(email,password);
        this.error = error;
    }

    public UnSuccessRegData() {
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
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
