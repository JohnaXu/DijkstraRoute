package com.filetool.main;

import com.filetool.util.FileUtil;
import com.filetool.util.LogUtil;
import com.routesearch.route.Route;


/**
 * 工具入口
 * 
 * @author
 * @since 2016-3-1
 * @version v1.0
 */
public class Main
{
    public static void main(String[] args)
    {
        if (args.length != 5)
        {
            System.err.println("please input args: NodeNumber, startNode, " +
               " endNode, graphFilePath, resultFilePath");
            return;
        }
        String nodeNumber = args[0];
        String startNode = args[1];
        String endNode = args[2];
        String graphFilePath = args[3];
        String resultFilePath =args[4];

        LogUtil.printLog("Begin");

        // 读取输入文件
        String graphContent = FileUtil.read(graphFilePath, null);
        //String conditionContent = FileUtil.read(conditionFilePath, null);

        // 功能实现入口
        // String resultStr = Route.searchRoute(graphContent, conditionContent);
        String resultStr = Route.searchRoute(graphContent,nodeNumber,startNode,endNode);

        System.out.println("result in main: " + resultStr);

        // 写入输出文件
        FileUtil.write(resultFilePath, resultStr, false);

        LogUtil.printLog("End");
    }

}
