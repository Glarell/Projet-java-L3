package com.controller;

import com.view.ViewError;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The type Controller view error.
 */
public class ControllerViewError implements ActionListener {

    /**
     * The View error.
     */
    ViewError viewError;

    /**
     * Instantiates a new Controller view error.
     *
     * @param viewError the view error
     */
    public ControllerViewError(ViewError viewError) {
        this.viewError = viewError;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.viewError.dispose();
    }
}
