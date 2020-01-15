package cn.bx.bid.util;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MyBatisUtil {
	private static SqlSessionFactory sf;
	static {
		String fileName = "mybatis.xml";
		InputStream in;
		try {
			in = Resources.getResourceAsStream(fileName);
			sf = new SqlSessionFactoryBuilder().build(in);

		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}

	}

	private MyBatisUtil() {
	}

	public static SqlSession get() {
		return sf.openSession();
	}

	public static SqlSession get(boolean autoCommit) {
		return sf.openSession(autoCommit);
	}

	public static void close(SqlSession s) {
		if (s != null)
		{
			s.commit();
			s.close();
		}
	}
}
