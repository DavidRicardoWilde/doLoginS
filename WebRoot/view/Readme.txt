1.struts.xml -- ����action��Ϣ
2.UserDAO.java -- �����ݿ��������غ��� ������insert delete�ȣ�
3.web.xml Ӧ����û�������
=========================���=======================================
1.��Ҫ�������ݿ���Դ��applicationContext.xml) �������µ�dataSource��id=dataSourceTest)
2.userDAO.java �����ݿ���صĲ���
3.UserDAO.java �� Ӧ����querySql����insertSql

problem:
1.ajax ���� ������ajax���error
	û�н���userService.java, 
	userService �ĺ���û��ִ�� ��query�����ĳ�execute ����û��ִ��
	Ӧ����action������ ajaxTest���Ǻõ� ajaxLogin��������
		action�İ����� userService��controller package�� ֮ǰ������model package��
			������ǲ��� ajax���Ǵ����
		��ԭ��ajaxTest action��class �ĳ�UserServcie������
			���Ǵ��
			Ӧ����UserService������
		��������Դ user.hbm.xmlû�и� ��֪����û��Ӱ��
			���꣬���������г���
				Ӧ����user.hbm.xml �� applicationContext.xml������
					applicationContext.xml�� ��insertSql֮���йص�Ҳ�ĳ��� UserBean
				applicationContext.xml����userbean��bean
				
				
				<action name="ajaxTest" class= "CheckUser">
			<result name="success" type="stream">
				<param name="contentType">text/html</param>
				<!-- <param name="inputName">inputStreamTest -->
				<param name="inputName">inputStream</param>
			</result>
		</action>
			
			
			
			
	��δ�jdbc��