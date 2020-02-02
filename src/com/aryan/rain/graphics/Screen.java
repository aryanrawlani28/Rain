package com.aryan.rain.graphics;

import com.aryan.rain.entity.mob.Chaser;
import com.aryan.rain.entity.mob.Mob;
import com.aryan.rain.entity.mob.Star;
import com.aryan.rain.entity.projectile.Projectile;
import com.aryan.rain.level.tile.Tile;

import java.util.Random;

public class Screen {
    public int width;
    public int height;

    public int[] pixels;

    private final int MAP_SIZE = 8;
    private final int MAP_SIZE_MASK = MAP_SIZE - 1;

    public int xOffset, yOffset;

    public int[] tiles = new int[MAP_SIZE*MAP_SIZE];
    public Random random = new Random();

    private final int ALPHA_COL = 0xffff00ff;

    public Screen(int width, int height){
        this.width = width;
        this.height = height;

        pixels = new int[width*height]; // 50,400 Size [0, 50399]
        for(int i=0; i<MAP_SIZE*MAP_SIZE; i++){
            tiles[i] = random.nextInt(0xffffff);
        }
    }

    public void clear(){
        for(int i=0; i<pixels.length; i++){
            pixels[i] = 0;
        }
    }

    public void renderSheet(int xp, int yp, SpriteSheet sheet, boolean fixed){
        if (fixed) {
            xp -= xOffset;
            yp -= yOffset;
        }

        for (int y = 0; y < sheet.SPRITE_HEIGHT; y++){
            int ya = y + yp;
            for (int x = 0; x < sheet.SPRITE_WIDTH; x++){
                int xa = x + xp;
                if (xa < 0 || xa >= width || ya < 0 || ya >= height) continue;
                pixels[xa+ya*width] = sheet.pixels[x+y*sheet.SPRITE_WIDTH];
            }
        }

    }

    // This method can draw any sprite.
    public void renderSprite(int xp, int yp, Sprite sprite, boolean fixed){
        if (fixed) {
            xp -= xOffset;
            yp -= yOffset;
        }

        for (int y = 0; y < sprite.getHeight(); y++){
            int ya = y + yp;
            for (int x = 0; x < sprite.getWidth(); x++){
                int xa = x + xp;
                if (xa < 0 || xa >= width || ya < 0 || ya >= height) continue;
                int col = sprite.pixels[x+y*sprite.getWidth()];
                if (col != ALPHA_COL) pixels[xa+ya*width] = col;
            }
        }


    }

    // Make sure to render only what we need. Also, when we move right -> player moves right but map should move to left.
    public void renderTile(int xp, int yp, Tile tile){
        xp -= xOffset;
        yp -= yOffset;

        for (int y = 0; y < tile.sprite.SIZE; y++){                     //mostly tile size is 16. if increase size in future, no probs this way.

            int ya = y + yp;

            for (int x = 0; x < tile.sprite.SIZE; x++){                 // same thing for x.

                int xa = x + xp;                                        // xa = x absolute
                if (xa < -tile.sprite.SIZE || xa >= width || ya < 0 || ya >= height){    // maps are going to be infinite? (Come back to this later #29) are easier?
                    break;
                }
                if (xa < 0) xa = 0;

                pixels[xa + ya * width] = tile.sprite.pixels[x + y * tile.sprite.SIZE];

            }
        }
    }

    public void renderProjectile(int xp, int yp, Projectile p){
        xp -= xOffset;
        yp -= yOffset;

        for (int y = 0; y < p.getSpriteSize(); y++){                     //mostly tile size is 16. if increase size in future, no probs this way.

            int ya = y + yp;

            for (int x = 0; x < p.getSpriteSize(); x++){                 // same thing for x.

                int xa = x + xp;                                        // xa = x absolute
                if (xa < -p.getSpriteSize() || xa >= width || ya < 0 || ya >= height){    // maps are going to be infinite? (Come back to this later #29) are easier?
                    break;
                }
                if (xa < 0) xa = 0;

                int col = p.getSprite().pixels[x + y * p.getSprite().SIZE];
                if (col != 0xFFFF00DC) pixels[xa + ya * width] = col;

            }
        }
    }

    // angle is in radians
    public void renderProjectile(int xp, int yp, Projectile p, double angle){
        xp -= xOffset;
        yp -= yOffset;

        for (int y = 0; y < p.getSpriteSize(); y++){                     //mostly tile size is 16. if increase size in future, no probs this way.

            int ya = y + yp;

            for (int x = 0; x < p.getSpriteSize(); x++){                 // same thing for x.

                int xa = x + xp;                                        // xa = x absolute
                if (xa < -p.getSpriteSize() || xa >= width || ya < 0 || ya >= height){    // maps are going to be infinite? (Come back to this later #29) are easier?
                    break;
                }
                if (xa < 0) xa = 0;

                int[] rpixels = rotate(p.getSprite().pixels, p.getSpriteSize(), p.getSpriteSize(), angle);

                int col = rpixels[x + y * p.getSprite().SIZE];
                if (col != 0xFFFF00DC) pixels[xa + ya * width] = col;

            }
        }
    }

