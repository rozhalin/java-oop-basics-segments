package com.efimchick.tasks.segments;

import static com.efimchick.tasks.segments.MathConstant.EPSILON;

class Point {
    private double x;
    private double y;

    public Point(final double x, final double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public int compareTo(Point p) {
        if (Math.abs(this.x - p.getX()) < EPSILON && Math.abs(this.y - p.getY()) < EPSILON) {
            return 0;
        } else if (this.x > p.getX()) {
            return 1;
        } else {
            return -1;
        }
    }
}
