package com.shymaaothman.crossworkerstask.presentation.base;


import com.shymaaothman.crossworkerstask.data.server.GenerericResponse;

/**
 * Created by Shymaa Othman on 4/5/2018.
 */

public interface BaseView<T extends BasePresenter> {

    void setPresenter(T presenter);

    void onServerError(GenerericResponse resp);

    void onUnknownError(String message);

    void setLoadingIndicator(boolean b);

    boolean isActive();


}
