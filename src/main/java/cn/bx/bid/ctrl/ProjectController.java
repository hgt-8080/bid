package cn.bx.bid.ctrl;

import cn.bx.bid.entity.Page;
import cn.bx.bid.entity.Professor;
import cn.bx.bid.entity.ProfessorEnroll;
import cn.bx.bid.entity.Project;
import cn.bx.bid.service.ProjectService;
import cn.bx.bid.util.AjaxResult;
import com.alibaba.fastjson.JSON;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.apache.http.util.EntityUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.http.ParseException;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author
 * @version 1.0
 * @date 2020/1/8
 * @description cn.bx.bid.ctrl
 */
@Controller
public class ProjectController {
    @Resource
    private ProjectService projectService;


    /**
     * 日期处理
     */
    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(format, true));
    }

    /**
     * 查询所有项目
     *
     * @param page
     * @param limit
     * @return
     */
    @GetMapping(value = "projectSearch", produces = "application/json;charset=utf-8")
    @ResponseBody
    public Page<Project> search(@RequestParam(name = "page", defaultValue = "1") int page, @RequestParam(name = "limit", defaultValue = "10") int limit, @RequestParam(name = "name", required = false) String name, @RequestParam(name = "category", required = false) String category) {
        Page<Project> projectPage = projectService.paging(page, limit, name, category);
        return projectPage;
    }

    /**
     * 根据id查看项目
     *
     * @param id
     * @return
     */
    @ResponseBody
    @PostMapping(value = "getProject", produces = "application/json;charset=utf-8")
    public AjaxResult getProject(@RequestParam(name = "id") long id) {
        Project project = projectService.get(id);
        if (project != null) {
            return AjaxResult.success(project);
        }
        return AjaxResult.error("接口请求异常！");
    }

    /**
     * 根据项目id查看所有专家
     *
     * @param id
     * @return
     */
    @ResponseBody
    @PostMapping(value = "/searchProfessor", produces = "application/json;charset=utf-8")
    public Page searchProfessor(@RequestParam(name = "id") long id) {
        Page<ProfessorEnroll> page = projectService.searchProfessorInProjs(id);
        return page;
    }

    /**
     * 添加项目
     *
     * @param session
     * @param p
     * @param file
     * @return
     */
    @ResponseBody
    @PostMapping(value = "addProject",produces ="application/json;charset=utf-8")
    public AjaxResult add(HttpSession session, @ModelAttribute("p") Project p, @RequestParam(value = "fujian", required = false) MultipartFile file) {
        try{
            if(file.isEmpty()==false){
                String oldName = file.getOriginalFilename();
                System.out.println(oldName);
                String prefix = oldName.substring(oldName.lastIndexOf('.'));
                String newFilePath = UUID.randomUUID()+prefix;
                p.setProjectFilePath(newFilePath);
                File path = new File(session.getServletContext().getRealPath("doc"));
                if(path.exists() == false){
                    path.mkdirs();
                }
                try {
                    file.transferTo(new File(path,newFilePath));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        int num=projectService.addProject(p);
        System.out.println(num);
        System.out.println(">>>>>"+p);

        if(num>0){
            return AjaxResult.success("添加成功",num);
        }
        return AjaxResult.error("添加失败");
    }

    @ResponseBody
    @PostMapping(value = "/delProject", produces = "application/json;charset=utf-8")
    public AjaxResult delProject(@RequestParam(name = "id") long id) {
        int num = projectService.delProject(id);
        if(num != 0){
            return AjaxResult.success("删除成功！");
        }
        return AjaxResult.error("删除失败！");
    }

    /**
     * 确定参加
     *
     * @param projid
     * @param pfid
     * @return
     */
    @ResponseBody
    @PostMapping(value = "/profRoleProject", produces = "application/json;charset=utf-8")
    public AjaxResult profRoleProject(@RequestParam(name = "projid") long projid, @RequestParam(name = "pfid") long pfid) {
        int num = projectService.profRoleProject(pfid, projid);
        if (num != 0) {
            return AjaxResult.success("选择成功");
        }
        return AjaxResult.error("更改失败");
    }

    /**
     * 不参加
     *
     * @param proid
     * @param pfid
     * @param reason
     * @return
     */
    @ResponseBody
    @PostMapping(value = "/profnotPresentProject", produces = "application/json;charset=utf-8")
    public AjaxResult profnotPresentProject(@RequestParam(name = "proid") long proid, @RequestParam(name = "pfid") long pfid, @RequestParam(name = "reason") String reason){
        System.out.println(pfid+"-"+proid+"-"+reason);
        int num=this.projectService.profnotPresentProject(proid,pfid,reason);
        if(num != 0){
            return AjaxResult.success("选择成功");
        }
        return AjaxResult.error("更改失败");
    }

    /**
     * 开始抽选专家
     *
     * @param pjid
     * @param category
     * @param distance
     * @return
     */
    @ResponseBody
    @PostMapping(value = "/randomSelect", produces = "application/json;charset=utf-8")
    public AjaxResult randomSelect(@RequestParam(name = "pjid") long pjid, @RequestParam(name = "category") String category, @RequestParam(name = "distance") Double distance) {
        Project p = projectService.get(pjid);
        //考虑了能来的，去掉确定不来的，不确定的也加上了
        int hasSelected = projectService.getPrentProfessors(pjid);
        String msg = "";
        try {
            if (p.getStatus() == 1) {
                msg = "{\"result\":false,\"msg\":\"此项目已经结束，不能再选\"}";
            } else if (hasSelected >= p.getProfessorNum()) {
                msg = "{\"result\":false,\"msg\":\"此项目人数已经满足，无需再选\"}";
            } else {
                List<Professor> list = projectService.getProfessorsForChoose(pjid, category);
                //
                if (distance != null) {//distance>0
                    //根据距离过滤
                    Jw location = getPos(p.getAddress());//项目开标地址的经纬度
                    System.out.println("开会地点。。。。。。" + location);
                    if (location != null) {
                        Iterator<Professor> r = list.iterator();
                        while (r.hasNext()) {
                            Professor t = r.next();
                            Jw l2 = getPos(t.getAddress());//获得专家的经纬度
                            if (l2 == null)
                                continue;//地址不确定的先不考虑
                            System.out.println("专家：" + t.getProfessorName() + " 住址：" + l2 + ">>>距离：" + l2.dis(location) + "公里");
                            if (l2.dis(location) > distance) {
                                r.remove();//删除当前专家
                            }
                        }
                    }
                }
                if (list.size() < (p.getProfessorNum() - hasSelected)) {
                    msg = "{\"result\":false,\"msg\":\"没有找到满足人数的专家，缺少" + (Math.abs(list.size() - (p.getProfessorNum() - hasSelected))) + "人，先修改条件或者重新选择距离再试\"}";
                } else {
                    //开始随机选择
                    int need = p.getProfessorNum() - hasSelected;
                    long ids[] = new long[need];
                    int index = 0;
                    while (true) {
                        int r = (int) (Math.random() * list.size());
                        ids[index++] = list.get(r).getId();
                        list.remove(r);
                        if (index >= ids.length)
                            break;
                    }
                    //保存到数据库
                    int result = this.projectService.saveChooseResult(p, ids);
                    msg = "{\"result\":true,\"msg\":\"成功抽选" + need + "名专家！\"}";
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            msg = "{\"result\":false,\"msg\":\"网络不稳定，稍后再试！\"}";
        }
        return AjaxResult.success(msg);
    }

    //考虑把key放入外部文件中，好灵活配置
    public static final String url = "http://api.map.baidu.com/geocoder/v2/?address=%s&output=json&ak=B4bd9e22c0a580cd04b0e84b17bf9eb6";

    // 获得经纬度
    private Jw getPos(String addr) {
        String str = getDistance(String.format(url, addr));
        Map m = (Map) JSON.parse(str);

        if (!new Integer(0).equals(m.get("status"))) {
            return null;
        }
        Map m2 = (Map) ((Map) m.get("result")).get("location");
        System.out.println(m2);
        double lng = Double.parseDouble(m2.get("lng").toString());
        double lat = Double.parseDouble(m2.get("lat").toString());
        return new Jw(lat, lng);
    }

    public String getDistance(String url) {
        // 获得Http客户端(可以理解为:你得先有一个浏览器;注意:实际上HttpClient与浏览器是不一样的)
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        // 创建Get请求
        HttpGet httpGet = new HttpGet(url);

        // 响应模型
        CloseableHttpResponse response = null;
        try {
            // 由客户端执行(发送)Get请求
            response = httpClient.execute(httpGet);
            // 从响应模型中获取响应实体
            HttpEntity responseEntity = response.getEntity();
            System.out.println("响应状态为:" + response.getStatusLine());
            if (responseEntity != null) {
                System.out.println("响应内容长度为:" + responseEntity.getContentLength());
                // System.out.println("响应内容为:" + EntityUtils.toString(responseEntity));
                return EntityUtils.toString(responseEntity);
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                // 释放资源
                if (httpClient != null) {
                    httpClient.close();
                }
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}

//经纬度描述类
class Jw {
    public double lat, lng;

    public Jw() {
    }

    public Jw(double lat, double lng) {
        super();
        this.lat = lat;
        this.lng = lng;
    }

    public double dis(Jw w) {
        return getDistance(this.lat, this.lng, w.lat, w.lng);
    }

    @Override
    public String toString() {
        return "Jw [lat=" + lat + ", lng=" + lng + "]";
    }

    public static double getDistance(double Lat_A, double Lng_A, double Lat_B, double Lng_B) {
        double ra = 6378.140;
        double rb = 6356.755;
        double flatten = (ra - rb) / ra;
        double rad_lat_A = Math.toRadians(Lat_A);
        double rad_lng_A = Math.toRadians(Lng_A);
        double rad_lat_B = Math.toRadians(Lat_B);
        double rad_lng_B = Math.toRadians(Lng_B);
        double pA = Math.atan(rb / ra * Math.tan(rad_lat_A));
        double pB = Math.atan(rb / ra * Math.tan(rad_lat_B));
        double xx = Math
                .acos(Math.sin(pA) * Math.sin(pB) + Math.cos(pA) * Math.cos(pB) * Math.cos(rad_lng_A - rad_lng_B));
        double c1 = (Math.sin((xx) - xx) * (Math.sin(pA) + Math.sin(pB)) * 2 / Math.cos(xx / 2) * 2);
        double c2 = (Math.sin(xx) + xx) * (Math.sin(pA) - Math.sin(pB)) * 2 / Math.sin(xx / 2) * 2;
        double dr = flatten / 8 * (c1 - c2);
        double distance = ra * (xx + dr);
        // DecimalFormat format = new DecimalFormat("###.000");
        // distance = Double.parseDouble(format.format(distance));
        return Math.abs(distance);
    }
}
