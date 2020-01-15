package cn.bx.bid.util;

import java.io.*;
import java.util.*;

import cn.bx.bid.entity.Professor;
import cn.bx.bid.service.ProfessorService;
import freemarker.template.Configuration;
import freemarker.template.Template;

public class DocUtil {
	public static void main(String[] args) {
		//export("d:/2.doc", genData());
		ProfessorService professorService=new ProfessorService();
		Professor p=professorService.get(2L);
		Map<String, Object> m = new HashMap<String, Object>();
		m.put("p",p);
		exportProfessor("e:/2.doc",m);
	}

 /*	public static Map genData() {
		Map<String, Object> m = new HashMap<String, Object>();

		Professor f = new Professor(0L,4L, new java.util.Date(), "老吴", "男", "1112222", "在职", "硕士", "水文工程", "包河区计生办",
				"副处级干部", "老干部", "高级工程师", "13838388222", "地质");
		m.put("p", f);
		List<WorkHistory> list = new ArrayList<WorkHistory>();
		list.add(new WorkHistory(1, "1994-1", "1997-2", "保洁", "保洁员", "包河区政府"));
		list.add(new WorkHistory(1, "1997-3", "1999-12", "保洁", "保洁员", "包河区政府"));
		list.add(new WorkHistory(1, "2000-6", "2007-2", "保洁", "保洁员", "包河区政府"));
		list.add(new WorkHistory(1, "2008-1", "2017-2", "保洁", "保洁员", "包河区政府"));
		list.add(new WorkHistory(1, "2017-2", "2019-1", "工程师", "审计员", "省政府"));
		m.put("workhistories", list);
		return m;
	}*/

	public static void exportProfessor(String newWordName, Map dataMap) {
		Configuration configuration = new Configuration(Configuration.VERSION_2_3_22);
		configuration.setDefaultEncoding("utf-8"); // 注意这里要设置编码
		// 模板文件 采购评标专家库专家抽取登记表.xml是放在WebRoot/template目录下的
		configuration.setClassForTemplateLoading(DocUtil.class, "/xml");
		Template t = null;
		try {
			// 采购评标专家库专家抽取登记表.xml是要生成Word文件的模板文件
			t = configuration.getTemplate("professor.ftl", "utf-8"); // 文件名
			// 还有这里要设置编码
		} catch (Exception e) {
			e.printStackTrace();
		}
		File outFile = null;
		Writer out = null;
		// String filename = newWordName;
		try {
			outFile = new File(newWordName);
			// 还有这里要设置编码
			out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile), "utf-8"));
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		try {

			t.process(dataMap, out);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
