package com.developervishalsehgal.udacityscholarsapp.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * Created by dru on 16/04/18.
 */

public interface BasePresenter {

    void start(@Nullable Bundle extras);

    void destroy();

}
