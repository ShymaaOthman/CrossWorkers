/**
 * Copyright (C) 2015 Fernando Cejas Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.shymaaothman.crossworkerstask.domain.base_usecase;


import com.shymaaothman.crossworkerstask.data.server.APIError;
import com.shymaaothman.crossworkerstask.data.server.GenerericResponse;
import com.shymaaothman.crossworkerstask.presentation.base.BaseView;

import java.lang.ref.WeakReference;

import io.reactivex.observers.DisposableObserver;
import retrofit2.HttpException;

/**
 * Created by Shymaa Othman on 25/7/2018.
 */
public abstract class BaseUseCaseObserver<P> extends DisposableObserver<P> {

  private WeakReference<BaseView> weakReference = null;

  public BaseUseCaseObserver(BaseView view) {

    this.weakReference = new WeakReference<>(view);
  }

  protected abstract void onSuccess(P p);

  @Override
  public void onNext(P p) {
    onSuccess(p);
  }

  @Override
  public void onError(Throwable throwable) {

      BaseView view = weakReference.get();
      view.setLoadingIndicator(false);
      if (throwable instanceof retrofit2.HttpException) {
        GenerericResponse responseModel = APIError.parseError(((HttpException) throwable).response()) ;
        view.onServerError(responseModel);
      } else {
        view.onUnknownError(throwable.getMessage());
      }
  }

  @Override
  public void onComplete() {

    if(isDisposed())
     dispose();
  }


}