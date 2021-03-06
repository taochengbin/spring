﻿##  前言
为了解决什么问题：
1.前端传入大量繁琐数据需要校验，会产生大量 if else语句以及try catch语句导致代码冗杂，不美观。用@Validated注解可以很好的解决。它根据实体类配置的校验规则在进入controller接口的时候就会自动校验参数。
2.controller中每一个接口请求都要对异常进行处理，若接口不断增多，不同的异常也会不断增多，会导致异常太过紊乱，不好维护且不好拓展。因此用@ControllerAdvice+@ExceptionHandler对异常做统一处理。
##  正文
为了更方便的理解，这里采用demo，提供登录和注册2个接口的方式给大家阐述。
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200629190800602.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzM4NDI1ODAz,size_16,color_FFFFFF,t_70)
**1.编写登录实体类UserLoginReq和注册实体类UserRegisterReq(这里@Getter@Sette注解用的lombok插件可以省去写get，set方法)**
@NotEmpty注解校验是否为空
@Size校验字符串的长度
@Pattern正则表达式校验字符串
UserLoginReq：
```
@Getter
@Setter
public class UserLoginReq {
    @NotEmpty(message = "用户姓名不能为空")
    @Size(max = 64, message = "用户姓名长度不能超过64位")
    private String name;

    @NotEmpty(message = "密码不能为空")
    private String password;
}
```
UserRegisterReq：

```
@Getter
@Setter
public class UserRegisterReq {
    @NotEmpty(message = "用户姓名不能为空")
    @Size(max = 64, message = "用户姓名长度不能超过64位")
    private String name;

    @NotEmpty(message = "用户证件类型不能为空")
    @Pattern(regexp = "^(01|02|03|04|99)$", message = "用户证件类型错误")
    private String cardType;

    @NotEmpty(message = "用户证件号码不能为空")
    @Size(max = 32, message = "用户证件号码长度不能超过32位")
    private String cardNo;

    @NotEmpty(message = "用户联系电话不能为空")
    @Size(max = 32, message = "用户联系电话长度不能超过32位")
    private String contactNo;

    @Size(max = 128, message = "联系地址长度不能超过128位")
    private String contactAddress;
}
```
2.pom.xml引入依赖
（1）spring-boot-starter-validation：提供上示代码中@NotEmpty，@Size，@Pattern等注解校验参数，message参数用于校验失败响应值
```
    <!-- 校验入参 -->
    <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-validation</artifactId>
    </dependency>
```
（2）hutool-all：hutool工具类，是一个很强大的工具类，提供如日期，克隆，类型转化等等很多方法简化代码开发，这里只是用于创建json对象和创建随机数（与此文核心内容无关）

```
    <!-- hutool工具 -->
    <dependency>
      <groupId>cn.hutool</groupId>
      <artifactId>hutool-all</artifactId>
      <version>4.6.13</version>
    </dependency>
```
3.编写controller接口，自定义异常枚举类和请求返回result类
UserController：

```
@RestController
public class UserController {
    @PostMapping(value = "/register")
    public void exceptionLoginDemo(@Validated UserRegisterReq userRegisterReq) {
        System.out.println("user register!");
    }
    
    @PostMapping(value = "/login")
    public void exceptionRegisterDemo(@Validated UserLoginReq userLoginReq) {
        System.out.println("user login!");

    }
}
```
**ExceptionEnum ：枚举类，自定义异常枚举**
```
public enum ExceptionEnum {

    // 请求异常集
    LOGIN_PARAM_EXCEPTION("100101", "登录请求参数错误异常"),
    REGISTER_PARAM_EXCEPTION("100102", "注册请求参数错误异常",true),
    //系统未知异常
    UNKNOWN_EXCEPTION("990101", "未知异常", true);
    // 错误码
    private String code;
    // 响应信息
    private String message;
    // 异常发现是否警告（此参数这里不考虑，只是用于拓展，比如异常发生会通知异常监听系统发出警告）
    private boolean isWarn;
    
    ExceptionEnum(String code, String message) {
        this.code = code;
        this.message = message;
        this.isWarn = false;
    }
    
    ExceptionEnum(String code, String message, boolean isWarn) {
        this.code = code;
        this.message = message;
        this.isWarn = isWarn;
    }
    
    public String getCode() {
        return this.code;
    }
    public String getMessage() {
        return this.message;
    }
    public boolean isWarn() {
        return this.isWarn;
    }
}

```
**Result：自定义返回给用户的结果集：错误码code，信息message，以及响应成功或失败的response数据**

