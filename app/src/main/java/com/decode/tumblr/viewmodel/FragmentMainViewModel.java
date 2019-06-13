package com.decode.tumblr.viewmodel;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.decode.tumblr.api.ApiClient;
import com.decode.tumblr.api.ApiInterface;
import com.decode.tumblr.helpers.DateFunction;
import com.decode.tumblr.model.Data;
import com.decode.tumblr.model.MainHeader;
import com.decode.tumblr.model.Post;

import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.decode.tumblr.App.API_KEY;

public class FragmentMainViewModel extends ViewModel {

    private MutableLiveData<List<Post>> postLiveData = new MutableLiveData<>();
    private int pageIndex = 0;
    private int pageLimit = 20;
    private int totalPosts = 0;
    private MutableLiveData<MainHeader> mainHeaderMutableLiveData = new MutableLiveData<>();

    public MutableLiveData<List<Post>> getPostLiveData() {
        if (postLiveData == null) {
            postLiveData = new MutableLiveData<>();
            fetchPosts();
        }
        return postLiveData;
    }


    public MutableLiveData<MainHeader> getMainHeaderMutableLiveData() {
        return mainHeaderMutableLiveData;
    }

    public void fetchPosts() {
        final ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<Data> call = apiService.getPosts(API_KEY, pageIndex, pageLimit);
        call.enqueue(new Callback<Data>() {
            @Override
            public void onResponse(@NonNull Call<Data> call, @NonNull Response<Data> response) {
                List<Post> results = fetchResults(response);

                if (results != null) {
                    totalPosts = Objects.requireNonNull(response.body()).response.blog.total_posts;
                    postLiveData.setValue(results);

                    MainHeader header = new MainHeader(response.body().response.blog.title,
                            String.valueOf(response.body().response.blog.total_posts),
                            DateFunction.getDateCurrentTimeZone(Long.parseLong(response.body().response.blog.updated)));

                    mainHeaderMutableLiveData.postValue(header);

                }
            }

            @Override
            public void onFailure(@NonNull Call<Data> call, @NonNull Throwable t) {
                // handle error
                Log.e("Error", Objects.requireNonNull(t.getMessage()));
            }
        });
    }

    private List<Post> fetchResults(Response<Data> response) {
        if (response.body() != null) {
            Data data = response.body();
            return data.response.posts;
        }
        return null;
    }
}
