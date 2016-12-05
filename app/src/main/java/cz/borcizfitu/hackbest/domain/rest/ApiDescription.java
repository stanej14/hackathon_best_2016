package cz.borcizfitu.hackbest.domain.rest;

import java.util.List;

import cz.borcizfitu.hackbest.domain.model.Item;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Interface for REST API.
 * Created by Jan Stanek[st.honza@gmail.com] on {12.11.16}
 **/
public interface ApiDescription {
    String API_VERSION = "/v0";

    @GET(API_VERSION + "/beststories.json")
    Observable<List<Integer>> obtainBestStoriesList();

    @GET(API_VERSION + "/item/{id}.json")
    Observable<Item> obtainItem(@Path("id") int storyId);
}
