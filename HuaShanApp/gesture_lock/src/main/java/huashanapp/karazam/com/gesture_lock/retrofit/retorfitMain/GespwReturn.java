package huashanapp.karazam.com.gesture_lock.retrofit.retorfitMain;

/**
 * Created by Administrator on 2016/12/21.
 */

public class GespwReturn {

    private String gesPassword;

    public String getGesPassword() {
        return gesPassword;
    }

    public void setGesPassword(String gesPassword) {
        this.gesPassword = gesPassword;
    }

    @Override
    public String toString() {
        return "GespwReturn{" +
                "gesPassword='" + gesPassword + '\'' +
                '}';
    }
}
