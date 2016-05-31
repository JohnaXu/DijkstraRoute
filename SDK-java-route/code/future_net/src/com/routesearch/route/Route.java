/**
 * 实现代码文件
 * 
 * @author XXX
 * @since 2016-3-4
 * @version V1.0
 */
package com.routesearch.route;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.*;

public final class Route
{
    private int nodeNum;//the number in the graph;
    private  int[] vex ;//the nodein in graph
    private  int[][] arc ; //the matri used in the search
    private int startNode; 
    private int tailNode;
    private int sAdded[];// flag is the node is in the set of S
    private int dist[] ;//the length from startNode to i
    private int path[] ;//the path from start to i

    public Route(int num, int s, int t){
        this.nodeNum = num;
        this.startNode = s;
        this.tailNode = t;
        this.vex = new int[num];
        this.arc = new int[num][num];
        this.sAdded = new int[num];
        this.dist = new int[num];
        this.path = new int[num];
        
    }

    /**
     * the String s read from file include space ,[,], this function used to delete the invalid 
     * element
     * */
    public static String replaceSpace(String s){
        s = s.replace("[", "");
        s = s.replace(" ", "");
        s = s.replace("]", "");
        return s;
    }

    /**
     * inite matri, just set the value of edge;
     * */
    public void initArc(String s){
        String[] str = s.split(",");
        int id, startPointID, tailPointId, value; 
        for( int i = 0; i< str.length; ){    
            startPointID = Integer.parseInt(str[i++]);
            tailPointId = Integer.parseInt(str[i++]);
            value = Integer.parseInt(str[i++]);
            arc[startPointID][tailPointId]=value;   
            //i= i+ 6;
        } 
    }
    
    /**
     * inite matri, just set the value of edge;
     * */
    public void initArc(char[] str,int len){
        int id, startPointID, tailPointId, value; 
        for( int i = 0; i< len; ){    
            //id = Integer.parseInt(String.valueOf(str[i]));
            startPointID = Integer.parseInt(String.valueOf(str[i]));
            tailPointId = Integer.parseInt(String.valueOf(str[i+2]));
            value = Integer.parseInt(String.valueOf(str[i+4]));
            arc[startPointID][tailPointId]=value;   
            i= i+ 6;
        } 
    }
    
    /**
     * init path.
     * let all verx is not added in the S;
     * path is -1,but path[i]=startNode;
     * */
    public void initRout(){
        for(int i = 0; i< this.nodeNum;i++){
            sAdded[i]=0;
            dist[i]=this.arc[startNode][i];
            if(dist[i]==0){
                path[i]=-1;
            }else{
                path[i]=startNode;
            }
            
        }
    }
    
    /**
     * key function,
     * find the shortest path from start point to tailpoint.
     * */
    public void findRout(){
        dist[startNode]=0;
        sAdded[startNode] = 1;
        
        for( int i = 0; i<this.nodeNum-1;i++){
            int min = 9999;
            int v = -1;
            //find the shortest path
            for(int w = 0;w < this.nodeNum; w++){
                if(this.sAdded[w] == 0 && this.dist[w] <min && this.dist[w] != 0){
                    v = w;
                    min =this.dist[w];
                }               
            }
            //all the verx added in S
            if(v == -1)break;            
            //change the value of the verx not added in S
            sAdded[v]=1;
            //already find the path from startpoint to tailpoint
            if(v == tailNode)break;
            for(int j = 0; j< this.nodeNum; j++){
                if(sAdded[j] == 0 && (min+arc[v][j]<dist[j])){
                    dist[j] = min+arc[v][j];
                    path[j]=v;
                }
            }
            
        }       
        
    }
    
    private  String getRouteResult() {
        // TODO Auto-generated method stub
        int NodeNum = this.nodeNum;
        int a[] = new int[NodeNum];
        a[0] = this.tailNode;
        int lenRoute ;
        String result = "|" + tailNode;
        for(int i = 1; ; i++){
            a[i] = this.path[a[i-1]];
            result = a[i]+ result;
            if(a[i] == startNode){
                lenRoute = i+1;
                break;                  
            }
            result = "|"+result;
        }
        return result;
    }
    private int getLenResult() {
        // TODO Auto-generated method stub
        return this.dist[this.tailNode];
    }

    /**
     * @author XXX
     * @since 2016-3-4
     * @version V1
     * @param graphContent the content of graph
     * @param num the total verx in graph
     * @param startNodeMain the startPoint 
     * @param endNodeMain the end node point
     * @return the path and length from startNodeMain to endNodeMain
*/
    public static String searchRoute(String graphContent, String num, String startNodeMain, String endNodeMain)
    {

        String s = Arrays.toString(graphContent.split("\\D+"));
        int nodeNum = Integer.parseInt(num);
        int startNode = Integer.parseInt(startNodeMain);
        int tailNode = Integer.parseInt(endNodeMain);

        

        char[] str = s.toCharArray();
        Route route = new Route(nodeNum, startNode, tailNode);
        
        route.initArc(replaceSpace(s));

        // s = replaceSpace(s);
        // route.initArc(str, s.length());
        route.initRout();
        
        route.findRout();
        
        String routeResult = route.getRouteResult();
        int lenResult = route.getLenResult();

        StringBuilder result = new StringBuilder();
        result.append("The ").append(route.startNode ).append(" to ").append(route.tailNode)
              .append("\n").append("route: ").append(routeResult)
              .append("\n").append("length: ").append(lenResult);

        return result.toString();

    }   

}
