package network;

import models.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GetDataService {

    @GET("/product")
    Call<List<Product>> getProducts();
}