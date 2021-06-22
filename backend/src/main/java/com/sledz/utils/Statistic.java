package com.sledz.utils;

import com.sledz.services.ProductProvider.ExternalProduct;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.Stream;

public class Statistic {

    public static Random r = new Random(2137);
    public static Double average(List<ExternalProduct> extProdList) {
        return extProdList.stream().map(e -> e.getPrice().doubleValue()).reduce((a,b)->a+b).get()/extProdList.size();
    }

    public static double normal(double mean, double std){
        return r.nextGaussian() * std + mean;
    }

    public static double[] stoch(int n, double mean, double std)
    {
        return DoubleStream.generate(() -> {return normal(mean,std);}).limit(n).toArray();
    }

    public static double[] stochd(int n, double start, double std)
    {
        double s = start;
        return DoubleStream.generate(() -> {return s+normal(0,std);}).limit(n).toArray();
    }

    /*static <T> T[] choose(T[] list,int n, boolean unique) {
        LinkedList<String> ret = new LinkedList<>();
       for( int i=0;i<n;i++)
       {
           int t = r.nextInt(list.length);
           if()

       }
    }*/

    public static List<String> perm(List<String> a, List<String> b, int n){
        LinkedList<String> ret = new LinkedList<>();
        for(String i : a)
        {
            for(String j : b)
            {
                ret.add(i+" "+j);
            }
        }
        Collections.shuffle(ret);
        return ret.stream().limit(n).collect(Collectors.toList());
    }
}
