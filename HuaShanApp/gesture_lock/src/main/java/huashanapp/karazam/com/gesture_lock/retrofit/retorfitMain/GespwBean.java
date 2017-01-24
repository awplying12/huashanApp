package huashanapp.karazam.com.gesture_lock.retrofit.retorfitMain;

/**
 * Created by Administrator on 2016/12/21.
 */

public class GespwBean {

    private String userId;
    private String gesPassword;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getGesPassword() {
        return gesPassword;
    }

    public void setGesPassword(String gesPassword) {
        this.gesPassword = gesPassword;
    }

    @Override
    public String toString() {
        return "GespwBean{" +
                "userId='" + userId + '\'' +
                ", gesPassword='" + gesPassword + '\'' +
                '}';
    }
}
