package com.necer.example;

import android.content.Context;

import com.necer.example.bean.Calendar;

import necer.network.RxObserver;

/**
 * Created by necer on 2017/6/29.
 */

public class MVPPresenter {

    private MVPModel mvpModel;
    private MVPView mvpView;

    public MVPPresenter(MVPView mvpView) {
        this.mvpView = mvpView;
        mvpModel = new MVPModel();
    }

    public void getCalendar(Context context,String date, String key,int whichRequest ,boolean isShowDialog) {

        mvpModel.getCalendar(date).subscribe(new RxObserver<Calendar>(context,key,whichRequest,isShowDialog) {
            @Override
            public void onStart(int whichRequest) {
                super.onStart(whichRequest);
                mvpView.onStartLoading(whichRequest);
            }

            @Override
            public void onSuccess(int whichRequest, Calendar calendar) {
                mvpView.onEndLoading(whichRequest);
                mvpView.setResult(calendar);
            }

            @Override
            public void onError(int whichRequest, Throwable e) {
                mvpView.onError(whichRequest, e);
                mvpView.onEndLoading(whichRequest);
            }
        });
    }
}
