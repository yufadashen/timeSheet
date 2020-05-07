package com.intehel.controller;

import com.github.pagehelper.PageInfo;
import com.intehel.common.util.*;
import com.intehel.model.DemoUser;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @author 余发
 */

@ResponseBody
@Controller
@RequestMapping("/test")
@Api(value = "Swagger2")
public class TestController {
/**
 * http://127.0.0.1:8080/swagger-ui.html
     * @Api：用在类上，说明该类的作用
     * @ApiOperation：用在方法上，说明方法的作用
     * @ApiImplicitParams：用在方法上包含一组参数说明
     * @ApiImplicitParam：用在 @ApiImplicitParams 注解中，指定一个请求参数的各个方面
     *     paramType：参数放在哪个地方
     *         · header --> 请求参数的获取：@RequestHeader
     *         · query -->请求参数的获取：@RequestParam
     *         · path（用于restful接口）--> 请求参数的获取：@PathVariable
     *         · body（不常用）
     *         · form（不常用）
     *     name：参数名
     *     dataType：参数类型
     *     required：参数是否必须传
     *     value：参数的意思
     *     defaultValue：参数的默认值
     * @ApiResponses：用于表示一组响应
     * @ApiResponse：用在@ApiResponses中，一般用于表达一个错误的响应信息
     *     code：数字，例如400
     *     message：信息，例如"请求参数没填好"
     *     response：抛出异常的类
     * @ApiModel：描述一个Model的信息（这种一般用在post创建的时候，使用@RequestBody这样的场景，请求参数无法使用@ApiImplicitParam注解进行描述的时候）
     * @ApiModelProperty：描述一个model的属性
     * @return
*/




    /**
     * @ApiOperation 用在方法上，说明方法的作用
     *      value=接口作用（删除用户）
     *      notes=接口说明（通过id删除用户）
     *      httpMethod=指定请求方式
     */
    @RequestMapping("test01")
    @ApiOperation(value = "test01", notes = "测试无参调用Swagger2", httpMethod = "GET")
    public String test01() {
        System.err.println("test01调用成功");
        return "调用成功";
    }

    /**
     *  @ApiImplicitParam 指定一个请求参数的各个方面
     *     paramType：参数放在哪个地方  （这个值写错可能会使controller接受的参数为null）
     *         · header --> 请求参数的获取：@RequestHeader
     *         · query -->请求参数的获取：@RequestParam （常用）
     *         · path（用于restful接口）--> 请求参数的获取：@PathVariable
     *         · body（不常用）
     *         · form（不常用）
     *     name：参数名
     *     dataType：参数类型
     *     required：参数是否必须传
     *     value：参数的意思
     *     defaultValue：参数的默认值
//     * @param str 传入字符串
     * @return
     */
    @RequestMapping("test02")
    @ApiOperation(value = "test02", notes = "测试单个参调用Swagger2", httpMethod = "GET")
    @ApiImplicitParam(name = "name", value = "姓名",paramType = "query",required = true,  dataType = "String",defaultValue="张三")
    public String test02(String name) {
        System.err.println("test02调用成功"+name);
        return "调用成功，str="+name;
    }


    /**
     * @ApiParam 和@ApiImplicitParam作用一样
     * @RequestParam 由于@ApiParam无法设置paramType的值，所以用户@RequestParam可以代替paramType = "query"
     * @param str
     * @return
     */
    @RequestMapping("test03")
    @ApiOperation(value = "test03", notes = "测试单个参调用Swagger2", httpMethod = "GET")
//    @ApiImplicitParam(name = "str", value = "单个参数描述",paramType = "query",required = true,  dataType = "String",defaultValue="默认值")
    public String test03(@RequestParam @ApiParam(name = "str",value = "单个参数描述",required = true ) String str) {
        System.err.println("test03调用成功"+str);
        return "调用成功，str="+str;
    }

