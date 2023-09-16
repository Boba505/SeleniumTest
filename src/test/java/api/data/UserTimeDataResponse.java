package api.data;

import api.data.UserTimeData;

public class UserTimeDataResponse extends UserTimeData {
    private String updatedAt;
    public UserTimeDataResponse() {
        super();
    }

    public UserTimeDataResponse(String name, String job, String updatedAt) {
        super(name, job);
        this.updatedAt = updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }
}
