


com.alibaba.dubbo.common.bytecode.ProxyTest#testMain

sudo java -jar ~/Software/dumpclass.jar 53702 \*proxyIns0\*

sudo java -jar ~/Software/dumpclass.jar 53702 \*Proxy\*

```
			// create ProxyInstance class.
			String pcn = pkg + ".proxy" + id;
			// for mac os
			pcn = pkg + ".proxyIns" + id;
			ccp.setClassName(pcn);
			ccp.addField("public static java.lang.reflect.Method[] methods;");
			ccp.addField("private " + InvocationHandler.class.getName() + " handler;");
			ccp.addConstructor(Modifier.PUBLIC, new Class<?>[]{ InvocationHandler.class }, new Class<?>[0], "handler=$1;");
            ccp.addDefaultConstructor();
			Class<?> clazz = ccp.toClass();
			clazz.getField("methods").set(null, methods.toArray(new Method[0]));

			// create Proxy class.
			String fcn = Proxy.class.getName() + id;
			ccm = ClassGenerator.newInstance(cl);
			ccm.setClassName(fcn);
			ccm.addDefaultConstructor();
			ccm.setSuperClass(Proxy.class);
			ccm.addMethod("public Object newInstance(" + InvocationHandler.class.getName() + " h){ return new " + pcn + "($1); }");
			Class<?> pc = ccm.toClass();
			proxy = (Proxy)pc.newInstance();
		```
生成类为proxy0 Proxy0，对于macos，这个类不能存在同一目标下，所以要改

