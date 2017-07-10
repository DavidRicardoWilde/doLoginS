1.struts.xml -- 配置action信息
2.UserDAO.java -- 对数据库操作的相关函数 包括（insert delete等）
3.web.xml 应该是没有问题的
=========================解决=======================================
1.需要更改数据库资源（applicationContext.xml) 怎加了新的dataSource（id=dataSourceTest)
2.userDAO.java 与数据库相关的操作
3.UserDAO.java 中 应该是querySql还是insertSql

problem:
1.ajax 出错 进入了ajax里的error
	没有进入userService.java, 
	userService 的函数没有执行 将query函数改成execute 依旧没有执行
	应该是action的问题 ajaxTest就是好的 ajaxLogin就有问题
		action的包不对 userService在controller package里 之前防盗链model package中
			结果还是不行 ajax还是错误的
		将原本ajaxTest action的class 改成UserServcie再试试
			还是错的
			应该是UserService有问题
		改了数据源 user.hbm.xml没有改 不知道有没有影响
			改完，服务器运行出错
				应该是user.hbm.xml 和 applicationContext.xml有问题
					applicationContext.xml中 与insertSql之间有关的也改成了 UserBean
				applicationContext.xml加了userbean的bean
				
				
				<action name="ajaxTest" class= "CheckUser">
			<result name="success" type="stream">
				<param name="contentType">text/html</param>
				<!-- <param name="inputName">inputStreamTest -->
				<param name="inputName">inputStream</param>
			</result>
		</action>
			
			
			
			
	如何打开jdbc？