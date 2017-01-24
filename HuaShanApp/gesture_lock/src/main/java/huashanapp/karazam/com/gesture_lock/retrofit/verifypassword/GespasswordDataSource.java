package huashanapp.karazam.com.gesture_lock.retrofit.verifypassword;



import huashanapp.karazam.com.gesture_lock.retrofit.retorfitMain.BaseDataSource;
import huashanapp.karazam.com.gesture_lock.retrofit.retorfitMain.BaseReturn;
import huashanapp.karazam.com.gesture_lock.retrofit.retorfitMain.DigestUtils;
import huashanapp.karazam.com.gesture_lock.retrofit.retorfitMain.GespwBean;
import huashanapp.karazam.com.gesture_lock.retrofit.retorfitMain.GespwReturn;
import rx.Observable;

/**
 * Created by Administrator on 2016/12/21.
 */

public class GespasswordDataSource extends BaseDataSource {

    GespasswordAPI service = retrofit1.create(GespasswordAPI.class);

    public Observable<BaseReturn<GespwReturn>> setGesPassword(String gespassword,String uuid,String token){

        gespassword = DigestUtils.encrypt(gespassword);
        GespwBean bean = new GespwBean();
        bean.setUserId(uuid);
        bean.setGesPassword(gespassword);
        return service.setGespassword(bean,token,"XMLHttpRequest");
    }
}
