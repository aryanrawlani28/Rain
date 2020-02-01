package com.aryan.rain.level;

import com.aryan.rain.util.Vector2i;

public class Node {

    public Vector2i tile;
    public Node parent;                     // We need to know where we came from. Path

    // fCost - Combination of gCost and hCost, total. Both gCost and hCost are important.
    // gCost - sum of all total node to node cost. How long has it been since the start? total cost since the start
    // hCost - Heuristic cost. "direct distance", a straight line.
    public double fCost, gCost, hCost;

    // We use "Lower cost" instead of "Shortest path" in A*

    public Node(Vector2i tile, Node parent, double gCost, double hCost){

        this.tile = tile;
        this.parent = parent;
        this.gCost = gCost;
        this.hCost = hCost;

        this.fCost = this.gCost + this.hCost;

    }

}
