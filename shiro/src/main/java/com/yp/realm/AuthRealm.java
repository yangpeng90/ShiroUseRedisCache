package com.yp.realm;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import com.yp.domain.Module;
import com.yp.domain.Role;
import com.yp.domain.User;
import com.yp.service.UserService;

public class AuthRealm extends AuthorizingRealm {

	private UserService userService;
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	//认证  登录 token 代表用户在界面输入的用户名和密码
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
		System.out.println("认证");
		UsernamePasswordToken upToken = (UsernamePasswordToken)token;
		
		/*//2.调用业务方法，实现根据用户名查询
		String hql = "from User where user_name= ?";
		List<User> list = userService.find(hql, User.class,upToken.getUsername());
		if(list!=null && list.size()>0){
			User user = list.get(0);
			System.out.println("持久化对象的hashCode:" + user.hashCode());
			//认证信息中包含用户信息，先认证再获取授权
			//Please use org.apache.shiro.authc.SimpleAuthenticationInfo.SimpleAuthenticationInfo(Object principal,
			//Object hashedCredentials, String realmName).因为这个redisCache是支持的。
			AuthenticationInfo info = new SimpleAuthenticationInfo(user,user.getPassword(),this.getName());
			return info;   //此处如果返回，就会立即进入到密码比较器
		}*/
		
		// 假如从数据库中查到的user如下
		User user = new User();
		user.setUsername("pengyang");//
		user.setPassword("b9501b87df416d8c13054c8ee6802bbb");//"py1234"
		
		AuthenticationInfo info = new SimpleAuthenticationInfo(user,
				user.getPassword(), this.getName());
		return info;
		
	}
	
	//获取当前用户的授权模块列表，即那些模块能访问，当jsp页面出现Shiro标签时，就会执行授权方法
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		System.out.println("授权");
		//获取当前用户getName()父类的方法
		User user = (User)principals.fromRealm(getName()).iterator().next();
		//得到权限字符串
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		
		/*//获取用户分配的角色
		User persistentUser = userService.get(User.class, user.getId());
		Set<Role> roles = persistentUser.getRoles();
		//用户授权的模块
		List<String> permissions = new ArrayList<String>();
		for(Role role :roles){
			//获取角色授权的模块
			Set<Module> modules = role.getModules();
			for(Module m:modules){
				permissions.add(m.getName());
			}
		}*/
		
		List<String> permissions = new ArrayList<String>();
		permissions.add("删除");

		// 授权信息保存在redis,即便是应用程序重启都还在，时常30分钟
		info.addStringPermissions(permissions);
		return info;
	}
}
