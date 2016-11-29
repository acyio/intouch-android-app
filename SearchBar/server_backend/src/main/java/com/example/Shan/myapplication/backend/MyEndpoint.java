/*
   For step-by-step instructions on connecting your Android application to this backend module,
   see "App Engine Java Endpoints Module" template documentation at
   https://github.com/GoogleCloudPlatform/gradle-appengine-templates/tree/master/HelloEndpoints
*/

package com.example.Shan.myapplication.backend;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;

import javax.inject.Named;

/**
 * An endpoint class we are exposing
 */
@Api(
        name = "myApi",
        version = "v1",
        namespace = @ApiNamespace(
                ownerDomain = "backend.myapplication.Shan.example.com",
                ownerName = "backend.myapplication.Shan.example.com",
                packagePath = ""
        )
)
public class MyEndpoint {

    protected boolean initialized = false;
    protected DatastoreService datastore;



    /**
     * A simple endpoint method that takes a name and says Hi back
     */
    @ApiMethod(name = "sayHi")
    public MyBean sayHi(@Named("name")String name) {
        if (!initialized){
            datastore = DatastoreServiceFactory.getDatastoreService();
            initialized = true;
        }

        String rem = name;
        name = name.substring(0, name.indexOf(","));
        rem = rem.substring(rem.indexOf(",") + 1);
        String phone = rem.substring(0, rem.indexOf(","));
        rem = rem.substring(rem.indexOf(",")+1);
        String email = rem;
        Entity contact = new Entity("PersonalContact");
        contact.setProperty("name", name);
        contact.setProperty("number", phone);
        contact.setProperty("email", email);
        contact.setProperty("cat", "personal");
        datastore.put(contact);

        MyBean response = new MyBean();
        response.setData("Hi, " + name);

        return response;
    }

}
