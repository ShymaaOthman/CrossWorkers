
package com.shymaaothman.crossworkerstask.data.server;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

import java.io.IOException;

import retrofit2.Response;


/**
 * Created by Shymaa Othman on 9/5/2017.
 */

public class APIError {

        public static GenerericResponse parseError(Response<?> response) {

            GenerericResponse generericResponse =null;
            Gson gson = new Gson();
            TypeAdapter<GenerericResponse> adapter = gson.getAdapter(GenerericResponse.class);
                if (response.errorBody() != null) {
                    try {
                        generericResponse = adapter.fromJson(response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                        return generericResponse;

                    }

            }

            return generericResponse;
        }
    }


