package com.contest.hackerrank;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class CircularWalkBFS {

    static int circularWalk(int n, int s, int t, int r_0, int g, int seed, int p){
        int vMaxJumps[] = new int[n];
        boolean visited[] = new boolean[n];
        vMaxJumps[0]= r_0;

        for(int i=1; i< n; i++){
            vMaxJumps[i] = ((vMaxJumps[i-1]*g) + seed)%p;
        }

        //Edge Condition 1 - Starting cannot jump:
        if(s==t){
            return(0);
        }
        if(vMaxJumps[s]==0 ){
            return(-1);
        }
        /**/

        //To keep track of the visited nodes.
        //HashMap visited = new HashMap();
        //Queue for BFS implementation.
        Queue q = new LinkedList();

        int current = s;
        int levelMarker = -1;
        q.add(current);
        q.add(levelMarker);
        int steps = 0;
        int jLoc=0;
        int kLoc=0;
        int jumps=0;
        int nodesTobeVisited[]= new int[vMaxJumps[s]*2];
        while(nodesTobeVisited.length==0){
            current = ((Integer)q.remove()).intValue();

            if(current == levelMarker){
                steps++;
                if(q.size()!=0){
                    //don't add if there is no more element in the queue for next processing.
                    q.add(levelMarker);
                }
                continue;
            }

            if(current == t){
                break;
            }


            visited[current]=true;
            jumps=vMaxJumps[current];
            //calculate the next positions and add.
            if(jumps==0){
                //it cannot jump anywhere
                continue;
            }
            //Add the next possible positions.

            for(int j=1; j<=jumps; j++ ){
                jLoc = (current+j+n)%n;
                kLoc = (current-j+n)%n;
                //if we find them.let's get out.
                if(jLoc==t ||kLoc==t){
                    return(++steps);

                }
                if(!visited[jLoc]){
                    q.add(jLoc);
                    //System.out.println("Current->"+current+": Adding ->"+jLoc);
                }
                if(!visited[kLoc]){
                    q.add(kLoc);
                    //System.out.println("Current->"+current+": Adding ->"+kLoc);
                }
            }

        }

        if(current == t){

            return(steps);
        }

        return(-1);
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int s = in.nextInt();
        int t = in.nextInt();
        int r_0 = in.nextInt();
        int g = in.nextInt();
        int seed = in.nextInt();
        int p = in.nextInt();
        int result = circularWalk(n, s, t, r_0, g, seed, p);
        long x = System.currentTimeMillis();
        //System.out.println(System.currentTimeMillis());
        System.out.println(result);
        long y = System.currentTimeMillis();
        System.out.println("time taken-->"+(y-x));
    }
}


