package cn.bx.bid.ctrl;

import cn.bx.bid.entity.Page;
import cn.bx.bid.entity.Professor;
import cn.bx.bid.entity.WorkHistory;
import cn.bx.bid.service.ProfessorService;
import cn.bx.bid.util.AjaxResult;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author
 * @version 1.0
 * @date 2020/01/09
 * @description cn.bx.bid.ctrl
 */
@Controller
public class ProfessorController {
    @Resource
    private ProfessorService professorService;

    /**
     * 日期处理
     */
    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(format, true));
    }

    /**
     * 模糊分页查询
     *
     * @param pageNum
     * @param limit
     * @param professorName
     * @param mobliePhone
     * @param identityNumber
     * @return
     */
    @ResponseBody
    @GetMapping(value = "/professorSearch", produces = "application/json;charset=utf-8")
    public Page<Professor> search(@RequestParam(name = "page", defaultValue = "1") int pageNum, @RequestParam(name = "limit", defaultValue = "10") int limit, @RequestParam(name = "professorName", required = false) String professorName,@RequestParam(name = "mobliePhone",required = false) String mobliePhone, @RequestParam(name = "identityNumber", required = false) String identityNumber) {
        Page<Professor> page = professorService.paging(professorName,mobliePhone,identityNumber, pageNum, limit);
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
    @PostMapping(value = "/getProfessor", produces = "application/json;charset=utf-8")
    public AjaxResult getProfessor(@RequestParam(name = "id") long id) {
        Professor professor = professorService.get(id);
        if (professor != null) {
            return AjaxResult.success(professor);
        }
        return AjaxResult.error("数据接口异常！");
    }


    /**
     * 添加功能
     *
     * @param professor
     * @param
     * @return
     */
    @ResponseBody
    @PostMapping(value = "/professorAdd", produces = "application/json;charset=utf-8")
    public AjaxResult reg(@ModelAttribute("professor") Professor professor) {
        int num = professorService.addProfessor(professor);
        if (num > 0) {
            return AjaxResult.success("添加成功！");
        }
        return AjaxResult.error("添加失败！请核对！");
    }

    /**
     * 修改功能
     *
     * @param professor
     * @param
     * @return
     */
    @ResponseBody
    @PostMapping(value = "/professorUpdate", produces = "application/json;charset=utf-8")
    public AjaxResult update(@ModelAttribute("professor") Professor professor) {
        int num = professorService.updateProfessor(professor);
        if (num > 0) {
            return AjaxResult.success("修改成功");
        }
        return AjaxResult.error("修改失败！请核对！");
    }

    /**
     * 删除功能
     *
     * @param
     * @param
     * @return
     */
    @ResponseBody
    @PostMapping(value = "/professorDel", produces = "application/json;charset=utf-8")
    public AjaxResult del(@RequestParam(name = "id") long id) {
        int r = professorService.delProfessor(id);
        if(r > 0){
            return AjaxResult.success("删除成功！");
        }
        return AjaxResult.error("删除失败！");
    }

    /**
     * 批量删除功能
     *
     * @param
     * @param
     * @return
     */
    @ResponseBody
    @GetMapping(value = "/batchDelProfessor", produces = "application/json;charset=utf-8")
    public AjaxResult batchDel(@RequestParam(value = "idArr[]") long[] ids) {
        int num = professorService.batchDel(ids);
        if (num > 0) {
            return AjaxResult.success("删除成功！");
        }
        return AjaxResult.error("删除失败,请核对！");
    }

    /**
     * 添加工作记录
     *
     * @param workHistory
     * @return
     */
    @ResponseBody
    @PostMapping(value = "/addHistory", produces = "application/json;charset=utf-8")
    public AjaxResult addHistory(@ModelAttribute("professor") WorkHistory workHistory){
        int num = professorService.addHistory(workHistory);
        if(num != 0){
            return AjaxResult.success("添加成功！");
        }
        return AjaxResult.error("添加失败！");
    }

    /**
     * 删除工作记录
     *
     * @param id
     * @param hid
     * @return
     */
    @ResponseBody
    @PostMapping(value = "/delWorkHistory",produces = "application/json;charset=utf-8")
    public AjaxResult delWorkHistory(@RequestParam(name = "id")long id,@RequestParam(name = "hid")long hid){
        if( professorService.delHis(id,hid) != 0){
            return AjaxResult.success("删除成功！");
        }
        return AjaxResult.error("删除失败！");
    }

    /**
     * 根据工作记录id获得指定工作记录
     *
     * @param id
     * @return
     */
    @ResponseBody
    @PostMapping(value = "/getWorkHistory",produces = "application/json;charset=utf-8")
    public AjaxResult getWorkHistory(@RequestParam(name = "id") long id){
        WorkHistory workHistory = professorService.getWorkHistory(id);
        System.out.println(workHistory);
        if(workHistory != null){
            return AjaxResult.success(workHistory);
        }
        return AjaxResult.error("接口请求异常！");
    }

    /**
     * 修改工作记录
     *
     * @param workHistory
     * @return
     */
    @ResponseBody
    @PostMapping(value = "/updateHistory",produces = "application/json;charset=utf-8")
    public AjaxResult updateHistory(@ModelAttribute("workHistory") WorkHistory workHistory){
        if(professorService.updateHistory(workHistory) != 0){
            return AjaxResult.success("修改成功！");
        }
        return AjaxResult.error("修改失败！");
    }
}
