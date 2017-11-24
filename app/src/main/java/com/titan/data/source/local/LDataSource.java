/*
 * Copyright 2016, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.titan.data.source.local;

import android.graphics.Bitmap;

import com.titan.model.Pest;

import java.util.List;
import java.util.Set;

/**
 * Main entry point for accessing tasks data.
 */
public interface LDataSource {

    interface uploadCallback {

        void onFailure(String info);

        void onSuccess(String data);
    }


    interface saveCallback {

        void onFailure(String info);

        void onSuccess(String data);
    }

    interface qureyCalllback{
        void onFailure(String info);

        void onSuccess(List<Pest> data);
    }

    interface qureyImgCalllback{
        void onFailure(String info);

        void onSuccess(Bitmap data);
    }




    //查询病虫害
    void queryPest(Integer type, String keyword, Set<String> whbw, qureyCalllback callback);

    //查询主要
    void queryMajorPest(Integer type, qureyCalllback callback);

    //获取病虫害图片
    void getPestImg(String keyword,qureyImgCalllback callback);


}
