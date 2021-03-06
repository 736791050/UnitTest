package com.example.unit.app.mock.Injectmocks;

import com.example.unit.app.mock.ModelImpl;

/**
 * @author lisen
 * @since 09-03-2018
 */

public class PresenterImpl {

    private ModelImpl model;

    public PresenterImpl(ModelImpl model){
        this.model = model;
    }

    public String getName(){
        return model.getName();
    }

}
