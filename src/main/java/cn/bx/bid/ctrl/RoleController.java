package cn.bx.bid.ctrl;

import cn.bx.bid.entity.User;
import cn.bx.bid.entity.Userrole;
import cn.bx.bid.service.RoleService;
import cn.bx.bid.util.AjaxResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author
 * @version 1.0
 * @date 2020/1/8
 * @description cn.bx.bid.ctrl
 */
@Controller
public class RoleController {
    @Resource
    private RoleService roleService;

    /**
     * 获得所有角色信息
     *
     * @param name
     * @param pass
     * @param request
     * @return
     */
    @ResponseBody
    @GetMapping(value = "/findRoles", produces = "application/json;charset=utf-8")
    public AjaxResult login(@RequestParam(name = "username", required = false) String name, @RequestParam(name = "password", required = false) String pass, HttpServletRequest request) {
        List<Userrole> list = roleService.findALL();
        if (list != null) {
            return AjaxResult.success(list);
        }
        return AjaxResult.error("数据接口异常！");
    }
}
