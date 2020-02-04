package com.aryan.rain.level;

// L1: Random gen (Noise mapping - advanced)
// L2: Map level

import com.aryan.rain.entity.Entity;
import com.aryan.rain.entity.mob.Player;
import com.aryan.rain.entity.particle.Particle;
import com.aryan.rain.entity.projectile.Projectile;
import com.aryan.rain.events.Event;
import com.aryan.rain.graphics.Screen;
import com.aryan.rain.graphics.layers.Layer;
import com.aryan.rain.level.tile.Tile;
import com.aryan.rain.util.Vector2i;

import java.util.*;

// Manages which tiles need to be rendered.
public class Level extends Layer {

    protected int width, height;                    // Primarily for L1
    protected int[] tilesInt;                       // Eg tiles[1] is grass, 2 is stone, etc..

    protected int[] tiles;  // Contains all of levels tiles. Since one level loaded at a time, this works well.

    private int xScroll, yScroll;

    private List<Entity> entities = new ArrayList<Entity>();
    private List<Projectile> projectiles = new ArrayList<Projectile>();
    private List<Particle> particles = new ArrayList<Particle>();
    private List<Player> players = new ArrayList<Player>();

    public static Level Spawn = new SpawnLevel("res/levels/spawn_level.png");


    // Used when we generate a random level
    public Level(int width, int height){
        // L1
        this.width = width;
        this.height = height;

        tilesInt = new int[width * height];            // Tile for every square

        generateLevel();
    }

    public Level(String path){
        // L2
        loadLevel(path);
        generateLevel();
    }

    protected void generateLevel() {

    }

    protected void loadLevel(String path){
          
    }

    public List<Projectile> getProjectiles(){
        return projectiles;
    }


    public void update(){
        // AI and stuff goes in update. 60 updates per sec
        for (int i=0; i < entities.size(); i++) {
            if (entities.get(i).isRemoved()){
                entities.remove(i);
            }else {
                entities.get(i).update();
            }
        }

        for (int j=0; j < projectiles.size(); j++) {
            if (projectiles.get(j).isRemoved()) {
                projectiles.remove(j);
            }else{
                projectiles.get(j).update();
            }
        }

        for (int k=0; k < particles.size(); k++) {
            if (particles.get(k).isRemoved()){
                particles.remove(k);
            }else{
                particles.get(k).update();
            }
        }

        for (int l=0; l < players.size(); l++) {
            if (players.get(l).isRemoved()){
                players.remove(l);
            }else{
                players.get(l).update();
            }
        }

        remove();
    }

    public void onEvent(Event event){
        getClientPlayer().onEvent(event);
    }


    private void remove(){

    }

    private void time(){
        // change level and stuff based on time.
    }

    public boolean tileCollision(int x, int y, int size, int xOffset, int yOffset){
        // boolean solid = false;
        // Size is the size of our object

        for(int c = 0; c < 4; c++){

            int xt = (x - c % 2 * size + xOffset) >> 4;   // basically do we use size or not? to check each corner
            int yt = (y - c / 2 * size + yOffset) >> 4;

            if (getTile(xt, yt).solid()) return true;
        }

        return false;
    }

    public void setScroll(int xScroll, int yScroll){
        this.xScroll = xScroll;
        this.yScroll = yScroll;
    }

    //this method and vars define the render region
    public void render(Screen screen){
        screen.setOffset(xScroll, yScroll);

        // as fast as possible. probably empty here.
        // Corner pins: rendering is done from top left to top right

        int x0 = xScroll >> 4;                          // x0 is the inmost x coord.. left side of the screen.
        int x1 = (xScroll + screen.width + 16) >> 4;    // x1 is the right side of the screen (16 is being added for aesthetic purpose and prevent black!)
        int y0 = yScroll >> 4;                          // y0 is the top
        int y1 = (yScroll + screen.height + 16) >> 4;   // y1 is bottom

        for (int y = y0; y < y1; y++){
            for (int x = x0; x < x1; x++){
                getTile(x, y).render(x, y, screen);
            }
        }

        for (int i=0; i< entities.size(); i++) {
            entities.get(i).render(screen);
        }

        for (int j=0; j < projectiles.size(); j++) {
            projectiles.get(j).render(screen);
        }

        for (int k=0; k < particles.size(); k++) {
            particles.get(k).render(screen);
        }

        for (int l=0; l < players.size(); l++) {
            players.get(l).render(screen);
        }
    }


    public void add(Entity e){
        e.init(this);

        if (e instanceof Particle){
            particles.add((Particle) e);
        }else if (e instanceof Projectile){
            projectiles.add((Projectile) e);
        }else if(e instanceof Player){
            players.add((Player) e);
        }else{
            entities.add(e);
        }
    }


    @Deprecated
    public List<Player> getPlayers(){
//        for (int i = 0; i < entities.size(); i++) {
//            if (entities.get(i) instanceof Player) {
//                return (Player) entities.get(i);
//            }
//        }
//
//        return null;
        return players;
    }

    public Player getPlayerAt(int i){
        return players.get(i);
    }

    public Player getClientPlayer(){
        return players.get(0);
    }




    /////////////////////////////////// A* Algorithm ///////////////////////////////////