    private int[] rotate(int[] pixels, int width, int height, double angle){
        int[] result = new int[width*height];

        double nx_x = rot_x(-angle, 1.0, 0.0);
        double nx_y = rot_y(-angle, 1.0, 0.0);

        double ny_x = rot_x(-angle, 0.0, 1.0);
        double ny_y = rot_y(-angle, 0.0, 1.0);

        double x0 = rot_x(-angle, -width / 2.0, -height / 2.0) + width / 2.0;
        double y0 = rot_y(-angle, -width / 2.0, -height / 2.0) + height / 2.0;

        // Move
        for (int y = 0; y < height; y++){
            double x1 = x0;
            double y1 = y0;
            for (int x = 0; x < width; x++){
                int xx = (int) x1;
                int yy = (int) y1;

                int col = 0;
                if (xx < 0 || xx >= width || yy < 0 || yy >= height){
                    col = 0xFFFF00DC;
                }else{
                    col = pixels[xx + yy * width];
                }

                result[x + y * width] = col;
                x1 += nx_x;
                y1 += nx_y;
            }
            x0 += ny_x;
            y0 += ny_y;
        }

        return result;
    }

    // Figure out where is the new x
    private double rot_x(double angle, double x, double y){
        double res = 0;

        double cos = Math.cos(angle - Math.PI / 2);
        double sin = Math.sin(angle - Math.PI / 2);

        res = (x * cos) + (y * -sin);

        return res;
    }

    private double rot_y(double angle, double x, double y){
        double res = 0;

        double cos = Math.cos(angle - Math.PI / 2);
        double sin = Math.sin(angle - Math.PI / 2);

        res = (x * sin) + (y * cos);

        return res;
    }

    public void renderMob(int xp, int yp, Mob mob){
        xp -= xOffset;
        yp -= yOffset;

        for (int y = 0; y < 32; y++){                     //mostly tile size is 16. if increase size in future, no probs this way.

            int ya = y + yp;
            int ys = y;

            // ys = 31 - y;                                                // instead of top to bottom, bottom to top.

            for (int x = 0; x < 32; x++){                                   // same thing for x.

                int xa = x + xp;                                            // xa = x absolute
                int xs = x;
                // xs = 31 - x;

                if (xa < -32 || xa >= width || ya < 0 || ya >= height){    // maps are going to be infinite? (Come back to this later #29) are easier?
                    break;
                }
                if (xa < 0) xa = 0;

                int col = mob.getSprite().pixels[xs+ys*32];

                if ((mob instanceof Chaser) && col == 0xff472bbf) col = 0xFFBA0015; // if chaser mob, change to cloak to red.

                if ((mob instanceof Star) && col == 0xff472bbf) col = 0xFFD8D83A; // if chaser mob, change to cloak to red.

                if (col != -65281) {
                    pixels[xa + ya * width] = col;
                }
            }
        }
    }

    public void renderMob(int xp, int yp, Sprite sprite, int flip){
        xp -= xOffset;
        yp -= yOffset;

        for (int y = 0; y < 32; y++){                     //mostly tile size is 16. if increase size in future, no probs this way.

            int ya = y + yp;
            int ys = y;
            if (flip == 2 || flip == 3) {
                ys = 31 - y;                                                // instead of top to bottom, bottom to top.
            }
            for (int x = 0; x < 32; x++){                                   // same thing for x.

                int xa = x + xp;                                            // xa = x absolute
                int xs = x;
                if (flip == 1 || flip == 3) {
                    xs = 31 - x;                                                // instead of going left -> right, we go right -> left this way.
                }

                if (xa < -32 || xa >= width || ya < 0 || ya >= height){    // maps are going to be infinite? (Come back to this later #29) are easier?
                    break;
                }
                if (xa < 0) xa = 0;

                int col = sprite.pixels[xs+ys*32];

                if (col != -65281) {
                    pixels[xa + ya * width] = col;
                }
            }
        }
    }

    public void drawRect(int xp, int yp, int width, int height, int color, boolean fixed) {

        if (fixed){
            xp -= xOffset;
            yp -= yOffset;
        }

        for (int x = xp; x < xp+width; x++){
            if (x < 0 || x >= this.width || yp >= this.height) continue;
            if (yp > 0) pixels[x + yp * this.width] = color;
            if (yp + height >= this.height) continue;
            if (yp + height > 0) pixels[x + (yp+height) * this.width] = color;
        }

        for (int y = yp; y <= yp+height; y++){
            if (xp >= this.width || y < 0 || y >= this.height) continue;
            if (xp > 0) pixels[xp + y * this.width] = color;
            if (xp + width >= this.width) continue;
            if (xp + width > 0)pixels[xp + width + y * this.width] = color;
        }

    }

    public void setOffset(int xOffset, int yOffset){
        this.xOffset = xOffset;
        this.yOffset = yOffset;
    }

    public void renderTextCharacter(int xp, int yp, Sprite sprite, int color, boolean fixed) {
        if (fixed) {
            xp -= xOffset;
            yp -= yOffset;
        }
        for (int y = 0; y < sprite.getHeight(); y++) {
            int ya = y + yp;
            for (int x = 0; x < sprite.getWidth(); x++) {
                int xa = x + xp;
                if (xa < 0 || xa >= width || ya < 0 || ya >= height) continue;
                int col = sprite.pixels[x + y * sprite.getWidth()];
                if (col != ALPHA_COL && col != 0xff7f007f) pixels[xa + ya * width] = color;
            }
        }
    }
}
