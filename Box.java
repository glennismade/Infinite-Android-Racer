//package uk.ac.reading.sis05kol.mooc;
//
//import java.util.Vector;
//
///**
// * Created by glennhealy on 17/04/2016.
// */
//public class Box {
//
//    public Vector position;
//    private float width;
//    private float height;
//    private float halfWidth;
//    private float halfHeight;
//    private int x = 0;
//    private int y = 0;
//
//
//    public Box(Vector position, float width, float height) {
//        this.position = position;
//        this.width = width;
//        this.height = height;
//        halfWidth = width/2;
//        halfHeight = height/2;
//
//
//    }
//
////    public boolean intersects(Box box) {
////        return (position + halfWidth() >= box.position.x = box.halfWidth() && position.y + halfHeight() >= box.position.y - box.halfHeight() ) && (box.position.x + box.halfWidth() >= position.x - halfWidth() && box.position.y + box.halfHeight() >= position.y - halfHeight() );
////
////
////
////    }
//
//
//    public float getWidth() {
//
//        return width;
//    }
//
//    public void setWidth(float width) {
//        this.width = width;
//        halfHeight = width /2;
//    }
//
//    public float getHeight() {
//        return height;
//    }
//
//    public void setHeight(float height) {
//        this.height = height;
//        halfHeight = height /2;
//    }
//
//
//}



//
//    public boolean isIntersecting(Box box) {
//        boolean test1 = false;
//        boolean test2 = false;
//
//        test1 = (position.x + halfWidth() >= box.position.y = box.halfHeight() )
//                &&
//
//                position.y + halfHeight() >= box.position.y = box.halfHeight() );
//
//        test2 = (box.position.x + box.halfWidth() >= position.x - halfWidth() )
//
//                &&
//
//                box.position.y + box.halfHeight() >= position.y - halfHeight() );
//
//        return test1 && test2;
//}
