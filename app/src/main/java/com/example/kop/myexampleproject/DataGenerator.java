package com.example.kop.myexampleproject;

import com.example.kop.myexampleproject.entity.MainBean;
import com.example.kop.myexampleproject.ui.camera.CameraActivity;
import com.example.kop.myexampleproject.ui.log.LogActivity;
import com.example.kop.myexampleproject.ui.recyclerview.RecyclerActivity;
import com.example.kop.myexampleproject.weiget.chart.RadarChartActivity;
import com.example.kop.myexampleproject.weiget.degree.DegreeActivity;
import com.example.kop.myexampleproject.weiget.point.PointActivity;
import java.util.ArrayList;

/**
 * 功    能: 初始化首页数据
 * 创 建 人: KOP
 * 创建日期: 2019-05-24 17:13
 */
public class DataGenerator {

    public static ArrayList<MainBean> generateData() {
        ArrayList<MainBean> list = new ArrayList<>();
        list.add(new MainBean("RecyclerView", "点击Item自动滑动到中间", RecyclerActivity.class));
        list.add(new MainBean("Camera", "调用系统拍照剪裁、权限申请、路径储存获取", CameraActivity.class));
        list.add(new MainBean("Point", "判断手指是否在圆内", PointActivity.class));
        list.add(new MainBean("Degree", "弧度、角度练习", DegreeActivity.class));
        list.add(new MainBean("Log保存", "log保存", LogActivity.class));
        list.add(new MainBean("雷达图表", "雷达图表、角度练习", RadarChartActivity.class));
        return list;
    }
}
