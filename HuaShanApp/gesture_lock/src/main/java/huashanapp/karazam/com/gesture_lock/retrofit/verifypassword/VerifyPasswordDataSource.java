package huashanapp.karazam.com.gesture_lock.retrofit.verifypassword;



import com.example.utils.utils.StringUtil;

import huashanapp.karazam.com.gesture_lock.retrofit.retorfitMain.BaseDataSource;
import huashanapp.karazam.com.gesture_lock.retrofit.retorfitMain.BaseReturn;
import huashanapp.karazam.com.gesture_lock.retrofit.retorfitMain.DigestUtils;
import huashanapp.karazam.com.gesture_lock.retrofit.retorfitMain.VerifyPasswordBean;
import rx.Observable;

/**
 * Created by Administrator on 2017/1/24.
 */

public class VerifyPasswordDataSource extends BaseDataSource {

    VerifyPasswordApi service = retrofit1.create(VerifyPasswordApi.class);

    public Observable<BaseReturn> verifyPassword(String password,String uuid,String token){
        password = DigestUtils.encrypt(password);
        VerifyPasswordBean bean = new VerifyPasswordBean();
        bean.setPassword(password);
        bean.setUserId(uuid);

        return service.verifyPassword(bean,token,"XMLHttpRequest");
    }
}
