class Point {  
    private int id;// 点的id  
    private boolean flag = false;// 标志是否被遍历  
    int sum;// 记录总的点个数  
  
    private TreeMap<Integer, Integer> thisPointMap = new TreeMap<Integer, Integer>();// 该点到各点的距离。  
    BufferedReader bufr = new BufferedReader(new InputStreamReader(System.in));  
  
    Point(int sum) { // 构造函数 带有顶点个数  
        this.sum = sum;  
    }  
  
    public void setId(int id) {// 设置顶点id  
        this.id = id;  
    }  
  
    public int getId() {// 获得顶点id  
        return this.id;  
    }  
  
    public void changeFlag() {// 修改访问状态。  
        this.flag = true;  
    }  
  
    public boolean isVisit() {// 查看访问状态  
        return flag;  
    }  
  
    public void setLenToOther()throws IOException{// 初始化改点到各顶点的距离。  
        System.out.println("=======请输入顶点" + (this.id + 1) + "至其他各顶点的边距=======");  
        for (int i = 0; i < sum; i++) {  
            if (i == this.id)  
                thisPointMap.put(this.id, 0);  
            else {  
                System.out.print("至 顶点" + (i + 1) + " 的距离 ：");  
                boolean flag =true;  
                int len = 0;  
                while(flag){  
                    try {  
                        len = Integer.valueOf(bufr.readLine());  
                        flag = false;  
                    } catch (NumberFormatException e) {  
                        System.out.print("输入有误，请重新输入：");  
                    }  
                };  
                thisPointMap.put(i, len);  
            }  
        }  
    }  
  
    // 该点到顶尖id的 距离。  
    public int lenToPointId(int id) {  
        return thisPointMap.get(id);  
    }  
}  