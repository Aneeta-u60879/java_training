package com.training.ust.sample;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import org.reactivestreams.Subscription;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class Demo1 {

	public static void main(String[] args) {
		System.out.println(" Empty Mono ");
	    Mono.empty().subscribe(System.out::println);
	 
	    System.out.println("Mono.just ");
	    Mono.just("Rahul")
	      .map(item -> "Mono item: " + item)
	      .subscribe(System.out::println);
	    
	    System.out.println(" Empty Flux ");
	    Flux.empty()
	      .subscribe(System.out::println);
	 
	    System.out.println("Conversion to Upper case");
	    Flux.just("RAHUL", "SHIBU", "VAISHNAVI", "TONY")
	      .map(item -> item.toUpperCase())
	      .subscribe(System.out::print);
	 
	    System.out.println("\n Conversion to lower case");
	    List<String> list = Arrays.asList("RAHUL", "SHIBU", "VAISHNAVI", "TONY");
	    Flux.fromIterable(list)
	      .map(item -> item.toLowerCase())
	      .subscribe(System.out::print);
	 
	   

	    
	}

}
