package iot.nvipash_harvardapi.http_client;

import iot.nvipash_harvardapi.entities.Record;
import iot.nvipash_harvardapi.entities.RecordsList;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GetRecordsData {
    @GET("Object?size=100&&apikey=9b5e1c90-bf82-11e8-ab54-f317b53c5d59")
    Call<RecordsList> getRecordsData();

    @GET("object/{id}?apikey=9b5e1c90-bf82-11e8-ab54-f317b53c5d59")
    Call<Record> getRecordWithId(@Path("id") int id);
}