package util.changhongit.com.cacheutils.Cache_RxText;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Administrator on 2016/9/9.
 */

public class RealmBean extends RealmObject {

    private long EXPIRE_LIMIT = 8*24*60 * 60 * 1000;    //默认8天
    private String Type;
    private String content;

    @PrimaryKey
    private long mCreateTime;

    public RealmBean() {
        mCreateTime = System.currentTimeMillis();
    }

    public long getEXPIRE_LIMIT() {
        return EXPIRE_LIMIT;
    }

    public void setEXPIRE_LIMIT(long EXPIRE_LIMIT) {
        this.EXPIRE_LIMIT = EXPIRE_LIMIT;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getmCreateTime() {
        return mCreateTime;
    }

    public void setmCreateTime(long mCreateTime) {
        this.mCreateTime = mCreateTime;
    }
}
