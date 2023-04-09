package org.example;

import org.example.entity.Element;
import org.example.util.ProgressUtil;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<Integer> list = new ArrayList<>();
        list.add(10);
        list.add(8);
        list.add(5);
        list.add(6);
        try{
            List<Element> sort = ProgressUtil.sort(list, Integer::doubleValue, Integer::doubleValue, Element.class);
            System.out.println(sort);
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}