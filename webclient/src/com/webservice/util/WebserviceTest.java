package com.webservice.util;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.InputStreamRequestEntity;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;


public class WebserviceTest {

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		// 请求报文
		String soapRequestData = "<soapenv:Envelope "
				+ "xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" "
				+ "xmlns:rb=\"http://xmlns.oracle.com/Enterprise/Tools/schemas/RB_BACKFLOW_REQ.v1\">"
				+ "<soapenv:Header/>" + "<soapenv:Body>" + "<parameter>"
				+ "<rb:NationalID>110111199001010076</rb:NationalID>"
				+ "</parameter></soapenv:Body></soapenv:Envelope>";
		// 创建post方法，使用wsdl地址作为参数
		PostMethod postMethod = new PostMethod(
				"http://www.webxml.com.cn/webservices/qqOnlineWebService.asmx?WSDL");
		// 然后把Soap请求数据添加到PostMethod中
		byte[] b = soapRequestData.getBytes("utf-8");
		InputStream is = new ByteArrayInputStream(b, 0, b.length);
		RequestEntity re = new InputStreamRequestEntity(is, b.length,
				"application/soap+xml; charset=utf-8");
		postMethod.setRequestEntity(re);
		// 设置请求消息头 -----------------------------------请求方法名
		postMethod.setRequestHeader("SOAPAction", "RB_BACKFLOW.v1");
		// 最后生成一个HttpClient对象，并发出postMethod请求
		HttpClient httpClient = new HttpClient();
		int statusCode = httpClient.executeMethod(postMethod);
		if (statusCode == 200) {
			System.out.println("调用成功！");
			String soapResponseData = postMethod.getResponseBodyAsString();
			System.out.println(soapResponseData);
		} else {
			System.out.println("调用失败！错误码：" + statusCode);
		}
	}

}
