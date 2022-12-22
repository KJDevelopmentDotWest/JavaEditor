package com.z7.shapes;

public class CircleFigure extends AbstractFigure {
    private double radius;

    public CircleFigure(double radius) {
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }
}
