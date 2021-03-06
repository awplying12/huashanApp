package com.karazam.huashanapp.manage.details_fragment.view.fragment.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.karazam.huashanapp.R;

import java.util.ArrayList;

import rx.Subscriber;
import util.changhongit.com.cacheutils.Cache_RxBitmap.Data;
import util.changhongit.com.cacheutils.Cache_RxBitmap.RxImageLoader;

/**
 * Created by Administrator on 2016/11/11.
 */

public class ImgAdapter extends RecyclerView.Adapter<ImgAdapter.ViewHolder> {

    private ArrayList<String> list = new ArrayList<>();
    private Context context;

    public ImgAdapter(ArrayList<String> list, Context context) {
        this.list = list;
        this.context = context;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_det_img_item,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {   //"https://gss0.baidu.com/9fo3dSag_xI4khGko9WTAnF6hhy/zhidao/pic/item/267f9e2f07082838eda9ce18b899a9014c08f112.jpg"
        RxImageLoader.init(context);

        String url = list.get(position);
        RxImageLoader.getLoaderObservable(null,url).subscribe(new Subscriber<Data>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                Log.i("e","e  :  "+e.toString());
            }

            @Override
            public void onNext(Data data) {

                holder.img.setImageBitmap(data.bitmap);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView img;
        public ViewHolder(View itemView) {
            super(itemView);

            img = (ImageView) itemView.findViewById(R.id.img_item);
        }
    }

    public ArrayList<String> getList() {
        return list;
    }

    public void setList(ArrayList<String> list) {
        this.list = list;
    }
}