    // Takes in 2 objs, and based on our info returns an int.
    private Comparator<Node> nodeSorter = new Comparator<Node>() {
        @Override
        public int compare(Node n0, Node n1) {

            // Sort by fCost for the shortest
            if (n1.fCost < n0.fCost) return +1; // Move up in index

            if (n1.fCost > n0.fCost) return -1; // Otherwise move down

            return 0;
        }
    };

    public List<Node> findPath(Vector2i start, Vector2i goal){

        // All adj are being considered one by one from openlist.

        List<Node> openList = new ArrayList<>();            // Every tile is in openlist, we're considering.
        List<Node> closedList = new ArrayList<>();          // One by one remove from open and add to closed

        Node current = new Node(start, null, 0, getDistance(start, goal));

        openList.add(current);


        // Working out stuf::
        while (openList.size() > 0){

            Collections.sort(openList, nodeSorter);     // Sorts node acc to our comparator.

            current = openList.get(0);                  // We're getting the first, because we're gonna sort them out closest to first. That's why consider the closest first. Perf++;

            // As soon as we find a path, get away. Finish.
            if (current.tile.equals(goal)){

                // Return here. Path reconstruction.
                List<Node> path = new ArrayList<Node>();

                while (current.parent != null){
                    path.add(current);
                    current = current.parent;
                }

                openList.clear();
                closedList.clear();

                return path;
            }

            openList.remove(current);
            closedList.add(current);                        // Tiles already checked here.

            // Uptill now we've checked all adj nodes.

            for (int i=0; i < 9; i++){

                if (i == 4) continue;                        // Middle, we're currently here.

                int x = current.tile.getX();
                int y = current.tile.getY();

                int xi = (i % 3) - 1;                         // Checking neighbours
                int yi = (i / 3) - 1;

                Tile at = getTile(x + xi, y + yi);      // Will be every tile in the list.

                if (at == null) continue;
                if (at.solid()) continue;

                Vector2i a = new Vector2i(x+xi, y+yi);  // Same tile, but in vect form.

                // Only immediate distances are being compared
                double gCost = current.gCost + getDistance(current.tile, a);
                //double gCost = current.gCost + getDistance(current.tile, a) == 1 ? 1 : 0.95;
                double hCost = getDistance(a, goal);

                // Next tile is "a", and we make the current one the parent of it.
                // We've also computed hCost and gCost.
                Node node = new Node(a, current, gCost, hCost);

                // If we've already been there, don't add to list.
                // BUT, in some cases, can reopen and visit through there because no other possibility exists.
                if (vecInList(closedList, a) && gCost >= node.gCost) continue;

                // if vec is not in openlist, add it.
                if (!vecInList(openList, a) || gCost < node.gCost) openList.add(node);
            }


        }

        closedList.clear();
        return null;
    }

    private double getDistance(Vector2i tile, Vector2i goal){

        double dx = tile.getX() - goal.getX();
        double dy = tile.getY() - goal.getY();
        double distance = Math.sqrt(dx * dx + dy * dy);
//        if (distance == 1) return 1;
//        return 0.95;

        return distance;
    }

    private boolean vecInList(List<Node> list, Vector2i v){
        for (Node n : list){
            if (n.tile.equals(v)) return true;
        }
        return false;
    }

    /////////////////////////////////////////////////////////////////////////////



    // Universal method to get all entities.
    public List<Entity> getEntities(Entity e, int radius){
        List<Entity> result = new ArrayList<>();

        int ex = (int)e.getX();
        int ey = (int)e.getY();

        for (int i=0; i<entities.size(); i++){
            Entity entity = entities.get(i);
            if (entity.equals(e)) continue;
            int x = (int) entity.getX();
            int y = (int) entity.getY();

            int dx = Math.abs(x - ex);
            int dy = Math.abs(y - ey);

            double dist = Math.sqrt((dx * dx) + (dy * dy));

            if (dist <= radius){
                result.add(entity);
            }
        }

        return result;
    }

    public List<Player> getPlayers(Entity e, int radius){

//        List<Entity> entities = getEntities(e, radius);
        List<Player> result = new ArrayList<Player>();
        int ex = (int) e.getX();
        int ey = (int) e.getY();

        for (int i=0; i<players.size(); i++){
            Player player = players.get(i);
            int x = (int) player.getX();
            int y = (int) player.getY();

            int dx = Math.abs(x - ex);
            int dy = Math.abs(y - ey);

            double dist = Math.sqrt((dx * dx) + (dy * dy));

            if (dist <= radius){
                result.add(player);
            }
        }

        return result;
    }


    // We will render what this method returns. This converts array to tiles.
    public Tile getTile(int x, int y){

        if (x < 0 || y < 0 || x >= width || y >= height) return Tile.spawn_grass;

        if (tiles[x+y*width] == Tile.col_spawn_floor) return Tile.spawn_floor;
        if (tiles[x+y*width] == Tile.col_spawn_grass) return Tile.spawn_grass;
        if (tiles[x+y*width] == Tile.col_spawn_hedge) return Tile.spawn_hedge;
        if (tiles[x+y*width] == Tile.col_spawn_wall1) return Tile.spawn_wall1;
        if (tiles[x+y*width] == Tile.col_spawn_wall2) return Tile.spawn_wall2;
        if (tiles[x+y*width] == Tile.col_spawn_water) return Tile.spawn_water;

        return Tile.spawn_grass;
    }
}
