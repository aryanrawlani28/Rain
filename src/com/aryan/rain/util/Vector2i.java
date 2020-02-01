package com.aryan.rain.util;

// A vector, with 2 coords, type of data: "i -> integer". For floats, Vector2f, etc
public class Vector2i {

    private int x, y;

    /////////////////////////////////////// GETTERS ///////////////////////////////////////

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    //////////////////////////////////////////////////////////////////////////////////////////


    /////////////////////////////////////// SETTERS ///////////////////////////////////////

    public Vector2i(){
        set(0, 0);
    }

    public Vector2i(int x, int y){
        set(x, y);
    }

    public Vector2i(Vector2i vector){
        set(vector.x, vector.y);
    }

    public Vector2i setX(int x){
        this.x = x;
        return this;
    }

    public Vector2i setY(int y){
        this.y = y;
        return this;
    }

    public void set(int x, int y){
        this.x = x;
        this.y = y;
    }

    //////////////////////////////////////////////////////////////////////////////////////////


    /////////////////////////////////////// OPERATIONS ///////////////////////////////////////

    public Vector2i add(Vector2i vector){
        this.x += vector.x;
        this.y += vector.y;

        return this;
    }

    public Vector2i subtract(Vector2i vector){
        this.x -= vector.x;
        this.y -= vector.y;

        return this;
    }

    public Vector2i dotProduct(){

        return this;
    }

    public Vector2i crossProduct(){

        return this;
    }

    //////////////////////////////////////////////////////////////////////////////////////////

    public void test(){
        Vector2i p_pos = new Vector2i(8,4);
        Vector2i m_pos = new Vector2i(p_pos).setX(10);
    }

}