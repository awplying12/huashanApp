package huashanapp.karazam.com.gesture_lock.retrofit.verifypassword;



import huashanapp.karazam.com.gesture_lock.retrofit.retorfitMain.BaseReturn;
import huashanapp.karazam.com.gesture_lock.retrofit.retorfitMain.GespwBean;
import huashanapp.karazam.com.gesture_lock.retrofit.retorfitMain.GespwReturn;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by Administrator on 2016/12/21.
 */

public interface GespasswordAPI {

    @POST("/mobile/uc/setGesPassword")
    Observable<BaseReturn<GespwReturn>> setGespassword(@Body GespwBean bean, @Header("sid") String token, @Header("X-Requested-With") String ID);
}
