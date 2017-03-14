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
		// ������
		String soapRequestData = "<soapenv:Envelope "
				+ "xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" "
				+ "xmlns:rb=\"http://xmlns.oracle.com/Enterprise/Tools/schemas/RB_BACKFLOW_REQ.v1\">"
				+ "<soapenv:Header/>" + "<soapenv:Body>" + "<parameter>"
				+ "<rb:NationalID>110111199001010076</rb:NationalID>"
				+ "</parameter></soapenv:Body></soapenv:Envelope>";
		// ����post������ʹ��wsdl��ַ��Ϊ����
		PostMethod postMethod = new PostMethod(
				"http://www.webxml.com.cn/webservices/qqOnlineWebService.asmx?WSDL");
		// Ȼ���Soap����������ӵ�PostMethod��
		byte[] b = soapRequestData.getBytes("utf-8");
		InputStream is = new ByteArrayInputStream(b, 0, b.length);
		RequestEntity re = new InputStreamRequestEntity(is, b.length,
				"application/soap+xml; charset=utf-8");
		postMethod.setRequestEntity(re);
		// ����������Ϣͷ -----------------------------------���󷽷���
		postMethod.setRequestHeader("SOAPAction", "RB_BACKFLOW.v1");
		// �������һ��HttpClient���󣬲�����postMethod����
		HttpClient httpClient = new HttpClient();
		int statusCode = httpClient.executeMethod(postMethod);
		if (statusCode == 200) {
			System.out.println("���óɹ���");
			String soapResponseData = postMethod.getResponseBodyAsString();
			System.out.println(soapResponseData);
		} else {
			System.out.println("����ʧ�ܣ������룺" + statusCode);
		}
	}

}
