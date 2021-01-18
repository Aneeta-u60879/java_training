package com.training.ust.sample;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.Arrays;

public class StreamDemo {

                public static void main(String[] args) {
                                // TODO Auto-generated method stub
                                
                                List<String> names = Arrays.asList("Alan", "Ajin", "Kiran", "Bond", "Sarika", "amala", "Harry", "Shiva", "Sarah");
                                /*Strings that with S*/
                                List<String> result = names.stream().filter(name -> name.startsWith("S")).collect(Collectors.toList());
                                System.out.println("Strings that start with S");
                                result.forEach(name -> System.out.println(name));
                                
                                /*Strings that start with A in lowercase*/
                                List<String> result2 = names.stream().sorted().filter(name -> name.startsWith("A")).collect(Collectors.toList());
                                List<String> result3 = result2.stream().map(String::toLowerCase).collect(Collectors.toList());
                                System.out.println("Strings that start with a in lowercase");
                                System.out.println(result3);
                                
                                //List<Integer> list1 = Arrays.asList(2,4,6,8,10);
                                int array[]=new int[]{2,4,6,8,10};
                                int s=  (int) Arrays.stream(array).map(b->b*b).average().orElse(Double.NaN);
                                //int s= (int) Arrays.stream(a).average().orElse(Double.NaN);
                                System.out.println("Average of sqare of given elements : "+s);

                }

}
