package com.xinlin.test.adapter;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.ws.Endpoint;
/**
 * 
 * 此处发布的服务
 * 服务方法参数，带@WebParam(name="name") 标签
 * 测试类PersonServiceRemoteWSOpTest 调用方法不同，注意区分
 * @author JXQ
 * 
 */
@WebService(targetNamespace="http://www.xinlinex.cn",serviceName="MyService")
public class PersonService {
	@WebMethod(operationName="helloOp")
	@WebResult(name="retWWW")
	public Person sayHello(
			@WebParam(name="name")
			String name,
			@WebParam(name="age")
			int age){
		System.out.println("sayHello called..."+name);
		Person p = new Person();
		p.setName("this is "+name+" visited...");
		p.setAge(age);
		//return "hello " + name;
		return p;
	}
	@WebMethod(exclude=true)
	public String sayHello2(String name){
		System.out.println("sayHello called...");
		return "hello " + name;
	}
	
	public static void main(String[] args) {
		//参数1：绑定服务的地址   参数2：提供服务的实例
		Endpoint.publish("http://127.0.0.1:5679/PersonWS", new PersonService());
		System.out.println("server ready...");
	}
}
