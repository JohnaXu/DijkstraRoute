class Dijkstra {  
    public static void main(String[] args)throws IOException {  
        ArrayList<Point> point_arr = new ArrayList<Point>();// 存储点集合  
        BufferedReader bufr = new BufferedReader(new InputStreamReader(System.in));  
        System.out.print("请输入顶点个数： ");  
        int sum = 0;  
        boolean flag =true;  
        while(flag){  
            try {  
                sum = Integer.valueOf(bufr.readLine());  
                flag = false;  
            } catch (NumberFormatException e) {  
                System.out.print("输入有误，请重新输入：");  
            }  
        };  
        for (int i = 0; i < sum; i++) {// 初始化  
            Point p = new Point(sum);  
            p.setId(i);  
            p.setLenToOther();  
            point_arr.add(p);  
        }  
        System.out.print("请输入起始顶点 id ：");  
        boolean flag2 =true;  
        int start = 0;  
        while(flag2){  
            try {  
                start = Integer.valueOf(bufr.readLine())-1;  
                if(start > sum-1 || start < 0)  
                    throw new NumberFormatException();  
                flag2 = false;  
            }catch (NumberFormatException e) {  
                System.out.print("输入有误，请重新输入：");  
            }  
        };  
        showDijkstra(point_arr, start);// 单源最短路径遍历  
    }  
  
    public static void showDijkstra(ArrayList<Point> arr, int i) {  
        System.out.print("顶点" + (i + 1));  
        arr.get(i).changeFlag();  
        Point p1 = getTopointMin(arr, arr.get(i));  
        if (p1 == null)  
            return;  
        int id = p1.getId();  
        showDijkstra(arr, id);  
  
    }  
  
    public static Point getTopointMin(ArrayList<Point> arr, Point p) {  
        Point temp = null;  
        int minLen = Integer.MAX_VALUE;  
        for (int i = 0; i < arr.size(); i++) {  
            // 当已访问 或 者是自身或者无该路径时跳过。  
            if (arr.get(i).isVisit() || arr.get(i).getId() == p.getId() || p.lenToPointId(i) < 0)  
                continue;  
            else {  
                if (p.lenToPointId(i) < minLen) {  
                    minLen = p.lenToPointId(i);  
                    temp = arr.get(i);  
                }  
            }  
        }  
        if (temp == null)  
            return temp;  
        else  
            System.out.print("  @--" + minLen + "--> ");  
        return temp;  
    }  
}  