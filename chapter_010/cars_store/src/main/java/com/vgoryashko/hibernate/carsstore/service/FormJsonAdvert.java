package com.vgoryashko.hibernate.carsstore.service;

import com.vgoryashko.hibernate.carsstore.models.cars.Car;
import com.vgoryashko.hibernate.carsstore.models.items.Advertisement;
import com.vgoryashko.hibernate.carsstore.models.items.Photo;
import com.vgoryashko.hibernate.carsstore.models.parts.Part;

import javax.json.*;
import java.util.List;

/**
 * Class that creates JSON object from a given List of Advertisement.
 *
 * @author Vlad Goryashko
 * @version 0.1
 * @since 3/23/18
 */
public class FormJsonAdvert {

    public JsonArray returnArray(List advertisements) {

        JsonArray jsonArray = null;

        if (advertisements != null) {
            JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
            JsonArrayBuilder arrayBuilderParts = Json.createArrayBuilder();
            JsonObjectBuilder advertBuilder = Json.createObjectBuilder();
            JsonArrayBuilder arrayBuilderPhotos = Json.createArrayBuilder();

            for (Object o : advertisements) {
                Advertisement advertisement = (Advertisement) o;
                Car car = advertisement.getCar();
                List parts = car.getParts();
                List photos = advertisement.getPhotos();

                for (Object ob : parts) {
                    Part part = (Part) ob;
                    arrayBuilderParts.add(Json.createObjectBuilder()
                            .add("type", part.getType())
                            .add("desc", part.getDescription()));
                }

                for (Object ob : photos) {
                    Photo photo = (Photo) ob;
                    arrayBuilderPhotos.add(photo.getFileName());
                }

                advertBuilder.add("advert", Json.createObjectBuilder()
                        .add("id", advertisement.getId())
                        .add("desc", advertisement.getDescription())
                        .add("price", advertisement.getPrice())
                        .add("sold", advertisement.isSold())
                        .add("created", advertisement.getCreated().getTime()))
                        .add("photos", Json.createArrayBuilder()
                                .add(arrayBuilderPhotos))
                        .add("car", Json.createObjectBuilder()
                                .add("vin", car.getVin())
                                .add("brand", car.getBrand()))
                        .add("parts", Json.createArrayBuilder()
                                .add(arrayBuilderParts));

                arrayBuilder.add(advertBuilder);
            }
            jsonArray = arrayBuilder.build();
        }
        return jsonArray;
    }
}
