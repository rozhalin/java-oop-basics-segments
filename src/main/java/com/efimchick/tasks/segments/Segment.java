package com.efimchick.tasks.segments;

public class Segment {

    private final Point leftPoint;
    private final Point rightPoint;

    public Point getLeftPoint() {
        return leftPoint;
    }

    public Point getRightPoint() {
        return rightPoint;
    }

    public Segment(Point start, Point end) {
        if (start.compareTo(end) == 0) throw new RuntimeException("Segment is degenerative");
        if (MathConstant.doubleCompare(start.getX(), end.getX()) != 1) {
            this.leftPoint = start;
            this.rightPoint = end;
        } else {
            this.leftPoint = end;
            this.rightPoint = start;
        }
    }

    double length() {
        return Math.sqrt(Math.pow(rightPoint.getY() - leftPoint.getY(), 2) +
                            Math.pow(rightPoint.getX() - leftPoint.getX(), 2));
    }

    Point middle() {
        double cordX = (leftPoint.getX() + rightPoint.getX()) / 2;
        double cordY = (leftPoint.getY() + rightPoint.getY()) / 2;
        return new Point(cordX, cordY);
    }

    Point intersection(Segment another) {
        double x1 = this.leftPoint.getX();
        double y1 = this.leftPoint.getY();
        double x2 = this.rightPoint.getX();
        double y2 = this.rightPoint.getY();
        double x3 = another.leftPoint.getX();
        double y3 = another.leftPoint.getY();
        double x4 = another.rightPoint.getX();
        double y4 = another.rightPoint.getY();

        if (this.segmentParallel(another)) {
            if (this.leftPoint.compareTo(another.rightPoint) == 0) {
                return this.leftPoint;
            } else if (this.rightPoint.compareTo(another.leftPoint) == 0) {
                return this.rightPoint;
            } else {
                return null;
            }
        }

        double A1 = y2 - y1;
        double B1 = x1 - x2;
        double C1 = A1*x1 + B1*y1;
        double A2 = y4 - y3;
        double B2 = x3 - x4;
        double C2 = A2*x3 + B2*y3;
        double det = A1 * B2 - A2 * B1;
        double x = (B2 * C1 - B1 * C2) / det;
        double y = (A1 * C2 - A2 * C1) / det;
        if (pointBetweenSegmentPoints(x1, x2, x) && pointBetweenSegmentPoints(x3, x4, x) &&
                pointBetweenSegmentPoints(y1, y2, y) && pointBetweenSegmentPoints(y3, y4, y)) {
            return new Point(x, y);
        } else {
            return null;
        }


    }

    private boolean pointBetweenSegmentPoints(double x1, double x2, double x) {
        double min = Math.min(x1,x2);
        double max = Math.max(x1,x2);
        boolean greater = MathConstant.doubleCompare(x, min) != -1;
        boolean less = MathConstant.doubleCompare(x, max) != 1;
        return greater && less;
    }

    private boolean segmentParallel(Segment segment) {
        double x1 = this.leftPoint.getX();
        double y1 = this.leftPoint.getY();
        double x2 = this.rightPoint.getX();
        double y2 = this.rightPoint.getY();
        double x3 = segment.leftPoint.getX();
        double y3 = segment.leftPoint.getY();
        double x4 = segment.rightPoint.getX();
        double y4 = segment.rightPoint.getY();
        return MathConstant.doubleCompare(((x2 - x1) / (y2 - y1)), ((x4 - x3) / (y4 - y3))) == 0;
    }
}
