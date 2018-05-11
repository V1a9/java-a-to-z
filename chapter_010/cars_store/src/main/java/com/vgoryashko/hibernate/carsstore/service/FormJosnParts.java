package com.vgoryashko.hibernate.carsstore.service;

import com.vgoryashko.hibernate.carsstore.models.parts.Part;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import java.util.List;

/**
 * Class that returns JSON array for Part.
 *
 * @author Vlad Goryashko
 * @version 0.1
 * @since 3/23/18
 */
public class FormJosnParts {

    private JsonArray jsonArray = null;

    public JsonArray returnJson(List<Part> parts) {

        if (parts != null) {
            JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
            for (Object o : parts) {
                Part part = (Part) o;
                arrayBuilder.add(Json.createObjectBuilder()
                        .add("type", (part.getType()))
                        .add("desc", (part.getDescription())
                        )
                );
            }

            jsonArray = arrayBuilder.build();
        }
        return jsonArray;
    }
}
