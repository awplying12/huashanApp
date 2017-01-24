package huashanapp.karazam.com.gesture_lock.retrofit.verifypassword;




import huashanapp.karazam.com.gesture_lock.retrofit.retorfitMain.BaseReturn;
import huashanapp.karazam.com.gesture_lock.retrofit.retorfitMain.VerifyPasswordBean;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by Administrator on 2017/1/24.
 */

public interface VerifyPasswordApi {
    @POST("/mobile/uc/verifyPassword")
    Observable<BaseReturn> verifyPassword(@Body VerifyPasswordBean bean, @Header("sid") String token, @Header("X-Requested-With") String ID);
}
