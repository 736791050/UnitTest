# 单元测试
单元测试（unit testing），是指对软件中的最小可测试单元进行检查和验证。
Java 里单元指的是一个类。

单元测试（模块测试）是开发者编写的一小段代码，用于检验被测代码的一个很小的、很明确的功能是否正确。
通常而言，一个单元测试是用于判断某个特定条件（或者场景）下某个特定函数的行为。

![单元测试目录](images/unit1.png)

[官方文档](https://developer.android.com/studio/test/)

## 一个简单的单元测试
1.在 main 目录下新建一个 UnitTest 类，里面包含一个 add 求和的方法
```java
public class UnitTest {

    public int add(int a, int b){
        return a + b;
    }
}
```
2.对 add 进行单元测试，在 test 对应目录下创建 UnitTestTest，或者在 UnitTest 下 右键 -> Go To -> Test
```java
public class UnitTestTest {

    private UnitTest unitTest;

    @Before
    public void setUp(){
        unitTest = new UnitTest();
    }

    @Test
    public void add() throws Exception {
        int result = unitTest.add(1, 2);
        assertEquals(3, result);
    }

}
```
3.在 UnitTestTest 右键 -> Run "UnitTestTest", 执行结果如下
```java
Process finished with exit code 0
```

4.如果修改验证值
```java
    @Test
    public void add() throws Exception {
        int result = unitTest.add(1, 2);
        assertEquals(4, result);
    }
```
执行结果：
```
    java.lang.AssertionError: 
    Expected :4
    Actual   :3
     ...
    Process finished with exit code 255
```

## 用命令 ./gradlew testDebugUnitTest 执行所有的单元测试
执行完毕后，会在 app/build/reports/tests/testDebugUnitTest/ 目录下生成 index.html 测试报告

![单元测试报告](images/unit2.png)

## Junit4 使用介绍

[junit4 wiki](https://github.com/junit-team/junit4/wiki)

[junit4 javadoc](https://junit.org/junit4/javadoc/latest/)

### 常用注解
| 注解名 | 含义 | 执行次数 |
| ------ | ------ | ------ |
| @Before | 方法之前执行 | 多次 |
| @After | 方法之后执行 | 多次 |
| @BeforeClass | 类所有方法执行前执行 | 一次 |
| @AfterClass | 类所有方法执行后执行 | 一次 |
| @Ignore | 不执行此测试方法 | |
| @Test | 表示是单元测试的方法 | |

#### @Test
对于每一个单元测试方法，都会加一个 @Test 注解。
```java
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface Test {
    static class None extends Throwable {
        private static final long serialVersionUID = 1L;

        private None() {
        }
    }

    Class<? extends Throwable> expected() default None.class;

    long timeout() default 0L;
}
```
通过上面的代码，可以看到 Test 可以传两个值：expected、timeout

* expected 用来验证方法会抛出某个异常，如果没有抛出异常，则会失败

```java
    @Test(expected=IndexOutOfBoundsException.class) 
    public void outOfBounds() {
        new ArrayList<Object>().get(1);
    }
```

* timeout 如果测试方法执行的时间超过了 timeout 的值，就会失败

```java
    @Test(timeout=100) 
    public void sleep100() {
       Thread.sleep(100);
    }
```

### 常用方法
#### assertEquals
* assertEquals(expected, actual)
验证expected的值跟actual是一样的，如果是一样的话，测试通过，不然的话，测试失败。如果传入的是object，那么这里的对比用的是equals()

* assertEquals(expected, actual, tolerance)
这里传入的expected和actual是float或double类型的，大家知道计算机表示浮点型数据都有一定的偏差，所以哪怕理论上他们是相等的，但是用计算机表示出来则可能不是，所以这里运行传入一个偏差值。如果两个数的差异在这个偏差值之内，则测试通过，否者测试失败。

* assertTrue(boolean condition)
验证contidion的值是true

* assertNull(Object obj)
验证obj的值是null

* assertNotNull(Object obj)
验证obj的值不是null

* assertSame(expected, actual)
验证expected和actual是同一个对象，即指向同一个对象

* assertNotSame(expected, actual)
验证expected和actual不是同一个对象，即指向不同的对象

* fail()
让测试方法失败

注：上面的每一个方法，都有一个重载的方法，可以在前面加一个String类型的参数，表示如果验证失败的话，将用这个字符串作为失败的结果报告。
比如：assertEquals("a and b not equals", "a", "b");


## [Mockito](https://site.mockito.org/)

[wiki](https://github.com/mockito/mockito/wiki)

[Unit tests with Mockito](http://www.vogella.com/tutorials/Mockito/article.html)

Mockito 的使用
1. 验证方法调用
2. 指定 mock 对象的某些方法的行为

### 先看一个例子
1.有 LoginPresenter 类
```java
public class LoginPresenter {

    private LoginManager mLoginManager;

    public LoginPresenter(LoginManager loginManager){
        mLoginManager = loginManager;
    }

    public void login(String name, String pwd){
        if("".equals(name) || "".equals(pwd)){
            return;
        }

        mLoginManager.performLogin(name, pwd);
    }
}
```
2.用户登陆时，调用 LoginManager 的 login 方法

如何对 LoginPresenter 的 login 方法进行单元测试？
```java
    @Test
    public void login() throws Exception {
        LoginManager loginManager = Mockito.mock(LoginManager.class);
        LoginPresenter loginPresenter = new LoginPresenter(loginManager);

        String name = "admin";
        String password = "admin123";
        loginPresenter.login(name, password);

        Mockito.verify(loginManager).performLogin(name, password);
    }
```

//TODO
### mock
如果不指定的话，一个mock对象的所有非void方法都将返回默认值：int、long类型方法将返回0，boolean方法将返回false，对象方法将返回null等等；而void方法将什么都不做。
### verify
### doAnser().when()
### doNothing
### doThrow
### doCallRealMethod
### when().thenReturn()
### spy
spy与mock的唯一区别就是默认行为不一样：spy对象的方法默认调用真实的逻辑，mock对象的方法默认什么都不做，或直接返回默认值。


## [Robolectric](http://robolectric.org/)


## Junit Rule

## 异步代码测试