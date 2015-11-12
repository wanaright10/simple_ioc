# simple_ioc
直接在 class 或者 field 上用上注解,就可以通过 BeanFactory 获取了,已经注入好bean,单例的:
```java
@Bean("a")
public class ClassA {
    @Bean
    private ClassB b;
    @Bean
    private ClassC c;
    @Bean
    private ClassD d;
}
```

```java
ClassA classA = BeanFactory.getBean("a", ClassA.class);
ClassB classB = BeanFactory.getBean(ClassB.class);
```
