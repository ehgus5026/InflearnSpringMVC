package com.example.SpringMVC.basic;


import lombok.Data;

@Data   // @Getter, @Setter, @EqualAndHashCode, @RequiredArgsConstructor, @toString 자동 생성
public class HelloData {

    private String userName;
    private int age;

}
