package com.gupaoedu.design.principle.OCP;

/**
 * @ProjectName gupao-design
 * @Classname JavaDiscountCourse
 * @Description TODO
 * @Date 2021/1/4 11:42
 * @Created by guoziyang
 */
public class JavaDiscountCourse extends JavaCourse {
    public JavaDiscountCourse(Integer id, String name, Double price) {
        super(id, name, price);
    }

    public Double getDiscountPrice() {
        return super.getPrice() * 0.6;
    }
}