    /**
     * @ApiImplicitParams：用在方法上包含一组参数说明
     * @param str1
     * @param str2
     * @return
     */
    @RequestMapping("test04")
    @ApiOperation(value = "test04", notes = "测试多个参调用Swagger2", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "str1", value = "第一个参数描述",paramType = "query",required = true,  dataType = "Integer",defaultValue="123"),
            @ApiImplicitParam(name = "str2", value = "第二个参数描述",paramType = "query",required = true,  dataType = "String",defaultValue="默认值")
    })
    public String test04(Integer str1,String str2) {
        System.err.println("test03调用成功，str1="+str1+",str2="+str2);
        return "调用成功，str1="+str1+",str2="+str2;
    }


    /**
     * 添加用户
     *
     * 在使用对象封装参数进行传参时，需要在该对象添加注解，将其注册到swagger中
     * @link com.zhongying.api.model.base.DemoUser
     *
     * 注意： 在后台采用对象接收参数时，Swagger自带的工具采用的是JSON传参，
     *     测试时需要在参数上加入@RequestBody,正常运行采用form或URL提交时候请删除。
     *
     * @param user 用户类对象
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value="/user",  method= RequestMethod.POST )
    @ApiOperation(value="添加用户信息", notes="")
    public String adduser(@RequestBody DemoUser user) throws Exception{
        if(null == user || user.getId() == null){
            return "(\"添加用户失败\",\"DT3388\",\"未知原因\",\"请联系管理员\")";
        }
        try {
            System.out.println("成功----------->"+user.getName());
        } catch (Exception e) {
            return "(\"添加用户失败\",\"DT3388\",\"未知原因\",\"请联系管理员\")";
        }

        return user.getId().toString();
    }


    /**
     * 删除用户
     * @param userId 用户ID
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/user/{userId}",  method= RequestMethod.DELETE )
    @ApiOperation(value="删除用户信息", notes="")
    @ApiImplicitParam(paramType="query", name = "userId", value = "用户ID", required = true, dataType = "Integer")
    public String deleteuser(@RequestParam Integer userId){
        if(userId > 2){
            return "删除失败";
        }
        return "删除成功";
    }

    /**
     * 修改用户信息
     * @param userId 用户ID
     * @param user 用户信息
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value="/user/{userId}",  method= RequestMethod.POST )
    @ApiOperation(value="根据修改用户信息", notes="")
    @ApiImplicitParam(paramType="query", name = "userId", value = "用户ID", required = true, dataType = "Integer")
    public String updateuser(@RequestParam Integer userId, @RequestBody DemoUser user) throws Exception{
        if(null == userId || null == user){
            return "(\"修改用户信息失败\",\"DT3391\",\"id不能为空\",\"请修改\")";
        }
        if(userId > 5 ){
            return "(\"修改用户信息失败\",\"DT3392\",\"id不能为空\",\"请修改\")";
        }
        System.out.println(userId);
        System.out.println(user);
        return "修改成功";
    }

    /**
     * 获取用户详细信息
     * @param userId 用户ID
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value="/user/{userId}",  method= RequestMethod.GET )
    @ApiOperation(value="获取用户详细信息", notes="仅返回姓名..")
    @ApiImplicitParam(paramType="query", name = "userId", value = "用户ID", required = true, dataType = "Integer")
    public DemoUser getuserDetail(@RequestParam Integer userId) throws Exception{
        System.out.println(userId);
        if(null == userId){
            throw new MyException("查看用户信息失败\",\"DT3390\",\"未知原因\",\"请联系管理员");
        }
        if(userId > 3){
            throw new MyException("查看用户信息失败\",\"DT3390\",\"未知原因\",\"请联系管理员");
        }
        DemoUser user = new DemoUser();
        user.setId(1);
        user.setName("测试员");
        return user;
    }

    /**
     * 获取用户列表
     * @param pageIndex 当前页数
     * @param pageSize 每页记录数
     * @param request
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value="/user",  method= RequestMethod.GET )
    @ApiOperation(value="获取用户列表", notes="目前一次全部取，不分页")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="header", name = "token", value = "token", required = true, dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "pageIndex", value = "当前页数", required = false, dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "pageSize", value = "每页记录数", required = true, dataType = "String"),
    })
    public PageInfo<DemoUser> getuserList(@RequestParam(value = "pageIndex", required = false, defaultValue = "1") Integer pageIndex,
                                          @RequestParam(value = "pageSize", required = false) Integer pageSize,
                                          HttpServletRequest request) throws Exception{

        String token = request.getHeader("token");
        if(null == token){
            throw new MyException("没有权限\",\"SS8888\",\"没有权限\",\"请查看操作文档");
        }
        if(null == pageSize){
            throw new MyException("每页记录数不粗安在\",\"DT3399\",\"不存在pageSize\",\"请查看操作文档");
        }

        DemoUser user1 = new DemoUser();
        user1.setId(1);
        user1.setName("测试员1");
        DemoUser user2 = new DemoUser();
        user2.setId(2);
        user2.setName("测试员2");

        List<DemoUser> userList = new ArrayList<DemoUser>();
        userList.add(user1);
        userList.add(user2);
        return new PageInfo<DemoUser>(userList);
    }







    private static final Logger logger = LoggerFactory.getLogger(TestController.class);

    @RequestMapping("/test0")
    @ApiOperation(value="测试", notes="测试Seagger2",httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "s1", value = "字符串1",paramType = "query",  dataType = "String"),
            @ApiImplicitParam(name = "s2", value = "字符串2",paramType = "query", required = true, dataType = "String")
    })
    public String test0(String s1, String s2){
        logger.trace("测试logger日志输出trace数据 s1=="+s1+",s2="+s2);
        logger.error("测试logger日志输出error数据 s1=="+s1+",s2="+s2);
        logger.debug("测试logger日志输出debug数据 s1=="+s1+",s2="+s2);
        logger.info("测试logger日志输出info数据s1=="+s1+",s2="+s2);
        System.err.println("进入方法,传入参数的值= s1=="+s1+",s2="+s2);
        return "测试返回数据成功 s1=="+s1+",s2="+s2;
    }








}
