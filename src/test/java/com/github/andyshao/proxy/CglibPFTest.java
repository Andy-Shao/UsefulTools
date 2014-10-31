package com.github.andyshao.proxy;

import java.lang.reflect.Method;

import org.junit.Assert;
import org.junit.Test;

import com.github.andyshao.proxy.CglibProxyFactoryTest.MyClass;
import com.github.andyshao.util.Reflects;

public class CglibPFTest {

	@Test
	public void test(){
		CglibProxyFactoryTest.MyClass myClass = new CglibProxyFactoryTest.MyClass();
		
		Assert.assertFalse(myClass.isAllow());
		
		CglibPF<CglibProxyFactoryTest.MyClass> cglibProxyF = new CglibPF<CglibProxyFactoryTest.MyClass>() {
			private final String methodName = ProxyFactory.buildMethodKey(Reflects.getMethod(MyClass.class, "isAllow"));
			
			@Override
			public boolean proxyMethods(MyClass target, Method method, Object[] args) {
				if(this.methodName.equals(ProxyFactory.buildMethodKey(method))) return true;
				return false;
			}
			
			@Override
			public Object invoke(MyClass target, Method method, Object[] args) throws Throwable {
				return true;
			}
			
			@Override
			public Class<?>[] implInterfaces() {
				return new Class<?>[0];
			}
		};
		myClass = cglibProxyF.toProxyFactroy().apply(myClass);
		
		Assert.assertTrue(myClass.isAllow());
	}
}
