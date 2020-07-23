package cn.com.common.utils;

import java.util.Properties;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;

@Intercepts({ @Signature(type = Executor.class, method = "query", args = {
		MappedStatement.class, Object.class }) })
public class MyInterceptor implements Interceptor {
	private String dialect;

	@Override
	public Object intercept(Invocation arg0) throws Throwable {
		RoutingStatementHandler sh=(RoutingStatementHandler)arg0.getTarget();
		return null;
	}

	@Override
	public Object plugin(Object arg0) {
		return Plugin.wrap(arg0, this);
	}

	@Override
	public void setProperties(Properties arg0) {
		this.dialect=arg0.getProperty("dialect");

	}

}
