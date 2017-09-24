package com.app.controller;

import com.app.utill.Render;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Vehicle implements Task {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return Render.jspPath(this, "vehicle.jsp");
    }
}
