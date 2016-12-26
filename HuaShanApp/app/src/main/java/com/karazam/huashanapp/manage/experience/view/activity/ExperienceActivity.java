package com.karazam.huashanapp.manage.experience.view.activity;

import android.databinding.DataBindingUtil;
import android.text.Html;
import android.view.View;
import android.widget.TextView;

import com.example.utils.base.BaseActivity;
import com.karazam.huashanapp.R;
import com.karazam.huashanapp.databinding.ActivityExperienceBinding;
import com.karazam.huashanapp.manage.experience.model.databinding.ExperienceEntity;
import com.karazam.huashanapp.manage.experience.view.ExperienceView;
import com.karazam.huashanapp.manage.experience.viewmodel.ExperienceViewModel;
import com.karazam.huashanapp.manage.experience.viewmodel.ExperienceViewModelImpl;
import com.karazam.huashanapp.manage.main.model.databinding.Project;
import com.ogaclejapan.rx.binding.Rx;
import com.ogaclejapan.rx.binding.RxProperty;
import com.ogaclejapan.rx.binding.RxView;

/**
 * Created by Administrator on 2016/11/15.
 */

public class ExperienceActivity extends BaseActivity implements ExperienceView{


    private ActivityExperienceBinding binding;
    private ExperienceEntity entity = new ExperienceEntity();
    private ExperienceViewModel mModel;

    @Override
    public void setContentLayout() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_experience);
        mModel = new ExperienceViewModelImpl(entity,this,this,this);
        binding.setEntity(entity);
        binding.setHandler(mModel);
        activities.add(this);
    }

    @Override
    public void dealLogicBeforeInitView() {
        project.set(new Project(1,""));
    }

    @Override
    public void initView() {

    }

    @Override
    public void dealLogicAfterInitView() {
        setLayout();
    }

    /**
     * 设置界面
     */

    public static RxProperty<Project> project =  RxProperty.create();
    private void setLayout() {

        RxView.findById(this, R.id.content_pl).bind(project, new Rx.Action<View, Project>() {
            @Override
            public void call(View target, Project project) {
                TextView name = (TextView) target.findViewById(R.id.det_name);
                TextView income = (TextView) target.findViewById(R.id.det_income);
                TextView time = (TextView) target.findViewById(R.id.det_time);

//                name.setText("你大爷");
//                income.setText("9.30");
                time.setText(Html.fromHtml("投资期限：<big><big><big>3</big></big></big>天"));
            }
        });

    }
}
