package com.controller;

import com.view.ViewError;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControllerViewError implements ActionListener {

    ViewError viewError;

    public ControllerViewError(ViewError viewError) {
        this.viewError = viewError;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.viewError.dispose();
    }
}