```
@Getter
@Setter
public class Result implements Serializable {
    private static final long serialVersionUID = -4127817000970452353L;
    //默认成功正常
    private static final String SUCCESS_CODE = "200";
    //默认成功消息
    private static final String SUCCESS_MESSAGE = "SUCCESS";
    //状态
    private String code;
    //消息
    private String message;
    //数据
    private Object response;
    
    private Result(Builder builder) {
        code = builder.code == null ? SUCCESS_CODE : builder.code + "";
        message = builder.message == null ? SUCCESS_MESSAGE : builder.message;
        response = builder.response;
    }
    
    public static Builder newBuilder() {
        return new Builder();
    }
    
    public static final class Builder {
        private Integer code;
        private String message;
        private Object response;
        private Builder() {
        }
        public Builder code(Integer val) {
            code = val;
            return this;
        }
        public Builder message(String val) {
            message = val;
            return this;
        }
        public Builder response(Object val) {
            response = val;
            return this;
        }
        public Result build() {
            return new Result(this);
        }
    }
    
    @Override
    public String toString() {
        return JSONUtil.toJsonStr(this);
    }
}

```
4.编写3个异常类以及异常handle：1个父异常FatherException，2个子异常ChildLoginException，ChildRegisterException，ExceptionHandle
**这里示例2个自定义子异常，实际工作中可动态拓展新增多个异常处理**
FatherException：
```
public abstract class FatherException extends Exception{
    private static final long serialVersionUID = 2287623661547050627L;
    
    protected boolean isWarn;
    
    protected String code;
    
    protected String message;

    public FatherException(ExceptionEnum exceptionEnum, String type, JSONObject params) {
        this.code = exceptionEnum.getCode();
        this.message = exceptionEnum.getMessage();
        this.isWarn = exceptionEnum.isWarn();
    }
}
```
ChildLoginException：

```
public class ChildLoginException extends FatherException {
    private static final long serialVersionUID = -7065580338200227702L;
    
    private static final String EXCEPTION_TYPE = "LOGIN";
    
    public ChildLoginException(ExceptionEnum exceptionEnum, JSONObject params) {
        super(exceptionEnum, EXCEPTION_TYPE, params);
    }
}
```
ChildRegisterException:

```
public class ChildRegisterException extends FatherException {
    private static final long serialVersionUID = 816726270176940141L;
    
    private static final String EXCEPTION_TYPE = "REGISTER";
    
    public ChildRegisterException(ExceptionEnum exceptionEnum, JSONObject params) {
        super(exceptionEnum, EXCEPTION_TYPE, params);
    }
}
```
**核心类**：
ExceptionHandle
**当请求来的时候，校验失败会进入ExceptionHandle类，通过instanceof 关键字根据异常类型去跑各自具体的异常处理逻辑，如登录就处理登录的异常，注册就处理注册的异常，若是BindException则是参数校验失败，根据请求url，封装各自的result返回值返回给用户**
（提示：代码中Result.newBuilder()等于new Result（），这是lombok插件创建对象的写法）

```
@ControllerAdvice
public class ExceptionHandle {
    private Logger logger = LoggerFactory.getLogger(ExceptionHandle.class);
    
    private Snowflake snowflake = IdUtil.createSnowflake(1, 1);
    
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result handle(Exception exception, HttpServletRequest request) {
        /*
        创建一个json Response对象（这里写法是因为用的huto工具类） ，请求返回值形如
        data = {
                  "code": "100101",
                  "message": "用户证件类型错误",
                  "response": {
                      "reqId": "1277545207021309952"
                   }
                }
         */
        JSONObject data = JSONUtil.createObj();
        // 生成随机数（huto工具类的方法）
        data.put("reqId", snowflake.nextIdStr());

        if (exception instanceof FatherException) {
            /*
            处理自定义的FatherException异常
             */
            
        } else if (exception instanceof ChildLoginException) {
            /*
            处理ChildLoginException 登录的异常
             */
        } else if (exception instanceof ChildRegisterException) {
            /*
            处理ChildRegisterException 注册的异常
             */
        } else if (exception instanceof BindException) {
            // 取得请求的url
            String reqUrl = this.getRequestUrl(request);
            // 取得BindException异常提示信息
            String message = this.getBindExceptionMessage((BindException) exception);
            switch (reqUrl) {
                // 请求一：登录
                case "/login":
                    request.setAttribute("resCode", ExceptionEnum.LOGIN_PARAM_EXCEPTION.getCode());
                    return Result.newBuilder().code(Integer.valueOf(ExceptionEnum.LOGIN_PARAM_EXCEPTION.getCode())).message(message).response(data).build();
                // 请求二：注册
                case "/register":
                    request.setAttribute("resCode", ExceptionEnum.REGISTER_PARAM_EXCEPTION.getCode());
                    return Result.newBuilder().code(Integer.valueOf(ExceptionEnum.REGISTER_PARAM_EXCEPTION.getCode())).message(message).response(data).build();
            }
        } else {
            // 处理未知异常
            logger.error(exception.getMessage(), exception);
            request.setAttribute("resCode", ExceptionEnum.UNKNOWN_EXCEPTION.getCode());
            return Result.newBuilder().code(Integer.valueOf(ExceptionEnum.UNKNOWN_EXCEPTION.getCode())).message(ExceptionEnum.UNKNOWN_EXCEPTION.getMessage()).response(data).build();
        }
        return null;
    }
```
## postman调用返回
返回响应码code，响应信息message，返回值response
1.注册证件类型支持01，02，03，04，99.不支持22报证件类型错误
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200629192505882.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzM4NDI1ODAz,size_16,color_FFFFFF,t_70)
2.登录密码不能为空
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200629192602414.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzM4NDI1ODAz,size_16,color_FFFFFF,t_70)
Demo的GitHub地址：https://github.com/taochengbin/springMVC
