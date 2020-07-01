package com.springboot.v1.configure.interceptor;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * @ClassName: BodyReaderHttpServletRequestWrapper
 * @Description: 
 * @author dyl
 * @date 2020-3-16 7:15:59
 */
public class BodyReaderHttpServletRequestWrapper extends HttpServletRequestWrapper {


    private final byte[] body;
    private String bodyStr;

    public BodyReaderHttpServletRequestWrapper(HttpServletRequest request) throws IOException {
        super(request);
        String bodyString = getBodyString(request);
       // body = bodyString.getBytes(Charset.forName("UTF-8"));
        body = bodyString.getBytes(Charset.forName("GBK"));
        bodyStr=bodyString;
    }

    public String getBodyStr() {
        return bodyStr;
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {
        final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(body);

        return new ServletInputStream() {
            @Override
            public int read() throws IOException {
                return byteArrayInputStream.read();
            }

            @Override
            public boolean isFinished() {
                return false;
            }

            @Override
            public boolean isReady() {
                return false;
            }

            @Override
            public void setReadListener(ReadListener readListener) {
            }
        };
    }


    public static String getBodyString(HttpServletRequest request) throws IOException {
        StringBuilder sb = new StringBuilder();
        InputStream inputStream = null;
        BufferedReader reader = null;
        try {
            inputStream = request.getInputStream();
            reader = new BufferedReader(
                    //new InputStreamReader(inputStream, Charset.forName("UTF-8")));
                    new InputStreamReader(inputStream, Charset.forName("GBK")));
            char[] bodyCharBuffer = new char[1024];
            int len = 0;
            while ((len = reader.read(bodyCharBuffer)) != -1) {
                sb.append(new String(bodyCharBuffer, 0, len));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return sb.toString();
    }
}