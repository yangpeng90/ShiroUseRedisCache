# ShiroUseRedisCache
shiro 与 spring 整合，使用redis做缓存，实现集群的单点登陆demo

----------
# applicationContext-shiro.xml
    <?xml version="1.0" encoding="UTF-8"?>
	<beans xmlns="http://www.springframework.org/schema/beans"  
		xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"       
		xmlns:p="http://www.springframework.org/schema/p"  
		xmlns:context="http://www.springframework.org/schema/context"   
		xmlns:tx="http://www.springframework.org/schema/tx"  
		xmlns:aop="http://www.springframework.org/schema/aop"  
		xsi:schemaLocation="http://www.springframework.org/schema/beans    
		http://www.springframework.org/schema/beans/spring-beans.xsd    
		http://www.springframework.org/schema/aop    
		http://www.springframework.org/schema/aop/spring-aop.xsd    
		http://www.springframework.org/schema/tx    
		http://www.springframework.org/schema/tx/spring-tx.xsd    
		http://www.springframework.org/schema/context    
		http://www.springframework.org/schema/context/spring-context.xsd">
	
		<description>Shiro与spring整合，使用redis做缓存</description>
		
		<!-- 开发者设置密码加密策略 -->
		<bean id="passwordMatcher" class="com.yp.encrypt.CustomCredentialsMatcher"/>
	
	    <!-- Used by the SecurityManager to access security data (users, roles, etc).
	         Many other realm implementations can be used too (PropertiesRealm, LdapRealm, etc.-->
	    <!-- 开发者自定义权限认证域，realm实现用户登录认证，授权及校验授权，同时保存认证与授权信息到缓存。可以配置
	    	  多个realm域。方式一，可以配置authenticator中的realms集合，并加上authenticationStrategy，然后，
	    	  把authenticator配置到securityManager。方式二，也可以在securityManager配置realms集合，它会自动
	    	  设置authenticator中realms集合。注，在本例中使用单realm，所以未配置authenticator-->
	    <bean id="authRealm" class="com.yp.realm.AuthRealm">
	    	<!-- 数据库加载认证授权信息
			<property name="userService" ref="userService"/> -->
			<!-- 自定义密码加密算法  -->
			<property name="credentialsMatcher" ref="passwordMatcher"/>
		</bean>
		
		<!-- 用户授权/认证信息Cache, 采用EhCache  缓存
	    <bean id="shiroEhcacheManager" class="org.apache.shiro.cache.ehcache.EhCacheManager">
	        <property name="cacheManagerConfigFile" value="classpath:ehcache-shiro.xml"/>
	    </bean> -->
	    
	    <!-- shiro redisManager -->
		<bean id="redisManager" class="org.crazycake.shiro.RedisManager">
			<property name="host" value="192.168.110.88"/>
			<property name="port" value="6379"/>
			<property name="expire" value="1800"/>
			<!-- optional properties:
			<property name="timeout" value="10000"/>
			<property name="password" value="123456"/>
			-->
		</bean>
	    
	    <!-- Session ID 生成器
		<bean id="sessionIdGenerator"
			class="org.apache.shiro.session.mgt.eis.JavaUuidSessionIdGenerator"/>-->

	    <!-- redisSessionDAO 父类构造器已经初始化了一个UUID生成器无需注入sessionIdGenerator-->
		<bean id="redisSessionDAO" class="org.crazycake.shiro.RedisSessionDAO">
			<property name="redisManager" ref="redisManager" />
		</bean>

		<!-- 会话Cookie模板，默认DEFAULT_MAX_AGE = -1，this.httpOnly = true -->  
		<bean id="sessionIdCookie" class="org.apache.shiro.web.servlet.SimpleCookie">  
		    <property name="name" value="SJSSIONID"/>  
		</bean> 

	    <!-- sessionManager 可以将这个bean注入到securityManager中，此时要去掉securityManager的native属性，
	    	  同时将sessionDao注入到此sessionManager,sessionIdCookieEnabled默认为true无需设置 -->
		<bean id="sessionManager" class="org.apache.shiro.web.session.mgt.DefaultWebSessionManager">
			<property name="sessionDAO" ref="redisSessionDAO" />
			<property name="sessionIdCookie" ref="sessionIdCookie"/>
		</bean>

	    <!-- cacheManager -->
		<bean id="cacheManager" class="org.crazycake.shiro.RedisCacheManager">
			<property name="redisManager" ref="redisManager" />
		</bean>
		
		<!-- 默认的web安全管理，默认开启 HTTP_SESSION_MODE，原文：Web applications by default do not use a native session 
			 manager and instead retain the Servlet Container's default session manager which does not support 
			 a SessionDAO。可以设置sessionMode = native，这样securityManager就创建并使能DefaultWebSessionManager管理session。
		-->
	    <bean id="securityManager" class="org.apache.shiro.web.mgt.DefaultWebSecurityManager">
	        <!-- <property name="sessionMode" value="native"/> -->
	        <!-- Single realm app.  If you have multiple realms, use the 'realms' property instead.
	        	 在多realm中，shiro默认只要其中的一个realm认证通过，就通过。可以设置认证策略
	        	 通过authenticationStrategy设置认证策略，认证策略配置在authenticator中(未配置)
	         -->
	        <!-- 引用自定义的realm -->
	        <property name="realm" ref="authRealm"/>
	        <!-- EhCache缓存 用来保存授权信息
	        <property name="cacheManager" ref="shiroEhcacheManager"/>-->
	        
	        <!-- redis缓存 用来保存授权信息-->
	        <property name="cacheManager" ref="cacheManager"/>
	        <!-- redis实现的sessionManager -->
	        <property name="sessionManager" ref="sessionManager"/>
	        
	        <!-- 注入sessionDao
	        <property name="sessionManager.sessionDao" ref="redisSessionDAO"/> -->
	        
	        <!-- <property name="rememberMeManager.cookie.maxAge" value="30"></property> -->
	    </bean>
	    
	    <!-- Define any javax.servlet.Filter beans you want anywhere in this application context.   -->
		<!-- They will automatically be acquired by the 'shiroFilter' bean above and made available -->
		<!-- to the 'filterChainDefinitions' property.  Or you can manually/explicitly add them     -->
		<!-- to the shiroFilter's 'filters' Map if desired. See its JavaDoc for more details.       -->
		<!-- <bean id="someFilter" class="..."/> -->
		<!-- <bean id="anotherFilter" class="..."></bean> -->
		
		<!-- 配置一个 bean, 该 bean 实际上是一个 Map. 通过实例工厂方法的方式 -->
	    <bean id="filterChainDefinitionMap" 
	    	factory-bean="filterChainDefinitionMapBuilder" factory-method="buildFilterChainDefinitionMap"></bean>
	    
	    <bean id="filterChainDefinitionMapBuilder"
	    	class="com.yp.filterChainDefinition.FilterChainDefinitionMapBuilder"></bean>
	
	    <!-- filter-name这个名字的值来自于web.xml中filter的名字，因为 Shiro 会来 IOC 容器中查找和 <filter-name>
	    	 名字对应的 filter bean.-->
	    <bean id="shiroFilter" class="org.apache.shiro.spring.web.ShiroFilterFactoryBean">
	        <property name="securityManager" ref="securityManager"/>
	        <!--登录页面  -->
	        <property name="loginUrl" value="/index.jsp"></property>
	        <!-- 登录成功后   
	        <property name="successUrl" value="/home.action"></property> --> 
	        <!-- 'filters'属性是没有必要的，因为定义的任何声明的javax.servlet.Filter bean将自动获得并通过
		    	  它的beanName在链定义中可用，但是如果你喜欢的话，你可以在这里执行实例覆盖或者起别名： -->
		    <!-- <property name="filters">
		        <util:map>
		            <entry key="anAlias" value-ref="someFilter"/>
		        </util:map>
		    </property> -->
		    
		    <property name="filterChainDefinitionMap" ref="filterChainDefinitionMap"></property>
		    <!--  /**代表下面的多级目录也过滤，可以将下列值配置到filterChainDefinitionMap中，以后可以从数据库加载
	        <property name="filterChainDefinitions">
	            <value>
					/index.jsp* = anon
					/login* = anon
					/logout* = anon
					/home* = anon
					
					# URL-to-FilterChain(we customized) definitions here.And you can also add in map
	                /** = someFilter
	                /** = anotherFilter
	                
	                # everything else requires authentication:
					/** = authc
					/*.* = authc
	            </value>
	        </property> -->
	    </bean>
	    
	    <!-- 保证实现了Shiro内部lifecycle方法的bean执行 -->
	    <bean id="lifecycleBeanPostProcessor" class="org.apache.shiro.spring.LifecycleBeanPostProcessor"/>
	
	    <!-- 生成代理，通过代理进行控制，shiro注解也将被激活，必须在配置了 LifecycleBeanPostProcessor 之后才可以使用-->
	    <bean class="org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator"
	          depends-on="lifecycleBeanPostProcessor">
	          <property name="proxyTargetClass" value="true"/>
	    </bean>
	    <bean class="org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor">
	        <property name="securityManager" ref="securityManager"/>
	    </bean>
    
	</beans>