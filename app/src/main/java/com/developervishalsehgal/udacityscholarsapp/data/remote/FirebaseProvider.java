package com.developervishalsehgal.udacityscholarsapp.data.remote;

/**
 *
 * Provides an implementation of {@link FirebaseHandler}. This provider can decide whether to
 * provide real or mock implementation
 *
 * @Author kaushald
 */
public class FirebaseProvider {

    public static FirebaseHandler provide() {
        return new FirebaseHandlerImpl();
    }

}
