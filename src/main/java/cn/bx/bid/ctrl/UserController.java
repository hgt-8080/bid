package cn.bx.bid.ctrl;

import cn.bx.bid.entity.User;
import cn.bx.bid.service.UserService;
import cn.bx.bid.util.AjaxResult;
import cn.bx.bid.util.LogUtil;
import cn.bx.bid.util.VerifyCode;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import cn.bx.bid.entity.Page;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author
 * @version 1.0
 * @date 2020/01/07
 * @description cn.bx.bid.ctrl
 */
@Controller
public class UserController {
    @Resource
    private UserService userService;

    /**
     * 日期处理
     */
    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(format, true));
    }

    /**
     * 登录功能
     *
     * @param name
     * @param pass
     * @param request
     * @return
     */
    @ResponseBody
    @PostMapping(value = "/login", produces = "application/json;charset=utf-8")
    public AjaxResult login(@RequestParam("verifyInput") String verifyCode, @RequestParam(name = "username", required = false) String name, @RequestParam(name = "password", required = false) String pass, HttpServletRequest request) {
        User user = userService.login(name, pass);
        if (user != null && verifyCode.equals(request.getSession().getAttribute("verifyCode"))) {
            request.getSession().setAttribute("USER_LOGIN", user);
            return AjaxResult.success("登录成功！", user);
        }
        request.getSession().removeAttribute("verifyCode");
        return AjaxResult.error("登录失败！");
    }

    /**
     * 模糊分页查询
     *
     * @param pageNum
     * @param limit
     * @param name
     * @param start
     * @param end
     * @return
     */
    @ResponseBody
    @GetMapping(value = "/userSearch", produces = "application/json;charset=utf-8")
    public Page<User> search(@RequestParam(name = "page", defaultValue = "1") int pageNum, @RequestParam(name = "limit", defaultValue = "10") int limit, @RequestParam(name = "name", required = false) String name, @RequestParam(name = "startDate", required = false) Date start, @RequestParam(name = "endDate", required = false) Date end) {
        Page<User> page = userService.paging(name, start, end, pageNum, limit);
        return page;
    }

    /**
     * 根据id获得对象
     *
     * @param id
     * @param
     * @return
     */
    @ResponseBody
    @GetMapping(value = "/getUser", produces = "application/json;charset=utf-8")
    public AjaxResult getUser(@RequestParam(name = "id") long id) {
        User user = userService.get(id);
        if (user != null) {
            return AjaxResult.success(user);
        }
        return AjaxResult.error("数据接口异常！");
    }

    /**
     * 修改功能
     *
     * @param user
     * @param
     * @return
     */
    @ResponseBody
    @PostMapping(value = "/userUpdate", produces = "application/json;charset=utf-8")
    public AjaxResult update(@ModelAttribute("user") User user) {
        int num = userService.update(user);
        if (num > 0) {
            return AjaxResult.success("修改成功");
        }
        return AjaxResult.error("修改失败！");
    }

    /**
     * 删除功能
     *
     * @param
     * @param
     * @return
     */
    @GetMapping("/user-del-{id}")
    public String del(@PathVariable("id") int id, Model model) {
        int r = userService.del(id);
        model.addAttribute("msg", r > 0 ? "删除成功" : "删除失败");
        return "redirect:userSearch.jhtml";
    }

    /**
     * 添加功能
     *
     * @param user
     * @param
     * @return
     */
    @ResponseBody
    @PostMapping(value = "/userAdd", produces = "application/json;charset=utf-8")
    public AjaxResult reg(@ModelAttribute("user") User user) {
        int num = userService.add(user);
        if (num > 0) {
            return AjaxResult.success("添加成功！");
        }
        return AjaxResult.error("添加失败！");
    }

    /**
     * 检查是否重名功能
     *
     * @param
     * @param
     * @return
     */
    @ResponseBody
    @PostMapping(value = "/checkUserNameRepeat", produces = "application/json;charset=utf-8")
    public AjaxResult checkUserNameRepeat(@RequestParam("id") long id, @RequestParam("name") String name) {
        boolean flag = userService.checkUserNameRepeat(id, name);
        if (flag) {
            return AjaxResult.success();
        }
        return AjaxResult.error();
    }

    /**
     * 批量删除功能
     *
     * @param
     * @param
     * @return
     */
    @ResponseBody
    @GetMapping(value = "/batchDel", produces = "application/json;charset=utf-8")
    public AjaxResult batchDel(@RequestParam(value = "idArr[]") long[] ids) {
        int num = userService.batchDel(ids);
        if (num > 0) {
            return AjaxResult.success("删除成功！");
        }
        return AjaxResult.error("删除失败！");
    }

    //生成验证码图片
    @RequestMapping("/getVerificationCode")
    public void getVerificationCode(HttpServletResponse response, HttpServletRequest request) {
        try {
            request.getSession().removeAttribute("verifyCode");
            int width = 200;
            int height = 69;
            BufferedImage verifyImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            //生成对应宽高的初始图片
            String randomText = VerifyCode.drawRandomText(width, height, verifyImg);
            //单独的一个类方法，出于代码复用考虑，进行了封装。
            //功能是生成验证码字符并加上噪点，干扰线，返回值为验证码字符
            request.getSession().setAttribute("verifyCode", randomText);
            response.setContentType("image/png");//必须设置响应内容类型为图片，否则前台不识别
            OutputStream os = response.getOutputStream(); //获取文件输出流
            ImageIO.write(verifyImg, "png", os);//输出图片流
            os.flush();
            os.close();//关闭流
        } catch (IOException e) {
            LogUtil.error(e.getMessage());
            e.printStackTrace();
        }
    }

}

