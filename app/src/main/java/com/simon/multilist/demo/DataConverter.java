package com.simon.multilist.demo;

import android.support.annotation.NonNull;

import com.simon.multilist.demo.bean.BaseParentNode;
import com.simon.multilist.core.tree.INode;
import com.simon.multilist.core.tree.IParent;
import com.simon.multilist.demo.bean.Area;
import com.simon.multilist.demo.bean.Cities;
import com.simon.multilist.demo.bean.City;
import com.simon.multilist.demo.bean.Street;

import java.util.ArrayList;
import java.util.List;

/**
 * convert tree to list
 * Created by simon on 17-5-12.
 */

public class DataConverter {

    public static List<INode> convert(@NonNull IParent rootNode) {
        ArrayList<INode> targetList = new ArrayList<>();
        List<? extends INode> children = rootNode.getChildren();
        for (INode child : children) {
            targetList.add(child);
            if(child instanceof BaseParentNode) {
                if(((BaseParentNode) child).isOpen()){
                    List<? extends INode> convert = convert(((IParent) child));
                    targetList.addAll(convert);
                }
            }
        }
        return targetList;
    }

    public static Cities createDemoData() {

        Cities cities = new Cities("重要城市");

        City beijing = new City("北京");

        Area chaoyang = new Area("朝阳");
        Street sanlitun = new Street("三里屯");
        Street wangjing = new Street("望京");
        chaoyang.addChild(sanlitun);
        chaoyang.addChild(wangjing);
        chaoyang.open();
        Area haidian = new Area("海淀");
        Street zhongguancun = new Street("中关村");
        Street wudaokou = new Street("五道口");
        haidian.addChild(zhongguancun);
        haidian.addChild(wudaokou);

        beijing.addChild(chaoyang);
        beijing.addChild(haidian);
        beijing.open();

        City shanghai = new City("上海");

        Area dongqu = new Area("东区");
        dongqu.addChild(new Street("街道一"));
        dongqu.addChild(new Street("街道二"));
        chaoyang.open();
        Area xiqu = new Area("西区");
        xiqu.addChild(new Street("街道三"));
        xiqu.addChild(new Street("街道四"));

        shanghai.addChild(dongqu);
        shanghai.addChild(xiqu);
        shanghai.open();

        cities.addChild(beijing);
        cities.addChild(shanghai);
        cities.open();

        return cities;
    }


}
